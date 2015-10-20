package com.example.poblenou.eltemps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.poblenou.eltemps.json.List;

import java.util.ArrayList;

public class WeatherArrayAdapter extends ArrayAdapter<List> {
    public WeatherArrayAdapter(Context context, int resource, ArrayList<List> objects) {
        super(context, resource, objects);
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
        tvForecast.setText(forecastItem.forecastString());

        return convertView;

    }
}
