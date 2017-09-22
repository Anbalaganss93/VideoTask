package com.testsample;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.halilibo.bettervideoplayer.BetterVideoCallback;
import com.halilibo.bettervideoplayer.BetterVideoPlayer;
import com.halilibo.bettervideoplayer.subtitle.CaptionsView;

public class MyPlayerActivity extends AppCompatActivity {


    private BetterVideoPlayer bvp;
    private String TAG = "BetterSample";
    private Typeface regular;
    private TextView mToolbarTitle;
    private DisplayMetrics metrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_player);
        init();
        setFontTypeface();

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        bvp = (BetterVideoPlayer) findViewById(R.id.bvp);

        if (savedInstanceState == null) {
            bvp.setAutoPlay(true);
            bvp.setSource(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video));
            bvp.setCaptions(R.raw.sub, CaptionsView.CMime.SUBRIP);
        }
        bvp.setHideControlsOnPlay(true);
        bvp.enableSwipeGestures(getWindow());

        bvp.setCallback(new BetterVideoCallback() {
            @Override
            public void onStarted(BetterVideoPlayer player) {
                //Log.i(TAG, "Started");
            }

            @Override
            public void onPaused(BetterVideoPlayer player) {
                //Log.i(TAG, "Paused");
            }

            @Override
            public void onPreparing(BetterVideoPlayer player) {
                //Log.i(TAG, "Preparing");
            }

            @Override
            public void onPrepared(BetterVideoPlayer player) {
                //Log.i(TAG, "Prepared");
            }

            @Override
            public void onBuffering(int percent) {
                //Log.i(TAG, "Buffering " + percent);
            }

            @Override
            public void onError(BetterVideoPlayer player, Exception e) {
                //Log.i(TAG, "Error " +e.getMessage());
            }

            @Override
            public void onCompletion(BetterVideoPlayer player) {
                //Log.i(TAG, "Completed");
            }

            @Override
            public void onToggleControls(BetterVideoPlayer player, boolean isShowing) {

            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.width = (int) (metrics.widthPixels);
            params.height = (int) (metrics.heightPixels * 0.5);
            bvp.setLayoutParams(params);
        }
    }

    private void setFontTypeface() {
        mToolbarTitle.setTypeface(regular);
    }

    private void init() {
        regular = Typeface.createFromAsset(getAssets(), "fonts/proximanova-reg.ttf");
        mToolbarTitle = (TextView) findViewById(R.id.activity_main_toolbar_name);
    }

    @Override
    public void onPause() {
        bvp.pause();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent videointent = new Intent(MyPlayerActivity.this, MainActivity.class);
        startActivity(videointent);
        finish();
    }
}
