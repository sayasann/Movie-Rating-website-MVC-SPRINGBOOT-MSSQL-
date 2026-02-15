package com.example_odev_1.odev_1.entity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "series_title", nullable = false)
    public String title;

    @Column(name = "released_year")
    public Integer releaseYear;

    @Column(name = "certificate")
    public String certificate;

    @Column(name = "runtime_min")
    public Integer duration;


    @Column(name = "imdb_rating")
    public Double imdbRating;

    @Column(name = "meta_score")
    public Double metaScore;

    @Column(name = "no_of_votes")
    public Long noOfVotes;

    @Column(name = "gross")
    public Long gross;

    @Column(name = "director")
    public String director;


    @Column(name = "poster_link", length = 1000)
    public String posterLink;

    @Column(name = "overview", length = 2000)
    public String overview;


    //MOVIE TARAFI OWNER TARAF YANI ONUN SERVICE VE CONTROLLERI YAZILMALI actorun değil
    @ManyToMany
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    public List<Actor> actors=new ArrayList<>();
    @OneToMany(mappedBy="movie",fetch= FetchType.EAGER,cascade = CascadeType.ALL)
    public List<Review> reviews=new ArrayList<>();
    @ManyToMany(mappedBy = "favoriteMovies")
    public List<User> likedByUsers=new ArrayList<>();

    @ManyToMany()
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    List<Genre> genres = new ArrayList<>();
    
    @ManyToMany(mappedBy = "watchedMovies")
    private List<User> watchedUsers;
    


    public Movie() {
    }

    public Movie(String title, Integer releaseYear, String certificate, Integer duration,
                 String genre, Double imdbRating, Double metaScore, Long noOfVotes,
                 Long gross, String director, String star1, String star2,
                 String star3, String star4, String posterLink, String overview) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.certificate = certificate;
        this.duration = duration;

        this.imdbRating = imdbRating;
        this.metaScore = metaScore;
        this.noOfVotes = noOfVotes;
        this.gross = gross;
        this.director = director;

        this.posterLink = posterLink;
        this.overview = overview;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<User> getLikedByUsers() {
        return likedByUsers;
    }

    public void setLikedByUsers(List<User> likedByUsers) {
        this.likedByUsers = likedByUsers;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public Long getId() {
		return id;
	}

    public Long getNoOfVotes() {
        return noOfVotes;
    }

    public void setNoOfVotes(Long noOfVotes) {
        this.noOfVotes = noOfVotes;
    }

    public Long getGross() {
        return gross;
    }

    public void setGross(Long gross) {
        this.gross = gross;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
















    public String getPosterLink() {
        return posterLink;
    }

    public void setPosterLink(String posterLink) {
        this.posterLink = posterLink;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }





    public Double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(Double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public Double getMetaScore() {
        return metaScore;
    }

    public void setMetaScore(Double metaScore) {
        this.metaScore = metaScore;
    }

}
