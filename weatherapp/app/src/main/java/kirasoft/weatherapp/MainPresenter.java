package kirasoft.weatherapp;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * Created by dakotajustin on 11/11/16.
 */

public class MainPresenter implements MainView.RequiredPresenterOps, MainView.ProvidedPresenterOps {

    //use a weak reference between activity could be destroyed at any moment
    private WeakReference<MainView.RequiredViewOps> view;

    //check if user has already pressed search for city so we can clear edit text field on click
    private boolean isCitySearched = false;

    //ensures only one api request is going on at once
    private boolean isSearching = false;

    //constructor
    public MainPresenter(MainView.RequiredViewOps ops) {
        view = new WeakReference<>(ops);
    }

    /**
     * Return the view constructor
     * Throw an exception if the view is unavailable
     * @return
     */
    private MainView.RequiredViewOps getView() throws NullPointerException {
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

    @Override
    public void clickUpdateWeatherText(String weatherStr, TextView weatherTextView) {
        //if waiting for api request, let user know they need to wait
        if(isSearching) {
            Toast.makeText(getAppContext(), "Wait for current request to finish before you search again",
                    Toast.LENGTH_LONG).show();
            return;
        }

        isSearching = true;
        weatherTextView.setText(weatherStr);
        //TODO: Api request

        isCitySearched = true;
    }
}
