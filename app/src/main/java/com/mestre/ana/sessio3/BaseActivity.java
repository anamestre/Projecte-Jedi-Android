package com.mestre.ana.sessio3;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mestre.ana.sessio3.DB.User;
import com.mestre.ana.sessio3.DB.UserData;
import com.mestre.ana.sessio3.MusicPlayer.MusicController;
import com.mestre.ana.sessio3.MusicPlayer.MusicPlayer;
import com.mestre.ana.sessio3.MusicPlayer.MusicPlayerService;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;

public class BaseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "aCqrloIHCuvw8rX8sON8B551T";
    private static final String TWITTER_SECRET = "CkkLBIClzyC2w00oHOateou4wxJ2IberPKXWR8WpQEi0T5Gh2s";
    private static final String PREFS_NAME = "User_sharedPreferences";


    private Toolbar toolbar;
    private Fragment fragment;
    private UserData usdata;
    private TextView nav_username;
    private ImageView nav_image;
    private NavigationView nav;
    public String username;
    private User currentUser;
    private Boolean logIn;
    private MusicController musicController;

    private MenuItem toast;
    private MenuItem state;

    public MusicController getMusicController(){
        return this.musicController;
    }


    public String getUsername() {
        return username;
    }

    public User getCurrentUser(){
        return currentUser;
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
                requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }
    }

    public void iniNavigationUser(){
        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        logIn = true;

        usdata = new UserData(this);
        usdata.open();
        currentUser = usdata.getUser(username);
        usdata.close();

        nav = (NavigationView) findViewById(R.id.nav_view);
        nav_username = (TextView) nav.getHeaderView(0).findViewById(R.id.username);
        nav_username.setText(username);

        nav_image = (ImageView) nav.getHeaderView(0).findViewById(R.id.profile_picture);
        if(currentUser.getId_photo() == null)
            Picasso.with(this).load(R.drawable.avatar).resize(400,400).into(nav_image);
        else
            Picasso.with(this).load(currentUser.getId_photo()).resize(400,400).centerCrop().into(nav_image);


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

        musicController = new MusicController(this);

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
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        toast = menu.findItem(R.id.action_toasts);
        state = menu.findItem(R.id.action_notificacio);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //int id = item.getItemId();
        //refreshSharedPreferences();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.nav_calculator:
                musicController.setVisibility(View.INVISIBLE);
                Calculator calculator = new Calculator();
                fragment = calculator;
                transaction();
                break;
            case R.id.nav_profile:
                musicController.setVisibility(View.INVISIBLE);
                Profile profile = new Profile();
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                fragment = profile;
                transaction();
                break;
            case R.id.nav_music:
                musicController.setVisibility(View.VISIBLE);
                MusicPlayer music = new MusicPlayer();
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                fragment = music;
                transaction();
                break;
            case R.id.nav_ranking:
                musicController.setVisibility(View.INVISIBLE);
                Ranking ran = new Ranking();
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                fragment = ran;
                transaction();
                break;
            case R.id.nav_memory:
                musicController.setVisibility(View.INVISIBLE);
                Memory mem = new Memory();
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                fragment = mem;
                transaction();
                break;
            case R.id.nav_log_out:
                musicController.setVisibility(View.INVISIBLE);
                logIn = false;
                SharedPreferences preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = preferences.edit();
                edit.putBoolean("logIn", false);
                edit.apply();

                Intent intent = new Intent(this, Login.class);
                startActivity(intent);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void songPicked(View v) {
        MusicPlayer mp = (MusicPlayer) fragment;
        mp.songPicked(v);
    }


}
