package com.workintech.s19d1.controller;

import com.workintech.s19d1.dto.MovieRequest;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.MovieService;
import com.workintech.s19d1.util.HollywoodValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@Slf4j
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> findAll() {
        return movieService.findAll();
    }

    @GetMapping("/{id}")
    public Movie findById(@PathVariable Long id) {
        try {
            return movieService.findById(id);
        } catch (Exception ex) {
            log.error("Error finding movie with id {}: {}", id, ex.getMessage());
            throw ex;
        }
    }

    @PostMapping
    public Movie save(@RequestBody MovieRequest movieRequest) {
        try {
            Movie movie = movieRequest.getMovie();
            Actor actor = movieRequest.getActor();
            if (actor != null) {
                movie.addActor(actor);
            }
            HollywoodValidation.validateMovie(movie);
            return movieService.save(movie);
        } catch (Exception ex) {
            log.error("Error saving movie: {}", ex.getMessage());
            throw ex;
        }
    }

    @PutMapping("/{id}")
    public Movie update(@PathVariable Long id, @RequestBody Movie movie) {
        try {
            movieService.findById(id);
            movie.setId(id);
            HollywoodValidation.validateMovie(movie);
            return movieService.save(movie);
        } catch (Exception ex) {
            log.error("Error updating movie with id {}: {}", id, ex.getMessage());
            throw ex;
        }
    }

    @DeleteMapping("/{id}")
    public Movie delete(@PathVariable Long id) {
        try {
            Movie movie = movieService.findById(id);
            movieService.delete(movie);
            return movie;
        } catch (Exception ex) {
            log.error("Error deleting movie with id {}: {}", id, ex.getMessage());
            throw ex;
        }
    }
}
