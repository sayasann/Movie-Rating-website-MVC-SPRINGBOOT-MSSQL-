package com.example_odev_1.odev_1.repository;
import com.example_odev_1.odev_1.entity.Genre;
import com.example_odev_1.odev_1.entity.Movie;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>,JpaSpecificationExecutor<Movie> {



    //başlığa göre arama
    List<Movie> findByTitleContainingIgnoreCase(String title);
    //genre göre arama
    @Query("""
           select distinct m
           from Movie m
           join m.genres g
           where lower(g.name) = lower(:genreName)
           """)
    List<Movie> findMoviesByGenreName(@Param("genreName") String genreName);
    //Yıla göre arama
    List<Movie> findByReleaseYear(Integer releaseYear);
     @Query("select distinct m.releaseYear from Movie m where m.releaseYear is not null order by m.releaseYear desc")
    List<Integer> findDistinctYears();

    @Query("select distinct g from Movie m join m.genres g order by g.id")
    List<Genre> findDistinctGenres();


    @Query("""
    SELECT DISTINCT m
    FROM Movie m
    JOIN m.genres g
    WHERE g.name IN :genreNames
      AND m.id <> :movieId
""")
    List<Movie> findMoviesByGenreNames(@Param("genreNames") List<String> genreNames, @Param("movieId") Long movieId);
}
