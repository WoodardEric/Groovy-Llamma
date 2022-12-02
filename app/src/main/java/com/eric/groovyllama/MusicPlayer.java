package com.eric.groovyllama;

import android.app.Service;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.provider.MediaStore;
import android.util.Log;
import androidx.annotation.Nullable;
import com.eric.groovyllama.model.Song;
import com.google.android.exoplayer2.Player;

import java.util.ArrayList;

public class MusicPlayer extends Service implements
        MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener{
    public MusicPlayer(){

    }
    private final IBinder musicBind = new MusicBinder();
    @Override
    public void onCreate() {
        super.onCreate();
        songPos = 0;
        mediaPlayer = new MediaPlayer();
        initAudioPlayer();
    }

    private void initAudioPlayer() {
        mediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build());
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnErrorListener(this);
        mediaPlayer.setOnPreparedListener(this);
    }
    public void setList(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public void setSong(int songIndex) {
        songPos = songIndex;
    }

    public class MusicBinder extends Binder {
        MusicPlayer getService() {
            return MusicPlayer.this;
        }
    }
    private static final int[] REPEAT_MODES = {
            Player.REPEAT_MODE_OFF, Player.REPEAT_MODE_ONE, Player.REPEAT_MODE_ALL
    };

    MediaPlayer mediaPlayer;
    private ArrayList<Song> songs;
    private int songPos;

    public MusicPlayer(Context context) {
        //mediaPlayer = new ExoPlayer.Builder(context.getApplicationContext()).build();
    }

    public void play() {
        mediaPlayer.reset();
        Uri trackUri = ContentUris.withAppendedId(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, songs.get(songPos).getId());
        try {
            mediaPlayer.setDataSource(getApplicationContext(), trackUri);
        } catch (Exception e) {
            Log.e("Music Service", "Error setting data souce", e);
        }

        mediaPlayer.prepareAsync();
//        if (mediaPlayer.isPlaying())
//        {
//            pause();
//        }
//        else {
//            mediaPlayer.setPlayWhenReady(true);
//        }
    }

    public void pause() {
        mediaPlayer.pause();
    }

//    public void next() {
//        if (mediaPlayer.hasNextMediaItem()){
//            mediaPlayer.seekToNextMediaItem();
//        }
//        else {
//            mediaPlayer.stop();
//        }
//    }
//
//    public void previous() {
//        if (mediaPlayer.hasPreviousMediaItem()) {
//            mediaPlayer.seekToPrevious();
//        }
//        else {
//            mediaPlayer.seekTo(0);
//        }
//    }
//
//    public void toggleLoop() {
//        int repeatMode = mediaPlayer.getRepeatMode();
//        if (repeatMode > 2) {
//            mediaPlayer.setRepeatMode(Player.REPEAT_MODE_OFF);
//        }
//        else
//        {
//            mediaPlayer.setRepeatMode(REPEAT_MODES[repeatMode + 1]);
//        }
//    }

    @Override
    public IBinder onBind(Intent intent) {
        return musicBind;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mediaPlayer.stop();
        mediaPlayer.release();
        return false;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }
}
