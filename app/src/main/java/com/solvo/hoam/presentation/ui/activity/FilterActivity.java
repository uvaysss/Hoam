package com.solvo.hoam.presentation.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.solvo.hoam.HoamApplication;
import com.solvo.hoam.R;
import com.solvo.hoam.data.db.model.CategoryModel;
import com.solvo.hoam.data.db.model.LocationModel;
import com.solvo.hoam.presentation.mvp.presenter.FilterPresenter;
import com.solvo.hoam.presentation.mvp.view.FilterView;
import com.solvo.hoam.presentation.ui.adapter.CategoryAdapter;
import com.solvo.hoam.presentation.ui.adapter.LocationAdapter;

import java.util.List;

public class FilterActivity extends BaseActivity implements FilterView {

    public static final String TAG = FilterActivity.class.getSimpleName();

    private CategoryAdapter categoryAdapter;
    private LocationAdapter locationAdapter;
    private Spinner locationSpinner;
    private Spinner categorySpinner;
    private EditText priceFromEditText;
    private EditText priceToEditText;

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

        locationAdapter = new LocationAdapter(this);
        locationSpinner = (Spinner) findViewById(R.id.spinner_location);
        locationSpinner.setAdapter(locationAdapter);

        categoryAdapter = new CategoryAdapter(this);
        categorySpinner = (Spinner) findViewById(R.id.spinner_parent_category);
        categorySpinner.setAdapter(categoryAdapter);

        priceFromEditText = (EditText)findViewById(R.id.et_price_from);
        priceToEditText = (EditText)findViewById(R.id.et_price_to);

        presenter.init();
    }

    @Override
    public void setLocations(List<LocationModel> locations, int locationPosition) {
        locationAdapter.setLocations(locations);
        locationAdapter.notifyDataSetChanged();
        locationSpinner.setSelection(locationPosition);
    }

    @Override
    public void setCategories(List<CategoryModel> categories, int categoryPosition) {
        categoryAdapter.setCategories(categories);
        categoryAdapter.notifyDataSetChanged();
        categorySpinner.setSelection(categoryPosition);
    }

    @Override
    public void setUpPrices(String priceFrom, String priceTo) {
        priceFromEditText.setText(priceFrom);
        priceToEditText.setText(priceTo);
    }

    @Override
    public void setViewPositions(int location, int category) {
        locationSpinner.setSelection(location);
        categorySpinner.setSelection(category);
    }

    @Override
    public void close() {
        setResult(Activity.RESULT_OK);
        finish();
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
                presenter.saveFilterInfo(
                        locationAdapter.getItem(locationSpinner.getSelectedItemPosition()),
                        categoryAdapter.getItem(categorySpinner.getSelectedItemPosition()),
                        locationSpinner.getSelectedItemPosition(),
                        categorySpinner.getSelectedItemPosition(),
                        priceFromEditText.getText().toString(),
                        priceToEditText.getText().toString()
                );
                return true;
            case R.id.action_reset:
                presenter.resetFilterInfo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
