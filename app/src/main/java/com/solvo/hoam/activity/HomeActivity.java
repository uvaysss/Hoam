package com.solvo.hoam.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.solvo.hoam.R;
import com.solvo.hoam.adapter.AdListAdapter;
import com.solvo.hoam.model.response.Ad;
import com.solvo.hoam.presenter.HomePresenter;
import com.solvo.hoam.view.HomeView;
import com.solvo.hoam.view.listener.EndlessScrollListener;

import java.util.List;

public class HomeActivity extends BaseActivity implements HomeView, SwipeRefreshLayout.OnRefreshListener {
    public static final String TAG = HomeActivity.class.getSimpleName();
    private static final int FILTER_REQUEST_CODE = 0;

    private SwipeRefreshLayout swipeRefreshLayout;
    private EndlessScrollListener scrollListener;
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private AdListAdapter adapter;
    private HomePresenter presenter;

    public static Intent buildIntent(Context context) {
        return new Intent(context, HomeActivity.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initToolbar(false);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);

        adapter = new AdListAdapter(this);
        layoutManager = new LinearLayoutManager(this);
        scrollListener = new EndlessScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.onListScroll(page);
            }
        };

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(scrollListener);

        presenter = new HomePresenter(this, this);
        presenter.init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.onSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter:
                presenter.onFilterClicked();
                return true;
            case R.id.action_about:
                presenter.onAboutAppClicked();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRefresh() {
        presenter.onRefresh();
    }

    @Override
    public void showLoading(boolean show) {
        swipeRefreshLayout.setRefreshing(show);
    }

    @Override
    public void setAds(List<Ad> adList, String total) {
        scrollListener.resetState();
        adapter.setAdList(adList);
        adapter.notifyDataSetChanged();

        Toast.makeText(this, total, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateAds(List<Ad> adList) {
        adapter.updateAdList(adList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        Toast.makeText(this, getString(R.string.internet_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void goToFilter() {
        startActivityForResult(FilterActivity.buildIntent(this), FILTER_REQUEST_CODE);
    }

    @Override
    public void goToAboutApp() {
        startActivity(AboutActivity.buildIntent(this));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == FILTER_REQUEST_CODE) {
            presenter.onActivityResult();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }
}
