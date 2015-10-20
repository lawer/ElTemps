package com.example.poblenou.eltemps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.poblenou.eltemps.json.List;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class WeatherArrayAdapter extends ArrayAdapter<List> {
    public WeatherArrayAdapter(Context context, int resource, ArrayList<List> objects) {
        super(context, resource, objects);
    }

    private String getForecastString(List list) {
        Long dt = list.getDt();
        java.util.Date date = new java.util.Date(dt * 1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("E d/M");
        String dateString = dateFormat.format(date);

        String description = list.getWeather().get(0).getDescription();

        Long min = Math.round(list.getTemp().getMin());
        Long max = Math.round(list.getTemp().getMax());

        return String.format("%s - %s - %s/%s",
                dateString, description, min, max
        );
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        List forecastItem = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.listview_forecasts_row, parent, false
            );
        }

        TextView tvForecast = (TextView) convertView.findViewById(R.id.tvForecast);
        tvForecast.setText(getForecastString(forecastItem));

        return convertView;

    }
}
