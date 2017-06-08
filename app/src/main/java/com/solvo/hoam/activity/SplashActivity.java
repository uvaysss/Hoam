package com.solvo.hoam.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.solvo.hoam.R;
import com.solvo.hoam.presenter.SplashPresenter;
import com.solvo.hoam.view.SplashView;

public class SplashActivity extends AppCompatActivity implements SplashView, SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private SplashPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mPresenter = new SplashPresenter(this);
        mPresenter.init();
    }

    @Override
    public void showError() {
        Toast.makeText(this, getString(R.string.internet_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading(boolean show) {
        mSwipeRefreshLayout.setRefreshing(show);
    }

    @Override
    public void goToMainScreen() {
        startActivity(HomeActivity.buildIntent(this));
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    public void onRefresh() {
        mPresenter.fetchCategories();
    }
}
