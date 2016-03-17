package com.hicollege.webapp.dtos;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "users", uniqueConstraints= @UniqueConstraint(columnNames = {"name"}))
public class User {
    
    private int id;
    private String name;
    private String age;
    private String email;
    private List<Album> albums;
    
    public User() {}
    
    public User(String name, String age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
    
    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Column(name = "name", unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name = "age")
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
    
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    @OneToMany
    @JoinColumn(name="id")
    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

}