package com.solvo.hoam.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.widget.Toast;

import com.solvo.hoam.R;
import com.solvo.hoam.presentation.ui.fragment.AboutAppFragment;
import com.solvo.hoam.presentation.ui.fragment.AdListFragment;
import com.solvo.hoam.presentation.ui.fragment.FavoritesFragment;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final long EXIT_TIMEOUT = 2000;
    private static long backPressed;

    private DrawerLayout drawerLayout;

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private Fragment adListFragment;
    private Fragment aboutAppFragment;
    private Fragment favoritesFragment;


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
        adListFragment = AdListFragment.newInstance();
        favoritesFragment = FavoritesFragment.newInstance();
        aboutAppFragment = AboutAppFragment.newInstance();

        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, adListFragment)
                .add(R.id.fragment_container, favoritesFragment)
                .add(R.id.fragment_container, aboutAppFragment)
                .commit();

        fragmentManager.executePendingTransactions();
        fragmentManager.beginTransaction()
                .hide(favoritesFragment)
                .hide(aboutAppFragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (backPressed + EXIT_TIMEOUT > System.currentTimeMillis()) {
                finish();
            } else {
                Toast.makeText(getBaseContext(), R.string.click_again_to_exit, Toast.LENGTH_SHORT).show();
                backPressed = System.currentTimeMillis();
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_ads:
                fragmentManager.beginTransaction()
                        .show(adListFragment)
                        .hide(favoritesFragment)
                        .hide(aboutAppFragment)
                        .commit();
                break;
            case R.id.nav_favorites:
                fragmentManager.beginTransaction()
                        .hide(adListFragment)
                        .show(favoritesFragment)
                        .hide(aboutAppFragment)
                        .commit();
                break;
            case R.id.nav_about_app:
                fragmentManager.beginTransaction()
                        .hide(adListFragment)
                        .hide(favoritesFragment)
                        .show(aboutAppFragment)
                        .commit();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
