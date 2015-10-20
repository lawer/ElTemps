package com.example.poblenou.eltemps;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import com.example.poblenou.eltemps.json.Forecast;
import com.example.poblenou.eltemps.json.List;

import java.util.Arrays;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Query;

interface OpenWeatherMapService {
    @GET("forecast/daily")
    Call<Forecast> dailyForecast(
            @Query("q") String city,
            @Query("mode") String format,
            @Query("units") String units,
            @Query("cnt") Integer num,
            @Query("appid") String appid);
}

public class OwmApiClient {
    private final OpenWeatherMapService service;
    private final String FORECAST_BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private final String CITY = "Barcelona";
    private final String APPID = "bd82977b86bf27fb59a04b61b657fb6f";


    public OwmApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FORECAST_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(OpenWeatherMapService.class);
    }

    public void updateForecasts(String location,
                                String units,
                                final WeatherArrayAdapter adapter,
                                final SwipeRefreshLayout refreshLayout) {

        Call<Forecast> forecastCall = service.dailyForecast(
                location, "json", units, 14, APPID
        );
        forecastCall.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Response<Forecast> response, Retrofit retrofit) {
                Forecast forecast = response.body();

                adapter.clear();
                for (List forecastItem : forecast.getList()) {
                    adapter.add(forecastItem);
                }

                refreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Update Forecasts", Arrays.toString(t.getStackTrace()));
            }
        });

    }

}

