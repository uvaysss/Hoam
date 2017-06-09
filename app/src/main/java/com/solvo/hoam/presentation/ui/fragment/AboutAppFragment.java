package com.solvo.hoam.presentation.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.solvo.hoam.BuildConfig;
import com.solvo.hoam.R;
import com.solvo.hoam.data.network.RestService;

public class AboutAppFragment extends Fragment {

    public static final String TAG = AboutAppFragment.class.getSimpleName();

    public static AboutAppFragment newInstance() {
        return new AboutAppFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_about, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView appVersionTextView = (TextView) view.findViewById(R.id.tv_app_version);
        appVersionTextView.setText(BuildConfig.VERSION_NAME);

        TextView urlTextView = (TextView) view.findViewById(R.id.tv_url);
        urlTextView.setText(RestService.HOAM_URL);
        urlTextView.setOnClickListener(v -> {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(RestService.HOAM_URL)));
        });
    }
}
