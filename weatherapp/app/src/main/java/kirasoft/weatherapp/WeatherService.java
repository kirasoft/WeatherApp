package kirasoft.weatherapp;


import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by dakotajustin on 11/12/16.
 */

public interface WeatherService {

    String BASE_URL = "api.openweathermap.org/data";

    @GET("/2.5/weather?q={city}&appid=38a6433be73ae261b56b5883a61f7d0a")
    Observable<WeatherReport> getWeatherReport(@Path("city") String city);

}
