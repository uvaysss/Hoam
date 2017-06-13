package com.solvo.hoam.presentation.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.solvo.hoam.HoamApplication;
import com.solvo.hoam.R;
import com.solvo.hoam.presentation.mvp.presenter.SplashPresenter;
import com.solvo.hoam.presentation.mvp.view.SplashView;
import com.solvo.hoam.presentation.ui.fragment.ConnectionFragment;

public class SplashActivity extends MvpAppCompatActivity implements SplashView {

    private SwipeRefreshLayout swipeRefreshLayout;
    private FragmentManager fragmentManager = getSupportFragmentManager();

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

        presenter.fetchCategories();

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
    public void showError() {
        fragmentManager.beginTransaction()
                .show(fragmentManager.findFragmentByTag(ConnectionFragment.TAG))
                .commit();
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
}
