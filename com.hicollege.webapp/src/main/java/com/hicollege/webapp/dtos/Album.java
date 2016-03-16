package com.hicollege.webapp.dtos;

import java.util.List;

public class Album {
    
    private List<Song> songs;
    private List<Artist> artists;
    private String title;
    
    
    public List<Song> getSongs() {
        return songs;
    }
    
    
    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
    
    
    public List<Artist> getArtists() {
        return artists;
    }
    
    
    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
    
    
    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((artists == null) ? 0 : artists.hashCode());
        result = prime * result + ((songs == null) ? 0 : songs.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Album))
            return false;
        Album other = (Album)obj;
        if (artists == null) {
            if (other.artists != null)
                return false;
        } else if (!artists.equals(other.artists))
            return false;
        if (songs == null) {
            if (other.songs != null)
                return false;
        } else if (!songs.equals(other.songs))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }
}
