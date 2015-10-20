package com.example.poblenou.eltemps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.poblenou.eltemps.json.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        TextView tvForecastDetail = (TextView) view.findViewById(R.id.tvForecastDetail);

        Intent i = getActivity().getIntent();
        List item = i.getParcelableExtra("item");

        tvForecastDetail.setText(item.forecastString());
        return view;
    }
}
