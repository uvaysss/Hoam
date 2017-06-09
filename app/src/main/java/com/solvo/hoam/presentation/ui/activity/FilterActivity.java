package com.solvo.hoam.presentation.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.solvo.hoam.R;
import com.solvo.hoam.HoamApplication;
import com.solvo.hoam.presentation.ui.adapter.CategoryAdapter;
import com.solvo.hoam.presentation.ui.adapter.LocationAdapter;
import com.solvo.hoam.data.network.response.Category;
import com.solvo.hoam.data.network.response.LocationResponse;
import com.solvo.hoam.presentation.mvp.presenter.FilterPresenter;
import com.solvo.hoam.presentation.mvp.view.FilterView;
import com.solvo.hoam.presentation.ui.adapter.SpinnerAdapter;

import java.util.List;

public class FilterActivity extends BaseActivity implements FilterView {
    public static final String TAG = FilterActivity.class.getSimpleName();

    private Spinner locationSpinner;
    private Spinner parentCategorySpinner;
    private Spinner subCategorySpinner;
    private EditText priceFromEditText;
    private EditText priceToEditText;
    private CategoryAdapter subCategoryAdapter;

    @InjectPresenter
    FilterPresenter presenter;

    @ProvidePresenter
    FilterPresenter providePresenter() {
        return new FilterPresenter(HoamApplication.getComponent());
    }

    public static Intent buildIntent(Context context) {
        return new Intent(context, FilterActivity.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        initToolbar(true);

        locationSpinner = (Spinner) findViewById(R.id.spinner_location);
        parentCategorySpinner = (Spinner) findViewById(R.id.spinner_parent_category);
        subCategorySpinner = (Spinner) findViewById(R.id.spinner_sub_category);
        priceFromEditText = (EditText)findViewById(R.id.et_price_from);
        priceToEditText = (EditText)findViewById(R.id.et_price_to);

        presenter.init();
    }

    @Override
    public void setUpViews(List<LocationResponse> locations, List<Category> categories) {
        locationSpinner.setAdapter(new LocationAdapter(this, locations));
        parentCategorySpinner.setAdapter(new CategoryAdapter(this, categories));
        parentCategorySpinner.setOnItemSelectedListener(new SpinnerAdapter() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.onParentCategoryItemClicked(position);
            }
        });

        subCategoryAdapter = new CategoryAdapter(this);
        subCategorySpinner.setAdapter(subCategoryAdapter);
    }

    @Override
    public void setViewPositions(int location, int parentCategory) {
        locationSpinner.setSelection(location);
        parentCategorySpinner.setSelection(parentCategory);
    }

    @Override
    public void setUpPrices(String priceFrom, String priceTo) {
        priceFromEditText.setText(priceFrom);
        priceToEditText.setText(priceTo);
    }

    @Override
    public void updateSubCategories(List<Category> categories, int position) {
        subCategoryAdapter.setCategories(categories);
        subCategoryAdapter.notifyDataSetChanged();
        subCategorySpinner.setSelection(position);
    }

    @Override
    public void close() {
        setResult(Activity.RESULT_OK);
        finish();
    }

    @Override
    public void showSubCategory(boolean show) {
        subCategorySpinner.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                presenter.onSaveClicked(locationSpinner.getSelectedItemPosition(),
                        parentCategorySpinner.getSelectedItemPosition(),
                        subCategorySpinner.getSelectedItemPosition(),
                        priceFromEditText.getText().toString(),
                        priceToEditText.getText().toString());
                return true;
            case R.id.action_reset:
                presenter.onResetClicked();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
