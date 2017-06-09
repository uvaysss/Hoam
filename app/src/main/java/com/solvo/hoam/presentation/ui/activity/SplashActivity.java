package com.solvo.hoam.presentation.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.solvo.hoam.R;
import com.solvo.hoam.HoamApplication;
import com.solvo.hoam.presentation.mvp.presenter.SplashPresenter;
import com.solvo.hoam.presentation.mvp.view.SplashView;

public class SplashActivity extends MvpAppCompatActivity implements SplashView, SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;

    @InjectPresenter
    SplashPresenter presenter;

    @ProvidePresenter
    SplashPresenter providePresenter() {
        return new SplashPresenter(HoamApplication.getComponent());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);

        presenter.init();
    }

    @Override
    public void showError() {
        Toast.makeText(this, getString(R.string.internet_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading(boolean show) {
        swipeRefreshLayout.setRefreshing(show);
    }

    @Override
    public void goToMainScreen() {
        startActivity(MainActivity.buildIntent(this));
        finish();
    }

    @Override
    public void onRefresh() {
        presenter.fetchCategories();
    }
}
