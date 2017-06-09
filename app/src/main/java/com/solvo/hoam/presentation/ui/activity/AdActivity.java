package com.solvo.hoam.presentation.ui.activity;

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

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.solvo.hoam.R;
import com.solvo.hoam.HoamApplication;
import com.solvo.hoam.presentation.ui.adapter.AdPagerAdapter;
import com.solvo.hoam.presentation.ui.helper.AdHelper;
import com.solvo.hoam.presentation.mvp.presenter.AdPresenter;
import com.solvo.hoam.presentation.mvp.view.AdView;

import me.relex.circleindicator.CircleIndicator;

public class AdActivity extends BaseActivity implements AdView, SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = AdActivity.class.getSimpleName();
    private static final String EXTRA_AD_ID = "ad_id";

    private View internetErrorView;
    private CoordinatorLayout coordinatorLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView titleTextView;
    private TextView priceTextView;
    private TextView usernameTextView;
    private TextView phoneTextView;
    private TextView categoryTextView;
    private TextView locationTextView;
    private TextView createdDateTextView;
    private TextView descriptionTextView;
    private TextView viewsTextView;
    private FloatingActionButton floatingActionButton;
    private CircleIndicator indicator;
    private ViewPager imageViewPager;
    private AdPagerAdapter pagerAdapter;

    @InjectPresenter
    AdPresenter mPresenter;

    @ProvidePresenter
    AdPresenter providePresenter() {
        return new AdPresenter(HoamApplication.getComponent());
    }

    public static Intent buildIntent(Context context, String adId) {
        return new Intent(context, AdActivity.class)
                .putExtra(EXTRA_AD_ID, adId);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        initToolbar(true);

        internetErrorView = findViewById(R.id.internet_error_view);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        titleTextView = (TextView) findViewById(R.id.tv_title);
        priceTextView = (TextView) findViewById(R.id.tv_price);
        usernameTextView = (TextView) findViewById(R.id.tv_username);
        phoneTextView = (TextView) findViewById(R.id.tv_phone);
        categoryTextView = (TextView) findViewById(R.id.tv_category);
        locationTextView = (TextView) findViewById(R.id.tv_location);
        createdDateTextView = (TextView) findViewById(R.id.tv_created_date);
        descriptionTextView = (TextView) findViewById(R.id.tv_description);
        viewsTextView = (TextView) findViewById(R.id.tv_views);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        indicator = (CircleIndicator) findViewById(R.id.indicator);
        imageViewPager = (ViewPager) findViewById(R.id.image_view_pager);

        mPresenter.init(getIntent().getStringExtra(EXTRA_AD_ID));
    }

    @Override
    public void setUpViews(String title, String price, String username, String category, String location,
                           String createdDate, String description, String views, String phone, String adId) {
        titleTextView.setText(title);
        priceTextView.setText(price);
        usernameTextView.setText(username);
        usernameTextView.setCompoundDrawables(AdHelper.getSupportDrawable(R.drawable.ic_user, this), null, null, null);
        phoneTextView.setText(phone);
        phoneTextView.setCompoundDrawables(AdHelper.getSupportDrawable(R.drawable.ic_phone_number, this), null, null, null);
        categoryTextView.setText(category);
        categoryTextView.setCompoundDrawables(AdHelper.getSupportDrawable(R.drawable.ic_category, this), null, null, null);
        locationTextView.setText(location);
        locationTextView.setCompoundDrawables(AdHelper.getSupportDrawable(R.drawable.ic_location, this), null, null, null);
        createdDateTextView.setText(createdDate);
        descriptionTextView.setText(description);
        viewsTextView.setText(views);
        floatingActionButton.setOnClickListener(view -> {
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone)));
        });
        pagerAdapter = new AdPagerAdapter(this, adId);
        imageViewPager.setAdapter(pagerAdapter);
        indicator.setViewPager(imageViewPager);
    }

    @Override
    public void hideViewPager() {
        imageViewPager.setVisibility(View.GONE);
    }

    @Override
    public void hidePageIndicator() {
        indicator.setVisibility(View.GONE);
    }

    @Override
    public void showLoading(boolean show) {
        swipeRefreshLayout.setRefreshing(show);
    }

    @Override
    public void showError() {
        internetErrorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showContent() {
        coordinatorLayout.setVisibility(View.VISIBLE);
        internetErrorView.setVisibility(View.GONE);
        swipeRefreshLayout.setEnabled(false);
    }

    @Override
    public void onRefresh() {
        mPresenter.fetchAd();
    }
}
