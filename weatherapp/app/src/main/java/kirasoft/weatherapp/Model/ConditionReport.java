package kirasoft.weatherapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by d780694 on 10/10/17.
 */

public class ConditionReport {

    @Expose
    private String text;
    @Expose
    @SerializedName("icon")
    private String iconLink;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIconLink() {
        return iconLink;
    }

    public void setIconLink(String iconLink) {
        this.iconLink = iconLink;
    }

}
