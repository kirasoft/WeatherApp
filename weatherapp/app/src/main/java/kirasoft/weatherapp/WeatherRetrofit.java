package kirasoft.weatherapp;

import retrofit2.Retrofit;

/**
 * Created by dakotajustin on 11/12/16.
 */

public class WeatherRetrofit {


    //generic retrofit service creator
    //this is a pattern I love to follow in many of my apps
    static <T> T createRetrofitService(final Class<T> clazz, final String endPoint) {
        final Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(endPoint)
                .build();
        T service = restAdapter.create(clazz);

        return service;
    }

}
