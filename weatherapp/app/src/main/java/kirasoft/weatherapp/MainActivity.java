package kirasoft.weatherapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dakotajustin on 11/11/16.
 */

public class MainActivity extends Activity implements MainView.RequiredViewOps {

    private MainView.ProvidedPresenterOps presenter;

    @BindView(R.id.edit_text_city)
    EditText editCity;
    @BindView(R.id.button_update_weather)
    Button updateWeather;
    @BindView(R.id.text_view_instructions)
    TextView instructions;
    @BindView(R.id.text_view_weather)
    TextView weatherText;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }



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
