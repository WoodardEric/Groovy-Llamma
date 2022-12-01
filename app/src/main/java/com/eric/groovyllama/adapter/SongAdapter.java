package com.eric.groovyllama.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.eric.groovyllama.R;
import com.eric.groovyllama.model.Song;

import java.util.ArrayList;

public class SongAdapter extends BaseAdapter {
    private ArrayList<Song> songs;
    private LayoutInflater songInflator;

    public SongAdapter(Context c, ArrayList<Song> songs) {
        this.songs = songs;
        songInflator = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return songs.size();
    }

    @Override
    public Object getItem(int i) {
        return songs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LinearLayout songLay = (LinearLayout) songInflator.inflate(R.layout.song, viewGroup, false);
        TextView songView = songLay.findViewById(R.id.song_title);
        TextView artistView = songLay.findViewById(R.id.song_artist);
        Song currSong = songs.get(i);
        songView.setText(currSong.getTitle());
        artistView.setText(currSong.getArtist());
        songLay.setTag(i);
        return songLay;
    }
}
