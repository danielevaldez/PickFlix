package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Movie;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMovieDao implements MovieDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcMovieDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT movie_id, movie_title, movie_duration, movie_release, movie_description, genre_id FROM movie";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Movie movie = mapRowToMovie(results);
                movies.add(movie);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return movies;
    }

    @Override
    public Movie getMovieById(int id) {
        Movie movie = null;
        String sql = "SELECT movie_id, movie_title, movie_duration, movie_release, movie_description, genre_id FROM movie WHERE movie_id = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                movie = mapRowToMovie(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return movie;
    }

    @Override
    public Movie getMovieByName(String name) {
        Movie movie = null;
        String sql = "SELECT movie_id, movie_title, movie_duration, movie_release, movie_description, genre_id FROM movie WHERE movie_name = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);
            if (results.next()) {
                movie = mapRowToMovie(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return movie;
    }

    @Override
    public List<Movie> getMoviesByGenreId(int id) {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT movie_id, movie_title, movie_duration, movie_release, movie_description, genre_id FROM movie WHERE genre_id = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            while (results.next()) {
                Movie movie = mapRowToMovie(results);
                movies.add(movie);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return movies;
    }

    private Movie mapRowToMovie(SqlRowSet rs) {
        Movie movie = new Movie();
        movie.setMovieId(rs.getInt("movie_id"));
        movie.setMovieTitle(rs.getString("movie_title"));
        movie.setMovieDuration(rs.getInt("movie_duration"));
        movie.setMovieRelease(rs.getInt("movie_release"));
        movie.setMovieDescription(rs.getString("movie_description"));
        movie.setGenreId(rs.getInt("genre_id"));
        return movie;
    }
}
