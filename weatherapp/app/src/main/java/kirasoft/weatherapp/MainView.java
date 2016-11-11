package kirasoft.weatherapp;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;

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
        void updateCurrentWeather(Weather weather);
    }

    /**
     * Operations offered to View to communicate with Presenter.
     * Processes user interactions, sends data requests to Model, etc.
     */
    interface ProvidedPresenterOps {
        // Presenter operations permitted to View
        void clickEnterCity(EditText editText);
        void clickUpdateWeatherText(Button button);
    }

    /**
     * Required Presenter methods available to Model.
     */
    interface RequiredPresenterOps {
        // Presenter operations permitted to Model
        Context getAppContext();
        Context getActivityContext();
    }


}
