package com.miz.apis.tmdb;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by miche on 06-02-2016.
 */
public class TmdbApi {

    private static final String API_URL = "https://api.themoviedb.org/3/";

    private TmdbApi() {}

    private static TmdbApiService sInstance;

    public static TmdbApiService getInstance() {
        if (sInstance == null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addInterceptor(new ThrottlingInterceptor())
                    .addInterceptor(new ErrorInterceptor())
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            sInstance = retrofit.create(TmdbApiService.class);
        }

        return sInstance;
    }

    /**
     * An OkHttp Interceptor that handles server errors from TMDb and
     * attempts to retry any failed requests after waiting 5 seconds.
     */
    private static class ErrorInterceptor implements Interceptor {

        private final Lock requestLock = new ReentrantLock();

        @Override
        public Response intercept(Chain chain) throws IOException {

            final Request request = chain.request();
            Response response = chain.proceed(request);

            // If the response code is 500 or more, it's a server error and we'll have to try again
            if (response.code() >= 500) {
                requestLock.lock();

                try {
                    // Sleep 5 seconds before retrying
                    Thread.sleep(5000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    requestLock.unlock();

                    // Re-send the request after waiting
                    response = chain.proceed(request);
                }
            }

            return response;
        }

    }

    /**
     * An OkHttp Interceptor to handle TMDb's API request rate limit,
     * currently of 40 requests per 10 seconds. If we hit the rate limit,
     * we wait until it has been reset and try again.
     */
    private static class ThrottlingInterceptor implements Interceptor {

        private final Lock requestLock = new ReentrantLock();

        @Override
        public Response intercept(Chain chain) throws IOException {

            final Request request = chain.request();
            Response response = chain.proceed(request);

            String rateLimitRemaining = response.header("X-RateLimit-Remaining");

            if (rateLimitRemaining.equals("0")) {
                // Get the rate limit reset and change the value from seconds to milliseconds
                String rateLimitReset = response.header("X-RateLimit-Reset") + "000";
                long limitReset = Long.valueOf(rateLimitReset);

                // Get the server time, so we know how long to wait
                String serverTime = response.header("Date");
                long serverTimeMillis = limitReset;
                SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);
                try {
                    serverTimeMillis = sdf.parse(serverTime).getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                // Calculate how long to wait before sending requests again
                long timeToWait = limitReset - serverTimeMillis;

                requestLock.lock();

                try {
                    Thread.sleep(timeToWait);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    requestLock.unlock();

                    // Re-send the request after waiting
                    response = chain.proceed(request);
                }
            }

            return response;
        }
    }

}
