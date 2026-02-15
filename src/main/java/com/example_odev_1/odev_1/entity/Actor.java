package com.example_odev_1.odev_1.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="actors")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;
    @Column(name="overview")
    private String overview;
    @Column(name="image_link")
    private String imageLink;

    @ManyToMany(mappedBy="actors")
    private List<Movie> movies=new ArrayList<Movie>();




    public Actor() {
    }

    public Actor(int id, String name, String overview, String imageLink) {
        this.id = id;
        this.name = name;
        this.overview = overview;
        this.imageLink = imageLink;
    }


    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
