package com.hicollege.webapp.dtos;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    
    private int id;
    private String name;
    private String age;
    private String email;
//    private List<Album> albums;
    
    public User(int id, String name, String age, String email) {
        this.id = id;
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
    
    @Column(name = "name")
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

//    public List<Album> getAlbums() {
//        return albums;
//    }
//
//    public void setAlbums(List<Album> albums) {
//        this.albums = albums;
//    }

}