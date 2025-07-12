package com.application.timer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

     VideoView splashVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        );

        setContentView(R.layout.activity_splash);

        splashVideo = findViewById(R.id.splashVideoView);

        // Set video URI
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splash);
        splashVideo.setVideoURI(videoUri);

        splashVideo.setOnCompletionListener(mp -> {
            // After video ends, go to MainActivity
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish(); // close splash activity
        });
        splashVideo.start(); // play the video

        splashVideo.setOnErrorListener((mp, what, extra) -> {
            Toast.makeText(this, "Error Playing Video", Toast.LENGTH_SHORT).show();
            return true;
        });
    }
}
