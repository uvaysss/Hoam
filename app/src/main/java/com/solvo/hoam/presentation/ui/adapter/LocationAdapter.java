package com.solvo.hoam.presentation.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.solvo.hoam.R;
import com.solvo.hoam.data.db.model.LocationModel;

import java.util.ArrayList;
import java.util.List;

public class LocationAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<LocationModel> locationList;
    private Resources resources;

    public LocationAdapter(Context context, List<LocationModel> locationList) {
        this.locationList = locationList;
        this.resources = context.getResources();
        layoutInflater = LayoutInflater.from(context);
        addGag();
    }

    public LocationAdapter(Context context) {
        this.locationList = new ArrayList<>();
        this.resources = context.getResources();
        layoutInflater = LayoutInflater.from(context);
    }

    public void setLocations(List<LocationModel> locationList) {
        this.locationList = locationList;
        addGag();
    }

    private void addGag() {
        locationList.add(0, new LocationModel(null, "Любой город", null, null, null));
    }

    @Override
    public int getCount() {
        return locationList.size();
    }

    @Override
    public LocationModel getItem(int position) {
        return locationList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position == 0 ? 0 : Long.valueOf(locationList.get(position).getId());
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.spinner_item, parent, false);

        TextView textView = (TextView) view.findViewById(R.id.spinner_item_text_view);
        textView.setText(locationList.get(position).getName());

        if (position == 0) {
            textView.setTextColor(resources.getColor(R.color.dark));
            textView.setTypeface(Typeface.DEFAULT_BOLD);
        }

        return view;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.spinner_title, parent, false);

        TextView textView = (TextView) view.findViewById(R.id.spinner_title_text_view);
        textView.setText(locationList.get(position).getName());

        return view;
    }
}
