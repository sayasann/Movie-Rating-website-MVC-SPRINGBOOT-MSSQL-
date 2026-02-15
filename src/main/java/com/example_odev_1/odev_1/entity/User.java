package com.example_odev_1.odev_1.entity;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@Entity
@Table(name = "Users")

public class User {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userID;

	@Column(nullable = false , unique = true)
	@NotBlank(message = "Name Cannot Be Empty")
	@Size(min = 2 ,max = 20,message = "Name Size Should Be Between 2-20")
	private String userName;

	@Column(nullable = false)
	@NotBlank(message = "Email Cannot Be Empty")
	@Email(message = "Invalid Email Format")
	private String userEmail;

	@Column(nullable = false)
	@NotBlank(message = "Password Cannot Be Empty")
	@Size(min = 3 ,max = 15,message = "Password Size Should Be Between 3-15")
	private String userPassword;

	@Column(nullable = false)
	@NotNull(message = "Birthdate Cannot Be Empty")
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Past(message = "Birthdate must be in the past")
	private Date userBirthDate;
	
	@AssertTrue(message = "You must be at least 18 years old")
	public boolean isAdult() {
	    if (userBirthDate == null) return false;
	    return userBirthDate.toLocalDate()
	            .plusYears(18)
	            .isBefore(LocalDate.now().plusDays(1));
	}

	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Review> reviews=new ArrayList<>();

	// MOVIE TARAFI OWNER TARAF controller ve service yazılmalı, gerekirse daha
	// hızlı erisim icin rewiew mvc de yazılabilir
	@ManyToMany
	@JoinTable(name = "user_favorite", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "movie_id"))
	private List<Movie> favoriteMovies=new ArrayList<>();
	
	@ManyToMany
    @JoinTable(
        name = "user_watch_list",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<Movie> watchList = new ArrayList<>();
	
	@ManyToMany
    @JoinTable(
        name = "user_watched_movies",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<Movie> watchedMovies = new ArrayList<>();

	public User() {
	}

	public User(Integer userID, String userName, String userEmail, String userPassword, Date userBirthDate) {
		this.userID = userID;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userBirthDate = userBirthDate;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Date getUserBirthDate() {
		return userBirthDate;
	}

	public void setUserBirthDate(Date userBirthDate) {
		this.userBirthDate = userBirthDate;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<Movie> getFavoriteMovies() {
		return favoriteMovies;
	}
	public void setFavoriteMovies(List<Movie> favoriteMovies) {
		this.favoriteMovies = favoriteMovies;
	}
	public List<Movie> getWatchList() {
		return watchList;
	}
	public void setWatchList(List<Movie> watchList) {
		this.watchList = watchList;
	}
	public List<Movie> getWatchedMovies() {
		return watchedMovies;
	}
	public void setWatchedMovies(List<Movie> watchedMovies) {
		this.watchedMovies = watchedMovies;
	}


	
}
