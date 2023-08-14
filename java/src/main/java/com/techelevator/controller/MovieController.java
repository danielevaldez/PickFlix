package com.techelevator.controller;

import com.techelevator.dao.MovieDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Movie;
import com.techelevator.model.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(path= "/movies")
public class MovieController {

    private MovieDao movieDao;

    public MovieController(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @RequestMapping(path = "/browse/{profileId}", method = RequestMethod.GET)
    public List<Movie> getMovies (@PathVariable int profileId) {
        List<Movie> getMovies = new ArrayList<>();
        try {
            getMovies = movieDao.getMovies(profileId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not retrieve movies.");
        }
        return getMovies;
    }




}
