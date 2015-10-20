package com.example.poblenou.eltemps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

        TextView tvListItemDate = (TextView) convertView.findViewById(R.id.tvListItemDate);
        tvListItemDate.setText(forecastItem.getDateString());

        TextView tvListItemForecast = (TextView) convertView.findViewById(R.id.tvListItemForecast);
        tvListItemForecast.setText(forecastItem.getWeather().get(0).getDescription());

        TextView tvListItemHighTextView = (TextView) convertView.findViewById(R.id.tvListItemHighTextView);
        tvListItemHighTextView.setText(String.valueOf(forecastItem.getRoundedMinTemp()));

        TextView tvListItemLowTextView = (TextView) convertView.findViewById(R.id.tvListItemLowTextView);
        tvListItemLowTextView.setText(String.valueOf(forecastItem.getRoundedMaxTemp()));

        ImageView ivListItemIcon = (ImageView) convertView.findViewById(R.id.ivListItemIcon);
        ivListItemIcon.setImageResource(R.drawable.cloud);

        return convertView;

    }
}
