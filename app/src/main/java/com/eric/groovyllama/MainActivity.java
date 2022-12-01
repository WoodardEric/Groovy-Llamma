package com.eric.groovyllama;

import android.media.MediaPlayer;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = MediaPlayer.create(this, R.raw.music1);
    }

    public void playSong(View v) {
        player.start();
    }

    public void pauseSong(View v) {
        player.pause();
    }

    public void stopSong(View v) {
        player.stop();
        player = MediaPlayer.create(this, R.raw.music1);
    }
}
