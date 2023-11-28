package cinema.controller;

import cinema.entity.movie;
import cinema.entity.user;
import cinema.service.MovieService;
import cinema.service.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public movie getUser(@PathVariable int movie_id) {
        movie theMovie = movieService.findById(movie_id);
        if (theMovie == null) {
            throw new RuntimeException("User id not found -" + movie_id);
        }
        return theMovie;
    }

    @PostMapping("/movies")
    @ResponseBody
    public movie addUser(@RequestBody movie theMovie) {
        movie dbMovie = movieService.save(theMovie);
        return dbMovie;
    }


}
