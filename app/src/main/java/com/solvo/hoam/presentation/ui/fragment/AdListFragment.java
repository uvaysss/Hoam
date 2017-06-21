package com.solvo.hoam.presentation.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.solvo.hoam.HoamApplication;
import com.solvo.hoam.R;
import com.solvo.hoam.data.network.RestService;
import com.solvo.hoam.domain.model.AdEntity;
import com.solvo.hoam.presentation.mvp.presenter.AdListPresenter;
import com.solvo.hoam.presentation.mvp.view.AdListView;
import com.solvo.hoam.presentation.ui.activity.FilterActivity;
import com.solvo.hoam.presentation.ui.adapter.AdListAdapter;
import com.solvo.hoam.presentation.ui.view.EndlessScrollListener;

import java.util.List;

public class AdListFragment extends MvpAppCompatFragment
        implements AdListView, SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = AdListFragment.class.getSimpleName();
    private static final int FILTER_REQUEST_CODE = 0;

    private CoordinatorLayout coordinatorLayout;
    private FloatingActionButton floatingActionButton;
    private SwipeRefreshLayout swipeRefreshLayout;
    private EndlessScrollListener scrollListener;
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private AdListAdapter adapter;

    @InjectPresenter
    AdListPresenter presenter;

    @ProvidePresenter
    AdListPresenter providePresenter() {
        return new AdListPresenter(HoamApplication.getComponent());
    }

    public static AdListFragment newInstance() {
        return new AdListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ad_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle(R.string.ads);

        coordinatorLayout = (CoordinatorLayout) getActivity().findViewById(R.id.coordinator_layout);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(v -> {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(RestService.HOAM_NEW_AD)));
        });
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
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            getActivity().setTitle(R.string.ads);
        }
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
    public void setAds(List<AdEntity> adList) {
        scrollListener.resetState();
        adapter.setAdList(adList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void updateAds(List<AdEntity> adList) {
        adapter.updateAdList(adList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        Snackbar.make(coordinatorLayout, R.string.internet_error, Snackbar.LENGTH_LONG).show();
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
