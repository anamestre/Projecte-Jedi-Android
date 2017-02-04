package com.mestre.ana.sessio3;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.mestre.ana.sessio3.DB.UserData;
import com.mestre.ana.sessio3.MusicPlayer.MusicPlayer;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;

public class BaseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "aCqrloIHCuvw8rX8sON8B551T";
    private static final String TWITTER_SECRET = "CkkLBIClzyC2w00oHOateou4wxJ2IberPKXWR8WpQEi0T5Gh2s";


    private Toolbar toolbar;
    private Fragment fragment;
    private UserData usdata;
    private TextView nav_username;
    private NavigationView nav;
    public static String username;


    public static String getUsername() {
        return username;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_base);

        iniView(savedInstanceState);

        iniNavigationUser();
        askPermissions();

         // Coses de relleno de Android.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void askPermissions(){
        if(Build.VERSION.SDK_INT >= 23){
            int hasExternalStoragePermission = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            if (hasExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            }
        }
    }

    public void iniNavigationUser(){
        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        nav = (NavigationView) findViewById(R.id.nav_view);
        nav_username = (TextView) nav.getHeaderView(0).findViewById(R.id.username);
        nav_username.setText(username);
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
