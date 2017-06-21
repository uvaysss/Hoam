package com.solvo.hoam.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.solvo.hoam.HoamApplication;
import com.solvo.hoam.R;
import com.solvo.hoam.domain.model.AdEntity;
import com.solvo.hoam.presentation.mvp.presenter.AdPresenter;
import com.solvo.hoam.presentation.mvp.view.AdView;
import com.solvo.hoam.presentation.ui.adapter.AdPagerAdapter;
import com.solvo.hoam.presentation.ui.fragment.ConnectionFragment;
import com.solvo.hoam.presentation.ui.helper.AdHelper;

import me.relex.circleindicator.CircleIndicator;

public class AdActivity extends BaseActivity implements AdView {

    public static final String TAG = AdActivity.class.getSimpleName();
    private static final String EXTRA_AD_ID = "ad_id";

    private FragmentManager fragmentManager = getSupportFragmentManager();

    private View contentLayout;
    private View imageLayout;
    private ProgressBar progressBar;
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
    AdPresenter presenter;

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

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        contentLayout = findViewById(R.id.content_layout);
        imageLayout = findViewById(R.id.image_layout);
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

        presenter.init(getIntent().getStringExtra(EXTRA_AD_ID));

        initConnectionFragment();
    }

    private void initConnectionFragment() {
        ConnectionFragment connectionFragment = ConnectionFragment.newInstance();
        connectionFragment.setOnClickListener(() -> presenter.onTryAgainClicked());

        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, connectionFragment, ConnectionFragment.TAG)
                .commit();
        fragmentManager.executePendingTransactions();
        fragmentManager.beginTransaction()
                .hide(fragmentManager.findFragmentByTag(ConnectionFragment.TAG))
                .commit();
    }

    @Override
    public void setUpViews(AdEntity ad) {
        titleTextView.setText(ad.getTitle());
        priceTextView.setText(AdHelper.getPrice(ad.getPrice()));
        usernameTextView.setText(ad.getAuthorName());
        usernameTextView.setCompoundDrawables(AdHelper.getSupportDrawable(R.drawable.ic_user, this), null, null, null);
        categoryTextView.setText(ad.getCategory());
        categoryTextView.setCompoundDrawables(AdHelper.getSupportDrawable(R.drawable.ic_category, this), null, null, null);
        locationTextView.setText(ad.getLocation());
        locationTextView.setCompoundDrawables(AdHelper.getSupportDrawable(R.drawable.ic_location, this), null, null, null);
        phoneTextView.setText(ad.getPhone());
        phoneTextView.setCompoundDrawables(AdHelper.getSupportDrawable(R.drawable.ic_phone_number, this), null, null, null);
        createdDateTextView.setText(AdHelper.getAdCreatedDate(ad.getCreatedAt(), false));
        descriptionTextView.setText(ad.getText());
        viewsTextView.setText(AdHelper.getViews(ad.getViews(), getResources()));
        floatingActionButton.setOnClickListener(view -> {
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + ad.getPhone())));
        });
        pagerAdapter = new AdPagerAdapter(this, ad.getImageList());
        imageViewPager.setAdapter(pagerAdapter);
        indicator.setViewPager(imageViewPager);
    }

    @Override
    public void hideImageLayout() {
        imageLayout.setVisibility(View.GONE);
    }

    @Override
    public void hidePageIndicator() {
        indicator.setVisibility(View.GONE);
    }

    @Override
    public void showLoading(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showError() {
        fragmentManager.beginTransaction()
                .show(fragmentManager.findFragmentByTag(ConnectionFragment.TAG))
                .commit();
    }

    @Override
    public void showContent() {
        fragmentManager.beginTransaction()
                .hide(fragmentManager.findFragmentByTag(ConnectionFragment.TAG))
                .commit();

        contentLayout.setVisibility(View.VISIBLE);
        floatingActionButton.setVisibility(View.VISIBLE);
    }

}
