package com.ascendant.e_businessprofile.Activity.ui.Healthcare.Compliance;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import com.ascendant.e_businessprofile.R;

public class DetailComplianceActivity extends AppCompatActivity {
    VideoView video;
    ProgressDialog pd;
    private VideoView videoView;
    private MediaController mediaController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_compliance);
        Intent data = getIntent();
        String VIDEO_URL = data.getStringExtra("VIDEO_URL");
        video =findViewById(R.id.videoPlay);
//        WebSettings webSettings = video.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        video.loadData("<iframe src='https://drive.google.com/file/d/1QVFB9x9e9ofOXwoXw4T73BsY0okyj-yu/preview' width='100%' height='100%' style='border: none;'></iframe>","text/html","UTF-8");
//        video=findViewById(R.id.videoPlay);
        pd=new ProgressDialog(this);
        pd.setMessage("Buffering Video Please Wait");
        pd.show();
//        Uri uri = Uri.parse("https://www.googleapis.com/drive/v3/files/1QVFB9x9e9ofOXwoXw4T73BsY0okyj-yu?alt=media&key=AIzaSyC3MT4Pr9MejUMQFzIxdNwB_damaMG2lEg");
        Uri uri = Uri.parse(VIDEO_URL);
        String fullScreen =  getIntent().getStringExtra("fullScreenInd");
        if("y".equals(fullScreen)){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().hide();
        }
        mediaController = new FullScreenMediaController(this);
        mediaController.setAnchorView(video);
        video.setMediaController(mediaController);
        video.setVideoURI(uri);
        video.start();
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                pd.dismiss();
            }
        });
    }
}