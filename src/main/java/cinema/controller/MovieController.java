package cinema.controller;

import cinema.entity.movie;
import cinema.entity.user;
import cinema.service.MovieService;
import cinema.service.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieServiceImpl theMovieService) {
        movieService = theMovieService;
    }

    @GetMapping("/movies")
    @ResponseBody
    public List<movie> findAll() {
        return movieService.findAll();
    }

    @GetMapping("/movie/{movie_id}")
    @ResponseBody
    public movie getMovie(@PathVariable int movie_id) {
        movie theMovie = movieService.findById(movie_id);
        if (theMovie == null) {
            throw new RuntimeException("User id not found -" + movie_id);
        }
        return theMovie;
    }

    @PostMapping("/movies")
    @ResponseBody
    public movie addMovie(@RequestBody movie theMovie) {
        movie dbMovie = movieService.save(theMovie);
        return dbMovie;
    }

    @GetMapping("/movies/name/{name}")
    @ResponseBody
    public List<movie> getMoviesByName(@PathVariable String name) {
        List<movie> movies = movieService.findByName(name);
        if (movies.isEmpty()) {
            throw new MovieNotFoundException("No movies found with name '" + name + "'");
        }
        return movies;
    }


}
