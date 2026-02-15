package com.example_odev_1.odev_1.Service.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example_odev_1.odev_1.Service.IUserService;
import com.example_odev_1.odev_1.entity.Movie;
import com.example_odev_1.odev_1.entity.Review;
import com.example_odev_1.odev_1.entity.User;
import com.example_odev_1.odev_1.repository.MovieRepository;
import com.example_odev_1.odev_1.repository.ReviewRepository;
import com.example_odev_1.odev_1.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Override
	public User registration(User newUser) {
		 if (userRepository.existsByUserName(newUser.getUserName())) {
		        throw new IllegalStateException("Username already exists");
		    }
		return userRepository.save(newUser);
	}
	
	@Override
	public User login(String userName, String password) {

	    Optional<User> optionalUser = userRepository.findByUserName(userName);

	    if(optionalUser.isPresent()) {
	    	User user = optionalUser.get();
	    	if(user.getUserPassword().equals(password)) {
	    		return user;
	    	}
	    }
	    
	    return null;
	}

	@Override
	public void deleteAccount(Integer userID,String password) {
		User user = findById(userID);
		if(!user.getUserPassword().equals(password)) {
			throw new IllegalStateException("Password is incorrect");
		}
		userRepository.delete(user);
		
	}
	
	@Override
	public List<Movie> favoriteMovies(User user, Long movieId) {

	    User foundUser = userRepository.findById(user.getUserID())
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    Movie foundMovie = movieRepository.findById(movieId)
	            .orElseThrow(() -> new RuntimeException("Movie not found"));

	    List<Movie> favorites = foundUser.getFavoriteMovies();

	    if (favorites.contains(foundMovie)) {
	        favorites.remove(foundMovie);
	    } else {
	        favorites.add(foundMovie);
	    }

	    userRepository.save(foundUser);
	    return favorites;
	}
	@Override
	public User findById(Integer id) {
		Optional<User> userOptional= userRepository.findById(id);
		User user = userOptional.get();
		return user;
	}
	@Override
	public List<Movie> watchList(User user, Long movieId) {
		User foundUser = userRepository.findById(user.getUserID())
				.orElseThrow(() -> new RuntimeException("User not found"));
		Movie foundMovie = movieRepository.findById(movieId)
				.orElseThrow(() -> new RuntimeException("Movie not found"));
		List<Movie> watchList = foundUser.getWatchList();
		List<Movie> watchedList=foundUser.getWatchedMovies();
		if(watchList.contains(foundMovie)) {
			watchList.remove(foundMovie);
		}
		else  {
			if(!watchedList.contains(foundMovie)) {
				watchList.add(foundMovie);
			}
			else {
				throw new IllegalStateException(
						"You have already watched this movie. You cannot add it to the watchlist."
			        );
			}
		}
		userRepository.save(foundUser);
		return watchList;
	}
	
	@Override
	public List<Movie> watchedMovies(User user, Long movieId) {
		User foundUser = userRepository.findById(user.getUserID())
				.orElseThrow(() -> new RuntimeException("User not found"));
		Movie foundMovie = movieRepository.findById(movieId)
				.orElseThrow(() -> new RuntimeException("Movie not found"));
		List<Movie> watchList = foundUser.getWatchList();
		List<Movie> watchedList=foundUser.getWatchedMovies();
		if(watchedList.contains(foundMovie)) {
			watchedList.remove(foundMovie);
		}
		else {
			if(watchList.contains(foundMovie)) {
				watchList.remove(foundMovie);
				watchedList.add(foundMovie);
			}
			else {
				watchedList.add(foundMovie);
			}
		}
		userRepository.save(foundUser);
		return watchedList;
	}
	
	@Transactional
	public void syncWatchedMoviesFromReviews(User user) {

	    User foundUser = userRepository.findById(user.getUserID())
	            .orElseThrow();

	    List<Review> reviews = reviewRepository.findByUser(foundUser);

	    List<Movie> watchedMovies = foundUser.getWatchedMovies();

		List<Movie> watchedLater = foundUser.getWatchList();

	    for (Review review : reviews) {
	        Movie movie = review.getMovie();

	        if (!watchedMovies.contains(movie)) {
	            watchedMovies.add(movie);
	        }
			if(watchedLater.contains(movie)) {
				watchedLater.remove(movie);
			}
	    }

	    userRepository.save(foundUser);
	}

@Override
public User changePassword(Integer userID, String newPassword) {
	User user = findById(userID);
	if(newPassword==null || newPassword.length()<3) {
		throw new IllegalArgumentException("Password must be at least 3 characters");
	}
	user.setUserPassword(newPassword);
	 return userRepository.save(user);
}

	
	
	
	

	
}
