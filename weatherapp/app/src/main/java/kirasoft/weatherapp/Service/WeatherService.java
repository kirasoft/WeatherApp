package kirasoft.weatherapp.Service;


import java.util.Map;

import kirasoft.weatherapp.Model.RawWeatherReport;
import kirasoft.weatherapp.Model.WeatherReport;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by dakotajustin on 11/12/16.
 */

public interface WeatherService {

    String BASE_URL = "https://api.apixu.com/v1/";

    @GET("current.json")
    Observable<RawWeatherReport> getWeatherReport(@QueryMap Map<String, String> options);

}
