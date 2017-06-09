package com.solvo.hoam.presentation.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.solvo.hoam.HoamApplication;
import com.solvo.hoam.R;
import com.solvo.hoam.data.network.response.Ad;
import com.solvo.hoam.presentation.mvp.presenter.HomePresenter;
import com.solvo.hoam.presentation.mvp.view.HomeView;
import com.solvo.hoam.presentation.ui.activity.FilterActivity;
import com.solvo.hoam.presentation.ui.adapter.AdListAdapter;
import com.solvo.hoam.presentation.ui.view.EndlessScrollListener;

import java.util.List;

public class HomeFragment extends MvpAppCompatFragment implements HomeView, SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = HomeFragment.class.getSimpleName();
    private static final int FILTER_REQUEST_CODE = 0;

    private SwipeRefreshLayout swipeRefreshLayout;
    private EndlessScrollListener scrollListener;
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private AdListAdapter adapter;

    @InjectPresenter
    HomePresenter presenter;

    @ProvidePresenter
    HomePresenter providePresenter() {
        return new HomePresenter(HoamApplication.getComponent());
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);

        adapter = new AdListAdapter(getContext());
        layoutManager = new LinearLayoutManager(getContext());
        scrollListener = new EndlessScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.onListScroll(page);
            }
        };

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(scrollListener);

        presenter.init();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_home, menu);

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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter:
                presenter.onFilterClicked();
                return true;
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

        Toast.makeText(getContext(), total, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateAds(List<Ad> adList) {
        adapter.updateAdList(adList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), getString(R.string.internet_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void goToFilter() {
        startActivityForResult(FilterActivity.buildIntent(getContext()), FILTER_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == FILTER_REQUEST_CODE) {
            presenter.onActivityResult();
        }
    }
}
