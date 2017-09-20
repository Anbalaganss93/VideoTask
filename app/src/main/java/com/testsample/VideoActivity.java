package com.testsample;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.TimedText;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import com.testsample.adapter.VideoAdapter;
import com.testsample.dto.Modelurl;

import java.util.ArrayList;

public class VideoActivity extends AppCompatActivity implements SurfaceHolder.Callback, MediaPlayer.OnPreparedListener {
   /* RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    VideoAdapter vAdapter;
    ArrayList<Modelurl> arrayList = new ArrayList<>();
    public static int scrollstatus=0;*/

    MediaPlayer mediaPlayer;
    SurfaceHolder surfaceHolder;
    SurfaceView playerSurfaceView;
    TextView tv_subtitle;
    String videoSrc = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"; //Environment.getExternalStorageDirectory().getPath() + "/video.mp4";
    String subTitleSrc = Environment.getExternalStorageDirectory().getPath() + "/sub.srt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_video);
        playerSurfaceView = (SurfaceView)findViewById(R.id.playersurface);
        tv_subtitle = (TextView)findViewById(R.id.tv_subtitle);
        surfaceHolder = playerSurfaceView.getHolder();
        surfaceHolder.addCallback(VideoActivity.this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void surfaceCreated(SurfaceHolder arg0) {

        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDisplay(surfaceHolder);
            mediaPlayer.setDataSource(videoSrc);
            mediaPlayer.setOnPreparedListener(VideoActivity.this);
            mediaPlayer.prepare();

            mediaPlayer.addTimedTextSource(subTitleSrc, MediaPlayer.MEDIA_MIMETYPE_TEXT_SUBRIP);
            int textTrackIndex = findTrackIndexFor(
                    MediaPlayer.TrackInfo.MEDIA_TRACK_TYPE_TIMEDTEXT, mediaPlayer.getTrackInfo());
            if (textTrackIndex >= 0) {
                mediaPlayer.selectTrack(textTrackIndex);
            } else {
                Log.w("test", "Cannot find text track!");
            }

            mediaPlayer.setOnTimedTextListener(new MediaPlayer.OnTimedTextListener() {
                @Override
                public void onTimedText(final MediaPlayer mediaPlayer, final TimedText timedText) {
                    if (timedText != null) {
                        Log.d("test", "subtitle: " + timedText.getText());
                    }
                }
            });

            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        } catch (Exception e) {
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private int findTrackIndexFor(int mediaTrackType, MediaPlayer.TrackInfo[] trackInfo) {
        int index = -1;
        for (int i = 0; i < trackInfo.length; i++) {
            if (trackInfo[i].getTrackType() == mediaTrackType) {
                return i;
            }
        }
        return index;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mediaPlayer.start();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
  /*  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        init();
    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclers);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        prevideourls();
        vAdapter = new VideoAdapter(VideoActivity.this, arrayList,recyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(vAdapter);
    }

    private void prevideourls() {
        Modelurl vlin = new Modelurl("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4", "0");
        arrayList.add(vlin);
        vlin = new Modelurl("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4", "0");
        arrayList.add(vlin);
        vlin = new Modelurl("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4", "0");
        arrayList.add(vlin);
*//*
        vlin = new Modelurl("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4", "0");
        arrayList.add(vlin);

        vlin = new Modelurl("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4", "0");
        arrayList.add(vlin);

        vlin = new Modelurl("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4", "0");
        arrayList.add(vlin);

        vlin = new Modelurl("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4", "0");
        arrayList.add(vlin);*//*
    }*/
}
