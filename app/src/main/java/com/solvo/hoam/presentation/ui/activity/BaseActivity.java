package com.solvo.hoam.presentation.ui.activity;

import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.solvo.hoam.R;

public class BaseActivity extends MvpAppCompatActivity {

    private Toolbar toolbar;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    protected void initToolbar(boolean addBackButton) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        if (addBackButton && getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    protected Toolbar getToolbar() {
        return toolbar;
    }
}