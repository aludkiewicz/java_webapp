package com.hicollege.webapp.dtos;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "albums", uniqueConstraints = @UniqueConstraint(columnNames = { "title" }) )
public class Album {
    
    private int id;
    private List<String> songs;
    private List<String> artists;
    private String title;
    private Set<User> users = new HashSet<>();;


    @Id
    @GeneratedValue
    @Column(name = "album_id")
    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="songs", joinColumns=@JoinColumn(name="album_id"))
    @Column(name="songs")
    public List<String> getSongs() {
        return songs;
    }
    
    
    public void setSongs(List<String> songs) {
        this.songs = songs;
    }
    
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="artists", joinColumns=@JoinColumn(name="album_id"))
    @Column(name="artist")
    public List<String> getArtists() {
        return artists;
    }
    
    
    public void setArtists(List<String> artists) {
        this.artists = artists;
    }
    
    @Column(name = "title")
    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }
    
    
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "albums", cascade = CascadeType.PERSIST)
    @JsonIgnore
    public Set<User> getUsers() {
        return users;
    }


    public void setUsers(Set<User> users) {
        this.users = users;
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
