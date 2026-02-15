package com.example_odev_1.odev_1.Service;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.List;

import com.example_odev_1.odev_1.entity.Movie;
import com.example_odev_1.odev_1.entity.User;

public interface IUserService {
	
	public User registration(User newUser);
	public User login(String userName,String password);
	public void deleteAccount(Integer userID,String password);
	public List<Movie> favoriteMovies(User user, Long movieId);
	public User findById(Integer id);
	public List<Movie> watchList(User user,Long movieId);
	public List<Movie> watchedMovies(User user,Long movieId);
	public void syncWatchedMoviesFromReviews(User user);
	public User changePassword(Integer userID , String newPassword);

	
	
	

}
