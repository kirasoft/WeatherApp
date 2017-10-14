package kirasoft.weatherapp.Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import kirasoft.weatherapp.Model.RawWeatherReport;
import kirasoft.weatherapp.Model.WeatherReport;
import kirasoft.weatherapp.R;
import kirasoft.weatherapp.Service.WeatherRetrofit;
import kirasoft.weatherapp.Service.WeatherService;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dakotajustin on 11/11/16.
 */

public class MainPresenter implements MainMvp.RequiredPresenterOps, MainMvp.ProvidedPresenterOps {

    //use a WeakReference because activity could be destroyed at any moment
    private WeakReference<MainMvp.RequiredViewOps> currentView;
    private boolean isBusy;

    private static final String TAG = "MainPresenter";
    private static final String BUSY_SEARCH_WARNING = "Wait for current request to finish before you search again";

    public MainPresenter(MainMvp.RequiredViewOps ops) {
        currentView = new WeakReference<>(ops);
    }

    /**
     * Return the view constructor
     * Throw an exception if the view is unavailable
     */
    private MainMvp.RequiredViewOps getView() throws NullPointerException {
        if(currentView != null) {
            return currentView.get();
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
        if(!isBusy)
            editText.setText("");
    }

    /**
     * Get weather report from user entered city.
     * @param cityStr Name of city weather report will search for
     */
    @Override
    public void clickUpdateWeatherText(final String cityStr, final TextView weatherTextView) {
        //if waiting for api request, let user know they need to wait
        if(isBusy) {
            Toast.makeText(getAppContext(), BUSY_SEARCH_WARNING,
                    Toast.LENGTH_LONG).show();
            return;
        }

        isBusy = true;

        Map<String, String> data = new HashMap<>();
        data.put("key", getActivityContext().getString(R.string.weather_api_key));
        data.put("q", cityStr);

        WeatherService weatherService = WeatherRetrofit.createRetrofitService(WeatherService.class, WeatherService.BASE_URL);
        weatherService.getWeatherReport(data)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RawWeatherReport>() {
                    @Override
                    public void onCompleted() {
                        //always gets called whether error or success
                        isBusy = false;
                    }

                    @Override
                    public void onError(Throwable e) {
                        //if there was an issue
                        Log.e(TAG, "Issue getting weather report from " + cityStr, e);
                        isBusy = false;
                    }

                    @Override
                    public void onNext(RawWeatherReport rawWeatherReport) {

                        //update weather text
                        if(rawWeatherReport.getWeatherReport() == null)
                        {
                            weatherTextView.setText("There was an error. Try again.");
                            isBusy = false;
                        }

                        final WeatherReport weatherReport = rawWeatherReport.getWeatherReport();

                        final String weatherReading = weatherReport.getConditionReport().getText() + "\n"
                                                                + "Current temperature is " + (int)weatherReport.getTemperature() + "\u00b0F\n"
                                                                + "Current Humidity is " +  (int)weatherReport.getHumidity() + "%";

                        weatherTextView.setText(weatherReading);

                        isBusy = false;
                    }
                });
    }

}
