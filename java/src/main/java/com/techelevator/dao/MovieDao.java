package com.techelevator.dao;

import com.techelevator.model.Movie;

import java.util.List;

public interface MovieDao {
    List<Movie> getMovies();
    Movie getMovieById(int id);
    Movie getMovieByName(String name);
    List<Movie> getMoviesByGenreId(int id);
}
