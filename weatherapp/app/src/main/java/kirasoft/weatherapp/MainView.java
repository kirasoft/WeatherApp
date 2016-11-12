package kirasoft.weatherapp;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by dakotajustin on 11/11/16.
 */

public interface MainView {

    /**
     *   Required View methods available to Presenter.
    * A passive layer, responsible to show data
    * and receive user interactions
    */
    interface RequiredViewOps {
        // View operations permitted to Presenter
        Context getAppContext();
        Context getActivityContext();

    }

    /**
     * Operations offered to View to communicate with Presenter.
     * Processes user interactions, sends data requests to Model, etc.
     */
    interface ProvidedPresenterOps {
        // Presenter operations permitted to View
        void clickEnterCity(EditText editText);
        void clickUpdateWeatherText(String weatherStr, TextView weatherTextView);
    }

    /**
     * Required Presenter methods available to Model.
     */
    interface RequiredPresenterOps {
        // Presenter operations permitted to Model
        Context getAppContext();
        Context getActivityContext();
    }

    /**
     *  Provided methods for model to communicate with Presenter
     */
    interface ProvidedModelOps {
        String getCurrentCity();
        String getCurrentTemperature();
        String getFullWeatherReading();
    }


}
