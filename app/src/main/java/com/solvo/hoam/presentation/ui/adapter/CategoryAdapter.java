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
import com.solvo.hoam.data.db.model.CategoryModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<CategoryModel> categoryList;
    private Resources resources;

    public CategoryAdapter(Context context, List<CategoryModel> categoryList) {
        this.categoryList = categoryList;
        this.resources = context.getResources();
        layoutInflater = LayoutInflater.from(context);
        addGag();
    }

    public CategoryAdapter(Context context) {
        this.categoryList = new ArrayList<>();
        this.resources = context.getResources();
        layoutInflater = LayoutInflater.from(context);
    }

    public void setCategories(List<CategoryModel> categoryList) {
        this.categoryList = categoryList;
        addGag();
    }

    private void addGag() {
        categoryList.add(0, new CategoryModel(null, "Любая категория", null, null, 0, null, null));
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public CategoryModel getItem(int position) {
        return categoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position == 0 ? 0 : Long.valueOf(categoryList.get(position).getId());
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.spinner_item, parent, false);

        CategoryModel category = categoryList.get(position);

        TextView textView = (TextView) view.findViewById(R.id.spinner_item_text_view);
        textView.setText(categoryList.get(position).getName());

        if (position == 0) {
            textView.setTextColor(resources.getColor(R.color.black));
            textView.setTypeface(Typeface.DEFAULT_BOLD);
        } else if (category.getParentId() == null) {
            textView.setTextColor(resources.getColor(R.color.colorAccent));
            textView.setTypeface(Typeface.DEFAULT_BOLD);
        }

        return view;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.spinner_title, parent, false);

        TextView textView = (TextView) view.findViewById(R.id.spinner_title_text_view);
        textView.setText(categoryList.get(position).getName());

        return view;
    }
}
