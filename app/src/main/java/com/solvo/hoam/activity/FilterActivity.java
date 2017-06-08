package com.solvo.hoam.activity;

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

import com.solvo.hoam.R;
import com.solvo.hoam.adapter.CategoryAdapter;
import com.solvo.hoam.adapter.LocationAdapter;
import com.solvo.hoam.model.response.Category;
import com.solvo.hoam.model.response.LocationResponse;
import com.solvo.hoam.presenter.FilterPresenter;
import com.solvo.hoam.view.FilterView;
import com.solvo.hoam.view.listener.SpinnerAdapter;

import java.util.List;

public class FilterActivity extends BaseActivity implements FilterView {
    public static final String TAG = FilterActivity.class.getSimpleName();

    private Spinner mLocationSpinner;
    private Spinner mParentCategorySpinner;
    private Spinner mSubCategorySpinner;
    private EditText mPriceFromEditText;
    private EditText mPriceToEditText;

    private FilterPresenter mPresenter;
    private CategoryAdapter mSubCategoryAdapter;

    public static Intent buildIntent(Context context) {
        return new Intent(context, FilterActivity.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        initToolbar(true);

        mLocationSpinner = (Spinner) findViewById(R.id.spinner_location);
        mParentCategorySpinner = (Spinner) findViewById(R.id.spinner_parent_category);
        mSubCategorySpinner = (Spinner) findViewById(R.id.spinner_sub_category);
        mPriceFromEditText = (EditText)findViewById(R.id.et_price_from);
        mPriceToEditText = (EditText)findViewById(R.id.et_price_to);

        mPresenter = new FilterPresenter(this);
        mPresenter.init();
    }

    @Override
    public void setUpViews(List<LocationResponse> locations, List<Category> categories) {
        mLocationSpinner.setAdapter(new LocationAdapter(this, locations));
        mParentCategorySpinner.setAdapter(new CategoryAdapter(this, categories));
        mParentCategorySpinner.setOnItemSelectedListener(new SpinnerAdapter() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPresenter.onParentCategoryItemClicked(position);
            }
        });

        mSubCategoryAdapter = new CategoryAdapter(this);
        mSubCategorySpinner.setAdapter(mSubCategoryAdapter);
    }

    @Override
    public void setViewPositions(int location, int parentCategory) {
        mLocationSpinner.setSelection(location);
        mParentCategorySpinner.setSelection(parentCategory);
    }

    @Override
    public void setUpPrices(String priceFrom, String priceTo) {
        mPriceFromEditText.setText(priceFrom);
        mPriceToEditText.setText(priceTo);
    }

    @Override
    public void updateSubCategories(List<Category> categories, int position) {
        mSubCategoryAdapter.setCategories(categories);
        mSubCategoryAdapter.notifyDataSetChanged();
        mSubCategorySpinner.setSelection(position);
    }

    @Override
    public void close() {
        setResult(Activity.RESULT_OK);
        finish();
    }

    @Override
    public void showSubCategory(boolean show) {
        mSubCategorySpinner.setVisibility(show ? View.VISIBLE : View.GONE);
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
                mPresenter.onSaveClicked(mLocationSpinner.getSelectedItemPosition(),
                        mParentCategorySpinner.getSelectedItemPosition(),
                        mSubCategorySpinner.getSelectedItemPosition(),
                        mPriceFromEditText.getText().toString(),
                        mPriceToEditText.getText().toString());
                return true;
            case R.id.action_reset:
                mPresenter.onResetClicked();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
