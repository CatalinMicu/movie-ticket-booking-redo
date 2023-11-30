package cinema.service;

import cinema.DAO.MovieRepository;
import cinema.entity.movie;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository themovieRepository) {
        movieRepository = themovieRepository;
    }

    @Override
    public List<movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public movie findById(int theId) {
        Optional<movie> rezultat = movieRepository.findById(theId);
        movie TheMovie = null;

        if (rezultat.isPresent()) {
            TheMovie = rezultat.get();
        } else {
            throw new RuntimeException("Nu a fost gasit filmul cu id - " + theId);
        }

        return TheMovie;
    }

    @Transactional
    @Override
    public movie save(movie theMovie) {
        return movieRepository.save(theMovie);
    }

    @Transactional
    @Override
    public void deleteById(int movie_id) {
        movieRepository.deleteById(movie_id);
    }

    @Transactional
    @Override
    public List<movie> findByName(String name) {
        return movieRepository.findByNameContainingIgnoreCase(name);
    }

}

