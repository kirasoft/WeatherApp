package kirasoft.weatherapp;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dakotajustin on 11/11/16.
 */

public class MainPresenter implements MainMvp.RequiredPresenterOps, MainMvp.ProvidedPresenterOps {

    //use a weak reference between activity could be destroyed at any moment
    private WeakReference<MainMvp.RequiredViewOps> view;

    //check if user has already pressed search for city so we can clear edit text field on click
    private boolean isCitySearched = false;

    //ensures only one api request is going on at once
    private boolean isSearching = false;

    //tag for logging
    private static final String TAG = "MainPresenter";

    //constructor
    public MainPresenter(MainMvp.RequiredViewOps ops) {
        view = new WeakReference<>(ops);
    }

    /**
     * Return the view constructor
     * Throw an exception if the view is unavailable
     * @return
     */
    private MainMvp.RequiredViewOps getView() throws NullPointerException {
        if(view != null) {
            return view.get();
        } else {
            throw new NullPointerException("View is not available");
        }
    }


    //its always nice to have a reference to this
    @Override
    public Context getAppContext() {
        try {
            return getView().getAppContext();
        } catch (NullPointerException e) {
            return null;
        }
    }

    //nice to have a reference to this one too
    @Override
    public Context getActivityContext() {
        try {
            return getView().getActivityContext();
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public void clickEnterCity(EditText editText) {
        //clear edit text when user clicks on it
        if(isCitySearched) {
            editText.setText("");
            isCitySearched = false;
        }
    }

    /**
     * Get weather report from user entered city.
     * @param cityStr Name of city weather report will search for
     * @param weatherTextView Textview to update with weather report
     */
    @Override
    public void clickUpdateWeatherText(final String cityStr, final TextView weatherTextView) {
        //if waiting for api request, let user know they need to wait
        if(isSearching) {
            Toast.makeText(getAppContext(), "Wait for current request to finish before you search again",
                    Toast.LENGTH_LONG).show();
            return;
        }

        isSearching = true;
       // weatherTextView.setText(weatherStr);
        //TODO: Dependency injection and Bus System
        WeatherService weatherService = WeatherRetrofit.createRetrofitService(WeatherService.class, WeatherService.BASE_URL);
        weatherService.getWeatherReport(cityStr, getAppContext().getString(R.string.weather_api_key))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WeatherReport>() {
                    @Override
                    public void onCompleted() {
                        //always gets called wether error or success
                        isSearching = false;
                    }

                    @Override
                    public void onError(Throwable e) {
                        //if there was an issue
                        Log.e(TAG, "Issue getting weather report from " + cityStr, e);
                    }

                    @Override
                    public void onNext(WeatherReport weatherReport) {
                        //update weather text
                        weatherTextView.setText(weatherReport.getFullWeatherReading());
                        isCitySearched = true;
                    }
                });


    }

}
