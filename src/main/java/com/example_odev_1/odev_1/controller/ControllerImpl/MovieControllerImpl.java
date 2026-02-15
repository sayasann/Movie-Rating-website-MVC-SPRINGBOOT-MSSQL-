package com.example_odev_1.odev_1.controller.ControllerImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example_odev_1.odev_1.Service.IUserService;
import com.example_odev_1.odev_1.Service.IMovieService;
import com.example_odev_1.odev_1.entity.Movie;
import com.example_odev_1.odev_1.entity.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class MovieControllerImpl {

    @Autowired
    private IMovieService movieService;
    @Autowired
    private IUserService userService;

    public MovieControllerImpl(IMovieService movieService) {
        this.movieService = movieService;
    }
@GetMapping("/")
public String homePage() {
    return "home";
}

    @GetMapping("/movies")
public String browseMovies(
        @RequestParam(required = false) String q,
        @RequestParam(required = false) String sort,
        @RequestParam(required = false) Long genreId,
        @RequestParam(required = false) Integer year,
        Model model
) {

    List<Movie> movies = movieService.getMovies(q, sort, genreId, year);

    model.addAttribute("movies", movies);
    model.addAttribute("q", q);
    model.addAttribute("sort", sort);
    model.addAttribute("genreId", genreId);
    model.addAttribute("year", year);
    model.addAttribute("genres", movieService.getAllGenres());
    model.addAttribute("years", movieService.getAllYears());


    return "movies/browse";
}



    @GetMapping("/movies/{id}")
    public String movieDetail(@PathVariable Long id,
                               Model model,
                               HttpSession session) {

        Movie movie = movieService.findById(id);
        List<Movie> similarMovies = movieService.similarMovies(id);
        model.addAttribute("similarMovies", similarMovies);

        model.addAttribute("movie", movie);

        boolean isFavorite = false;
        boolean inWatchList = false;
        boolean isWatched = false;

        User loggedUser = (User) session.getAttribute("loggedUser");
        if (loggedUser != null) {
            User freshUser = userService.findById(loggedUser.getUserID());

            isFavorite  = freshUser.getFavoriteMovies().contains(movie);
            inWatchList = freshUser.getWatchList().contains(movie);
            isWatched   = freshUser.getWatchedMovies().contains(movie);
        }

        model.addAttribute("isFavorite", isFavorite);
        model.addAttribute("inWatchList", inWatchList);
        model.addAttribute("isWatched", isWatched);

        return "movies/detail";
    }

    @PostMapping("movies/reviews/{id}")
    public String addReview(@PathVariable(name = "id")
                              Long movieId,
                            @RequestParam(name="review") String review,
                            @RequestParam (name="rating")Integer rating,
                            @RequestParam(value = "spoiler", required = false)String spoilerParam,HttpSession session) {


        User loggedUser = (User) session.getAttribute("loggedUser");
        if(loggedUser ==null){
            return "redirect:/users/login";
        }
        movieService.addReview(movieId,review,rating,spoilerParam,loggedUser);
        return "redirect:/movies/"+movieId;

    }

    @PostMapping("/movies/reviews/delete/{movieId}/{reviewId}")
    public String deleteReview(@PathVariable(name="movieId") Long movieId,@PathVariable
            (name="reviewId") Integer reviewId,
                               HttpSession session) {

        User loggedUser = (User) session.getAttribute("loggedUser");
        if(loggedUser ==null){
            return "redirect:/users/login";
        }
        movieService.deleteReviewFromMovie(movieId,reviewId);
        return "redirect:/movies/"+movieId;

    }




}
