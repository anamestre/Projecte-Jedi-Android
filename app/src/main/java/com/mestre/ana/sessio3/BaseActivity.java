package com.mestre.ana.sessio3;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mestre.ana.sessio3.DB.UserData;

public class BaseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private Fragment fragment;
    private UserData usdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        iniView(savedInstanceState);

         // Coses de relleno de Android.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outstate){
        super.onSaveInstanceState(outstate);
        getSupportFragmentManager().putFragment(outstate, "fragment", fragment);
    }

    public void transaction(){
        android.support.v4.app.FragmentTransaction FragmentTransaction;
        FragmentTransaction = getSupportFragmentManager().beginTransaction();
        FragmentTransaction.replace(R.id.fragment_container, fragment);
        FragmentTransaction.commit();
    }

    public void iniView(Bundle savedInstancesState){

        if(savedInstancesState != null) {
            fragment = getSupportFragmentManager().getFragment(savedInstancesState, "fragment");
        }
        else {
            Calculator calculator = new Calculator();
            fragment = calculator;
        }

        transaction();

        // Ini Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = (String) getResources().getString(R.string.calculator);
        toolbar.setTitle(title);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.nav_calculator:
                Calculator calculator = new Calculator();
                fragment = calculator;
                transaction();
                break;
            case R.id.nav_profile:
                Profile profile = new Profile();
                fragment = profile;
                transaction();
                break;
            case R.id.nav_music:
                MusicPlayer music = new MusicPlayer();
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                fragment = music;
                transaction();
                break;
            case R.id.nav_ranking:
                Ranking ran = new Ranking();
                fragment = ran;
                transaction();
                break;
            case R.id.nav_memory:
                Memory mem = new Memory();
                fragment = mem;
                transaction();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
