package com.solvo.hoam.presentation.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.solvo.hoam.R;
import com.solvo.hoam.data.network.response.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private List<Category> mCategoryList;

    public CategoryAdapter(Context context, List<Category> categories) {
        mLayoutInflater = LayoutInflater.from(context);
        mCategoryList = categories;
    }

    public CategoryAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mCategoryList = new ArrayList<>();
    }

    public void setCategories(List<Category> categories) {
        mCategoryList.clear();
        mCategoryList.addAll(categories);
    }

    @Override
    public int getCount() {
        return mCategoryList.size();
    }

    @Override
    public Category getItem(int position) {
        return mCategoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.valueOf(mCategoryList.get(position).getId());
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.spinner_item, parent, false);

        TextView textView = (TextView) view.findViewById(R.id.spinner_item_text_view);
        textView.setText(mCategoryList.get(position).getName());

        return view;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.spinner_title, parent, false);

        TextView textView = (TextView) view.findViewById(R.id.spinner_title_text_view);
        textView.setText(mCategoryList.get(position).getName());

        return view;
    }
}
