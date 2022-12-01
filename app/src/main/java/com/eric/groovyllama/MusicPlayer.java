package com.eric.groovyllama;

import android.content.Context;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;

public class MusicPlayer {
    private static final int[] REPEAT_MODES = {
            Player.REPEAT_MODE_OFF, Player.REPEAT_MODE_ONE, Player.REPEAT_MODE_ALL
    };

    ExoPlayer mediaPlayer;

    public MusicPlayer(Context context) {
        mediaPlayer = new ExoPlayer.Builder(context.getApplicationContext()).build();
    }

    public void play() {
        if (mediaPlayer.isPlaying())
        {
            pause();
        }
        else {
            mediaPlayer.setPlayWhenReady(true);
        }
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public void next() {
        if (mediaPlayer.hasNextMediaItem()){
            mediaPlayer.seekToNextMediaItem();
        }
        else {
            mediaPlayer.stop();
        }
    }

    public void previous() {
        if (mediaPlayer.hasPreviousMediaItem()) {
            mediaPlayer.seekToPrevious();
        }
        else {
            mediaPlayer.seekTo(0);
        }
    }

    public void toggleLoop() {
        int repeatMode = mediaPlayer.getRepeatMode();
        if (repeatMode > 2) {
            mediaPlayer.setRepeatMode(Player.REPEAT_MODE_OFF);
        }
        else
        {
            mediaPlayer.setRepeatMode(REPEAT_MODES[repeatMode + 1]);
        }
    }
}
