package kirasoft.weatherapp;

import android.app.Activity;
import android.content.Context;

/**
 * Created by dakotajustin on 11/11/16.
 */

public class MainActivity extends Activity implements MainView.RequiredViewOps {

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public void updateCurrentWeather(Weather weather) {
        //TODO:
        //display current weather as text in textview
    }

}
