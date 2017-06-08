package com.solvo.hoam.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.solvo.hoam.BuildConfig;
import com.solvo.hoam.R;
import com.solvo.hoam.rest.RestService;

public class AboutActivity extends BaseActivity {
    private static final String TAG = AboutActivity.class.getSimpleName();

    private TextView mAppVersionTextView;
    private TextView mUrlTextView;

    public static Intent buildIntent(Context context) {
        return new Intent(context, AboutActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initToolbar(true);

        mAppVersionTextView = (TextView) findViewById(R.id.tv_app_version);
        mAppVersionTextView.setText(BuildConfig.VERSION_NAME);

        mUrlTextView = (TextView) findViewById(R.id.tv_url);
        mUrlTextView.setText(RestService.HOAM_URL);
        mUrlTextView.setOnClickListener(v -> {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(RestService.HOAM_URL)));
        });
    }
}
