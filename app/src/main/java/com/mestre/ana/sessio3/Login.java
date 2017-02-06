package com.mestre.ana.sessio3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.mestre.ana.sessio3.DB.User;
import com.mestre.ana.sessio3.DB.UserData;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.*;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.identity.*;
import com.twitter.sdk.android.core.internal.TwitterApi;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Ana on 01/02/2017.
 */

public class Login extends Activity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "aCqrloIHCuvw8rX8sON8B551T";
    private static final String TWITTER_SECRET = "CkkLBIClzyC2w00oHOateou4wxJ2IberPKXWR8WpQEi0T5Gh2s";
    private TwitterLoginButton loginButton;
    private static final String PREFS_NAME = "User_sharedPreferences";

    private UserData db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        boolean logIn = settings.getBoolean("logIn", false);
        String username = settings.getString("username", "");

        if(logIn) userLogIn(username);
        else {
            TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
            Fabric.with(this, new Twitter(authConfig));
            setContentView(R.layout.login_layout);

            db = new UserData(this);
            db.open();

            loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
            loginButton.setCallback(new Callback<TwitterSession>() {
                @Override
                public void success(Result<TwitterSession> result) {
                    // The TwitterSession is also available through:
                    // Twitter.getInstance().core.getSessionManager().getActiveSession()
                    TwitterSession session = result.data;
                    String username = session.getUserName();
                    User user = db.getUser(username);

                    SharedPreferences preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = preferences.edit();
                    edit.putBoolean("logIn", true);
                    edit.putString("username", username);
                    edit.apply();

                    if (user != null) userLogIn(username);
                    else {
                        db.createUser(username);
                        userLogIn(username);
                    }

                }

                @Override
                public void failure(TwitterException exception) {
                    Log.d("TwitterKit", "Login with Twitter failure", exception);
                }
            });
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }

    public void userLogIn(String username){
        Intent intent = new Intent(this, BaseActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

}
