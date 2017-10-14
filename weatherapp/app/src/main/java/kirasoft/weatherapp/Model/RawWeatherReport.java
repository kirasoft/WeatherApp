package kirasoft.weatherapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by d780694 on 10/14/17.
 */

public class RawWeatherReport {

    @Expose
    @SerializedName("current")
    private WeatherReport weatherReport;

    @Expose
    private Location location;

    public WeatherReport getWeatherReport() {
        return weatherReport;
    }

    public void setWeatherReport(WeatherReport weatherReport) {
        this.weatherReport = weatherReport;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
