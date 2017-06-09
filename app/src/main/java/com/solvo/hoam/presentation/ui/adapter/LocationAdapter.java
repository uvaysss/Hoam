package com.solvo.hoam.presentation.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.solvo.hoam.R;
import com.solvo.hoam.data.network.response.LocationResponse;

import java.util.List;

public class LocationAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private List<LocationResponse> mLocationList;

    public LocationAdapter(Context context, List<LocationResponse> locations) {
        mLayoutInflater = LayoutInflater.from(context);
        mLocationList = locations;
    }

    @Override
    public int getCount() {
        return mLocationList.size();
    }

    @Override
    public LocationResponse getItem(int position) {
        return mLocationList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.valueOf(mLocationList.get(position).getRegionId());
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.spinner_item, parent, false);

        TextView textView = (TextView) view.findViewById(R.id.spinner_item_text_view);
        textView.setText(mLocationList.get(position).getName());

        return view;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.spinner_title, parent, false);

        TextView textView = (TextView) view.findViewById(R.id.spinner_title_text_view);
        textView.setText(mLocationList.get(position).getName());

        return view;
    }
}
