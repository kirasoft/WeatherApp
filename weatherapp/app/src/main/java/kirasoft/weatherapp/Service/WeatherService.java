package kirasoft.weatherapp;


import kirasoft.weatherapp.Model.WeatherReport;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by dakotajustin on 11/12/16.
 */

public interface WeatherService {

    String BASE_URL = "http://api.openweathermap.org/data/";

    @GET("2.5/weather")
    Observable<WeatherReport> getWeatherReport(@Query("q") String city, @Query("appId") String appId);

}
