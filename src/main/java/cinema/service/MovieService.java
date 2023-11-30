package cinema.service;

import cinema.entity.movie;

import java.util.List;

public interface MovieService {
    List<movie> findAll();

    movie findById(int movie_id);

    movie save(movie theMovie);
    void deleteById(int movie_id);

    List<movie> findByName(String name);
}
