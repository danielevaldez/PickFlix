package com.techelevator.controller;

import com.techelevator.dao.GenreDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Genre;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
@RequestMapping(path = "/genres")
public class GenreController {
    private GenreDao genreDao;

    public GenreController(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/favorited", method = RequestMethod.POST)
    public void addFavoriteGenres(@RequestParam int profileId, int genreId) {
        try {
            Genre genre = genreDao.addFavoriteGenre(profileId, genreId);
            if (genre == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Favorited genre failed.");
            }
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Favorited genre failed.");
        }
    }
}
