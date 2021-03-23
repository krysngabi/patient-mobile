package com.smartit.priis;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.widget.ImageView;



/**
 * This is the launcher activity of the Application
 */
public class SplashActivity extends AppCompatActivity {

    /** Splash screen timer. */
    public static final int SPLASH_TIME_OUT = 800;
    public ImageView mIVTapLinxLogo = null;
    SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food);
        sharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        initializeUI();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                    Intent i = new Intent(getApplicationContext(), Patient.class);
                    startActivity(i);

                    finish();
            }
        }, SPLASH_TIME_OUT);
    }

    /**
     * Initializing the UI thread.
     */
    private void initializeUI() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        mIVTapLinxLogo = (ImageView) findViewById(R.id.imgTapLinx);
        mIVTapLinxLogo.getLayoutParams().width = (size.x);
        mIVTapLinxLogo.getLayoutParams().height = (size.y);
    }
}
