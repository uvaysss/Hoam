package com.solvo.hoam.presentation.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.solvo.hoam.HoamApplication;
import com.solvo.hoam.R;
import com.solvo.hoam.domain.model.AdEntity;
import com.solvo.hoam.presentation.mvp.presenter.FavoritesPresenter;
import com.solvo.hoam.presentation.mvp.view.FavoritesView;
import com.solvo.hoam.presentation.ui.adapter.AdListAdapter;
import com.solvo.hoam.presentation.ui.helper.AdHelper;

import java.util.List;

public class FavoritesFragment extends MvpAppCompatFragment implements FavoritesView {

    private RecyclerView recyclerView;
    private TextView noDataTextView;

    @InjectPresenter
    FavoritesPresenter presenter;

    @ProvidePresenter
    FavoritesPresenter providePresenter() {
        return new FavoritesPresenter(HoamApplication.getComponent());
    }

    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        noDataTextView = (TextView) view.findViewById(R.id.tv_no_data);
        noDataTextView.setCompoundDrawables(null, AdHelper.getSupportDrawable(R.drawable.ic_no_favorites, getContext()), null, null);

        presenter.init();
    }

    @Override
    public void setAdList(List<AdEntity> adList) {
        recyclerView.setAdapter(new AdListAdapter(adList, getContext()));

        if (adList.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            noDataTextView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            noDataTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.init();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            getActivity().setTitle(R.string.favorites);
            presenter.init();
        }
    }
}
