package com.mwc.mytwitterdigit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.Digits;
import com.digits.sdk.android.DigitsAuthButton;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "DaoGOxzNkwroRnHQVtj7V0SQ7";
    private static final String TWITTER_SECRET = "Sr4x4KGxzSS5uT453CDvuCDCgy0STIhcgsLaty5gCwlJAgegX3";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig), new Digits());
        setContentView(R.layout.activity_main);

        try {
            DigitsAuthButton digitsButton = (DigitsAuthButton) findViewById(R.id.auth_button);
            //digitsButton.setCallback(callback);
            digitsButton.setAuthTheme(android.R.style.Theme_Material);

            digitsButton.setCallback(new AuthCallback() {
                @Override
                public void success(DigitsSession session, String phoneNumber) {
                    // TODO: associate the session userID with your user model
                    Toast.makeText(getApplicationContext(), "Authentication successful for "
                            + phoneNumber, Toast.LENGTH_LONG).show();
                }

                @Override
                public void failure(DigitsException exception) {
                    Log.d("Digits", "Sign in with Digits failure", exception);
                }
            });

        }catch (Exception e)
        {
            e.printStackTrace();;
        }

    }
}
