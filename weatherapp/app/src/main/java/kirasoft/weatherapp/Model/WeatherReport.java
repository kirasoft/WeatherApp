package kirasoft.weatherapp.Model;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import kirasoft.weatherapp.R;

import static android.content.res.Resources.getSystem;

/**
 * Created by d780694 on 10/10/17.
 */

public class WeatherReport {

    @Expose
    @SerializedName("temp_f")
    private double temperature;

    @Expose
    @SerializedName("condition")
    private ConditionReport conditionReport;

    @Expose
    @SerializedName("wind_mph")
    private double windSpeed;

    @Expose
    private double humidity;

    @Expose
    @SerializedName("feelslike_f")
    private double feelsLikeTemperature;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public ConditionReport getConditionReport() {
        if(conditionReport == null) {
            conditionReport = new ConditionReport();
            conditionReport.setText("No condition report found");
            conditionReport.setIconLink("");
        }

        return conditionReport;
    }

    public void setConditionReport(ConditionReport conditionReport) {
        this.conditionReport = conditionReport;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getFeelsLikeTemperature() {
        return feelsLikeTemperature;
    }

    public void setFeelsLikeTemperature(double feelsLikeTemperature) {
        this.feelsLikeTemperature = feelsLikeTemperature;
    }
}


