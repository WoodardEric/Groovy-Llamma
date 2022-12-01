package com.eric.groovyllama.model;

public class Song {
    private long id;
    private String title;
    private String artist;
    private String album;

    public Song(long id, String title, String artist, String album) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
    }

    public long getId() {return id;}
    public String getTitle() {return title;}
    public String getArtist() {return title;}
    public String getAlbum() {return album;}
}
