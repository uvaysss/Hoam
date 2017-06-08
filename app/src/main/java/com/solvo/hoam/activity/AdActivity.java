package com.solvo.hoam.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;

import com.solvo.hoam.R;
import com.solvo.hoam.adapter.AdPagerAdapter;
import com.solvo.hoam.helper.AdHelper;
import com.solvo.hoam.presenter.AdPresenter;
import com.solvo.hoam.view.AdView;

import me.relex.circleindicator.CircleIndicator;

public class AdActivity extends BaseActivity implements AdView, SwipeRefreshLayout.OnRefreshListener {
    public static final String TAG = AdActivity.class.getSimpleName();
    private static final String EXTRA_AD_ID = "ad_id";

    private View mInternetErrorView;
    private CoordinatorLayout mCoordinatorLayout;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView mTitleTextView;
    private TextView mPriceTextView;
    private TextView mUsernameTextView;
    private TextView mPhoneTextView;
    private TextView mCategoryTextView;
    private TextView mLocationTextView;
    private TextView mCreatedDateTextView;
    private TextView mDescriptionTextView;
    private TextView mViewsTextView;
    private FloatingActionButton mFab;
    private CircleIndicator mIndicator;
    private ViewPager mImageViewPager;

    private AdPagerAdapter mPagerAdapter;
    private AdPresenter mPresenter;

    public static Intent buildIntent(Context context, String adId) {
        return new Intent(context, AdActivity.class)
                .putExtra(EXTRA_AD_ID, adId);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        initToolbar(true);

        mInternetErrorView = findViewById(R.id.internet_error_view);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        mTitleTextView = (TextView) findViewById(R.id.tv_title);
        mPriceTextView = (TextView) findViewById(R.id.tv_price);
        mUsernameTextView = (TextView) findViewById(R.id.tv_username);
        mPhoneTextView = (TextView) findViewById(R.id.tv_phone);
        mCategoryTextView = (TextView) findViewById(R.id.tv_category);
        mLocationTextView = (TextView) findViewById(R.id.tv_location);
        mCreatedDateTextView = (TextView) findViewById(R.id.tv_created_date);
        mDescriptionTextView = (TextView) findViewById(R.id.tv_description);
        mViewsTextView = (TextView) findViewById(R.id.tv_views);
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mIndicator = (CircleIndicator) findViewById(R.id.indicator);
        mImageViewPager = (ViewPager) findViewById(R.id.image_view_pager);

        mPresenter = new AdPresenter(this);
        mPresenter.init(getIntent().getStringExtra(EXTRA_AD_ID));
    }

    @Override
    public void setUpViews(String title, String price, String username, String category, String location,
                           String createdDate, String description, String views, String phone, String adId) {
        mTitleTextView.setText(title);
        mPriceTextView.setText(price);
        mUsernameTextView.setText(username);
        mUsernameTextView.setCompoundDrawables(AdHelper.getSupportDrawable(R.drawable.ic_user, this), null, null, null);
        mPhoneTextView.setText(phone);
        mPhoneTextView.setCompoundDrawables(AdHelper.getSupportDrawable(R.drawable.ic_phone_number, this), null, null, null);
        mCategoryTextView.setText(category);
        mCategoryTextView.setCompoundDrawables(AdHelper.getSupportDrawable(R.drawable.ic_category, this), null, null, null);
        mLocationTextView.setText(location);
        mLocationTextView.setCompoundDrawables(AdHelper.getSupportDrawable(R.drawable.ic_location, this), null, null, null);
        mCreatedDateTextView.setText(createdDate);
        mDescriptionTextView.setText(description);
        mViewsTextView.setText(views);
        mFab.setOnClickListener(view -> {
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone)));
        });

        mPagerAdapter = new AdPagerAdapter(this, adId);
        mImageViewPager.setAdapter(mPagerAdapter);
        mIndicator.setViewPager(mImageViewPager);
    }

    @Override
    public void hideViewPager() {
        mImageViewPager.setVisibility(View.GONE);
    }

    @Override
    public void hidePageIndicator() {
        mIndicator.setVisibility(View.GONE);
    }

    @Override
    public void showLoading(boolean show) {
        mSwipeRefreshLayout.setRefreshing(show);
    }

    @Override
    public void showError() {
        mInternetErrorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showContent() {
        mCoordinatorLayout.setVisibility(View.VISIBLE);
        mInternetErrorView.setVisibility(View.GONE);
        mSwipeRefreshLayout.setEnabled(false);
    }

    @Override
    public void onRefresh() {
        mPresenter.fetchAd();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.onStop();
    }
}
