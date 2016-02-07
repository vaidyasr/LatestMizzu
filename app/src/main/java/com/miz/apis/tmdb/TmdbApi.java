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
                    .addInterceptor(new ThrottlingInterceptor()).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            sInstance = retrofit.create(TmdbApiService.class);
        }

        return sInstance;
    }

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
