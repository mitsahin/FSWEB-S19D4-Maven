package com.workintech.s19d1.util;

import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class HollywoodValidation {

    private HollywoodValidation() {
    }

    public static void validateActor(Actor actor) {
        if (actor == null) {
            throw new ApiException("Actor cannot be null", HttpStatus.BAD_REQUEST);
        }
        if (actor.getFirstName() == null || actor.getFirstName().isBlank()) {
            throw new ApiException("Actor first name is required", HttpStatus.BAD_REQUEST);
        }
        if (actor.getLastName() == null || actor.getLastName().isBlank()) {
            throw new ApiException("Actor last name is required", HttpStatus.BAD_REQUEST);
        }
        if (actor.getGender() == null) {
            throw new ApiException("Actor gender is required", HttpStatus.BAD_REQUEST);
        }
        if (actor.getBirthDate() == null) {
            throw new ApiException("Actor birth date is required", HttpStatus.BAD_REQUEST);
        }
    }

    public static void validateMovie(Movie movie) {
        if (movie == null) {
            throw new ApiException("Movie cannot be null", HttpStatus.BAD_REQUEST);
        }
        if (movie.getName() == null || movie.getName().isBlank()) {
            throw new ApiException("Movie name is required", HttpStatus.BAD_REQUEST);
        }
        if (movie.getDirectorName() == null || movie.getDirectorName().isBlank()) {
            throw new ApiException("Movie director name is required", HttpStatus.BAD_REQUEST);
        }
        if (movie.getRating() < 0 || movie.getRating() > 10) {
            throw new ApiException("Movie rating must be between 0 and 10", HttpStatus.BAD_REQUEST);
        }
        if (movie.getReleaseDate() == null) {
            throw new ApiException("Movie release date is required", HttpStatus.BAD_REQUEST);
        }
    }
}
