package com.solvo.hoam.presentation.ui.activity;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import com.solvo.hoam.R;

import moxy.MvpAppCompatActivity;

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