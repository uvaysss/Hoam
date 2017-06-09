package com.solvo.hoam.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import com.solvo.hoam.R;
import com.solvo.hoam.presentation.ui.fragment.AboutAppFragment;
import com.solvo.hoam.presentation.ui.fragment.HomeFragment;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private FragmentManager fragmentManager = getSupportFragmentManager();

    public static Intent buildIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar(false);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, getToolbar(),
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_ads);

        initFragments();
    }

    private void initFragments() {
        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, HomeFragment.newInstance(), HomeFragment.TAG)
                .addToBackStack(HomeFragment.TAG)
                .add(R.id.fragment_container, AboutAppFragment.newInstance(), AboutAppFragment.TAG)
                .addToBackStack(AboutAppFragment.TAG)
                .commit();

        fragmentManager.executePendingTransactions();

        fragmentManager.beginTransaction()
                .hide(fragmentManager.findFragmentByTag(AboutAppFragment.TAG))
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            finish();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_ads:
                fragmentManager.beginTransaction()
                        .show(fragmentManager.findFragmentByTag(HomeFragment.TAG))
                        .hide(fragmentManager.findFragmentByTag(AboutAppFragment.TAG))
                        .commit();
                break;
            case R.id.nav_favorites:
                // Todo go to favorites
                break;
            case R.id.nav_about_app:
                fragmentManager.beginTransaction()
                        .show(fragmentManager.findFragmentByTag(AboutAppFragment.TAG))
                        .hide(fragmentManager.findFragmentByTag(HomeFragment.TAG))
                        .commit();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
