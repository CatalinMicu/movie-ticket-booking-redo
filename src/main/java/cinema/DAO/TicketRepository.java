package cinema.DAO;

import cinema.entity.movie;
import cinema.entity.ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<ticket, Integer> {

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM ticket t WHERE t.movie.id = :movieId AND t.seatNumber = :seatNumber")
    boolean isSeatTaken(int movieId, int seatNumber);

    List<ticket> findByMovie(movie Movie);

}
