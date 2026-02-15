package com.example_odev_1.odev_1.Service.ServiceImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example_odev_1.odev_1.entity.Review;
import com.example_odev_1.odev_1.entity.User;
import com.example_odev_1.odev_1.repository.ReviewRepository;
import com.example_odev_1.odev_1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example_odev_1.odev_1.Service.IMovieService;
import com.example_odev_1.odev_1.entity.Genre;
import com.example_odev_1.odev_1.entity.Movie;
import com.example_odev_1.odev_1.repository.MovieRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieServiceImpl implements IMovieService {

@Autowired
private MovieRepository movieRepository;
@Autowired
private ReviewRepository reviewRepository;

@Autowired
UserRepository userRepository;


    
    @Override
    	public Movie findById(Long id) {
    		Optional<Movie> optional=  movieRepository.findById(id);
    		Movie movie = optional.get();
    		return movie;
    	}

    @Transactional
    public void addReview(Long movieId,
                          String review,
                          Integer rating,
                          String spoilerParam,
                          User sessionUser) {

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow();

        User managedUser = userRepository
                .findById(sessionUser.getUserID())
                .orElseThrow();

        Review reviewObj = new Review(review, rating, spoilerParam != null);
        reviewObj.setDate(new Date());
        reviewObj.setMovie(movie);
        reviewObj.setUser(managedUser);

        movie.getReviews().add(reviewObj);

        movieRepository.save(movie);
    }

    @Override
    public void deleteReviewFromMovie(Long movieId,Integer reviewId) {
        Optional<Movie> optional = movieRepository.findById(movieId);
        if(optional.isEmpty()) {
            System.out.println("NO SUCH MOVIE");
        }
        else{
            Movie movie = optional.get();
            boolean removed = movie.getReviews().removeIf(r -> r.getId().equals(reviewId));

            // sonra DB’den sil
            if (removed) {
                reviewRepository.deleteById(reviewId);
            }
            movieRepository.save(movie);

        }
    }
public List<Movie> getMovies(String q, String sort, Long genreId, Integer year) {

    Specification<Movie> spec = (root, query, cb) -> {
        List<Predicate> predicates = new ArrayList<>();

        
        if (q != null && !q.trim().isEmpty()) {
            predicates.add(
                cb.like(
                    cb.lower(root.get("title")),
                    "%" + q.trim().toLowerCase() + "%"
                )
            );
        }

        
        if (genreId != null) {
            predicates.add(
                cb.equal(root.join("genres").get("id"), genreId)
            );
            // join duplicate satır üretebilir; güvenli olsun diye:
            query.distinct(true);
        }

        
        if (year != null) {
            predicates.add(cb.equal(root.get("releaseYear"), year));
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    };

    return movieRepository.findAll(spec, resolveSort(sort));
}

private Sort resolveSort(String sort) {
    if (sort == null || sort.trim().isEmpty()) {
        return Sort.by(Sort.Direction.DESC, "imdbRating");
    }

    if (sort.equals("rating-desc")) return Sort.by(Sort.Direction.DESC, "imdbRating");
    if (sort.equals("rating-asc"))  return Sort.by(Sort.Direction.ASC,  "imdbRating");
    if (sort.equals("year-desc"))   return Sort.by(Sort.Direction.DESC, "releaseYear");
    if (sort.equals("year-asc"))    return Sort.by(Sort.Direction.ASC,  "releaseYear");
    if (sort.equals("title-asc"))   return Sort.by(Sort.Direction.ASC,  "title");

    return Sort.by(Sort.Direction.DESC, "imdbRating");
}
@Override
public List<Genre> getAllGenres() {
    return movieRepository.findDistinctGenres();
}
@Override

public List<Integer> getAllYears() {
    return movieRepository.findDistinctYears();
}

    @Override
    public List<Movie> similarMovies(Long id) {

        Optional<Movie> optional =movieRepository.findById(id);
        if(optional.isEmpty()){
            System.out.println("NO SUCH MOVIE");
            return null;
        }
        List<Genre> genres = optional.get().getGenres();
        List<String>genresString = genres.stream().map(Genre::getName).toList();

        return movieRepository.findMoviesByGenreNames(genresString,id);




    }


}
