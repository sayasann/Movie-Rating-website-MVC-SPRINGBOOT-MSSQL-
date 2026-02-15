package com.example_odev_1.odev_1.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name="reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="review")
    private String review;
    @Column(name="rating")
    private Integer rating;
    @Column(name="date")
    private Date date;

    @Column(name="is_spoiler")
    private Boolean spoiler;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_userid", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;


    public Review() {
    }


    public Review(String review, Integer rating, Boolean spoiler) {
        this.review = review;
        this.rating = rating;

        this.spoiler = spoiler;
    }

    public Boolean getIsSpoiler() {
        return spoiler;
    }

    public void setIsSpoiler(Boolean spoiler) {
        this.spoiler = spoiler;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
