package kirasoft.weatherapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dakotajustin on 11/12/16.
 */

public class WeatherReport implements MainView.ProvidedModelOps {

        @SerializedName("coord")
        @Expose
        private Coord coord;
        @SerializedName("weather")
        @Expose
        private List<Weather> weather = new ArrayList<>();
        @SerializedName("base")
        @Expose
        private String base;
        @SerializedName("main")
        @Expose
        private Main main;
        @SerializedName("wind")
        @Expose
        private Wind wind;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;

        /**
         * @return The coord
         */
        public Coord getCoord() {
            return coord;
        }

        /**
         * @param coord The coord
         */
        public void setCoord(Coord coord) {
            this.coord = coord;
        }

        /**
         * @return The weather
         */
        public List<Weather> getWeather() {
            return weather;
        }

        /**
         * @param weather The weather
         */
        public void setWeather(List<Weather> weather) {
            this.weather = weather;
        }

        /**
         * @return The base
         */
        public String getBase() {
            return base;
        }

        /**
         * @param base The base
         */
        public void setBase(String base) {
            this.base = base;
        }

        /**
         * @return The main
         */
        public Main getMain() {
            return main;
        }

        /**
         * @param main The main
         */
        public void setMain(Main main) {
            this.main = main;
        }

        /**
         * @return The wind
         */
        public Wind getWind() {
            return wind;
        }

        /**
         * @param wind The wind
         */
        public void setWind(Wind wind) {
            this.wind = wind;
        }

        /**
         * @return The id
         */
        public Integer getId() {
            return id;
        }

        /**
         * @param id The id
         */
        public void setId(Integer id) {
            this.id = id;
        }

        /**
         * @return The name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name The name
         */
        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String getCurrentCity() {
                return getName();
        }

        @Override
        public String getCurrentTemperature() {
                return this.getMain().getTemp().toString() + " Kelvin";
        }

        @Override
        public String getFullWeatherReading() {
                String shortDescript = getWeather().get(0).getDescription();
                String temp = getCurrentTemperature();
                String humidity = getMain().getHumidity().toString();
                String windSpeed = getWind().getSpeed().toString();
                String windTemp = getWind().getDeg().toString() + "°";
                String fullDescription = shortDescript + " with a temperature of " + temp +
                        " and a humidity of " + humidity + ". Winds are at a speed of " + windSpeed +
                        " with a temperature of " + windTemp;
                return fullDescription;
        }
}

