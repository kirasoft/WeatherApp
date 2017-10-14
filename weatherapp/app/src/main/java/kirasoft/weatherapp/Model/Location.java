package kirasoft.weatherapp.Model;

import com.google.gson.annotations.Expose;

/**
 * Created by d780694 on 10/14/17.
 */

public class Location {

    @Expose
    private String name;

    @Expose
    private String region;

    @Expose
    private String country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
