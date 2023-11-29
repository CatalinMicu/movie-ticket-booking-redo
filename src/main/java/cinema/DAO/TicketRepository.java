package cinema.DAO;

import cinema.entity.movie;
import cinema.entity.ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<ticket, Integer> {

    Optional<ticket> findByMovieIdAndSeatNumber(int movieId, int seatNumber);

    List<ticket> findByMovie(movie Movie);

}
