package com.example_odev_1.odev_1.Service;
import com.example_odev_1.odev_1.entity.Movie;
import com.example_odev_1.odev_1.entity.User;
import com.example_odev_1.odev_1.entity.Genre;

import java.util.List;
import java.util.Optional;
//Optinal=nullable olabilecek durumlar için kullanılır
//null gelirse hata vermez
public interface IMovieService {


    
    public Movie findById(Long id);

    public void addReview(
            Long movieId,
            String review,
            Integer rating,
            String spoilerParam, User user);

    public void deleteReviewFromMovie(Long movieId, Integer reviewId);
public List<Movie> getMovies(String q, String sort, Long genreId, Integer year);
public List<Genre> getAllGenres();
public List<Integer> getAllYears();
public List<Movie> similarMovies(Long id);
}
