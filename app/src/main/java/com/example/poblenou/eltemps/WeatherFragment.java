package com.example.poblenou.eltemps;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.poblenou.eltemps.json.Forecast;
import com.example.poblenou.eltemps.json.List;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * A placeholder fragment containing a simple view.
 */
public class WeatherFragment extends Fragment {

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    public WeatherFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        String[] data = {
                "Lun 23/6 - Soleado - 31/17",
                "Mar 24/6 - Niebla - 21/8",
                "Mier 25/6 - Nublado - 22/17",
                "Jue 26/6 - Lluvioso - 18/11",
                "Vie 27/6 - Niebla - 21/10",
                "Sab 28/6 - Soleado - 23/18",
                "Dom 29/6 - Soleado - 20/7"
        };

        items = new ArrayList<>(Arrays.asList(data));
        adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.listview_forecasts_row,
                R.id.tvForecast,
                items
        );

        ListView lvForecast = (ListView) rootView.findViewById(R.id.lvForecasts);
        lvForecast.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_weather_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_refresh) {
            refresh();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void refresh() {
        final String FORECAST_BASE_URL = "http://api.openweathermap.org/data/2.5/";
        final String CITY = "Barcelona";
        final String APPID = "bd82977b86bf27fb59a04b61b657fb6f";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FORECAST_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OpenWeatherMapService service = retrofit.create(OpenWeatherMapService.class);

        Call<Forecast> forecastCall = service.dailyForecast(
                CITY, "json", "metric", 14, APPID
        );
        forecastCall.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Response<Forecast> response, Retrofit retrofit) {
                Forecast forecast = response.body();

                ArrayList<String> forecastStrings = new ArrayList<>();
                for (List list : forecast.getList()) {
                    Long dt = list.getDt();
                    java.util.Date date = new java.util.Date(dt * 1000);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("E d/M");
                    String dateString = dateFormat.format(date);

                    String description = list.getWeather().get(0).getDescription();

                    Long min = Math.round(list.getTemp().getMin());
                    Long max = Math.round(list.getTemp().getMax());

                    String forecastString = String.format("%s - %s - %s/%s",
                            dateString, description, min, max
                    );
                    forecastStrings.add(forecastString);
                }

                adapter.clear();
                adapter.addAll(forecastStrings);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.w(null, Arrays.toString(t.getStackTrace()));
            }
        });
    }

    public interface OpenWeatherMapService {
        @GET("forecast/daily")
        Call<Forecast> dailyForecast(
                @Query("q") String city,
                @Query("mode") String format,
                @Query("units") String units,
                @Query("cnt") Integer num,
                @Query("appid") String appid);
    }

}
