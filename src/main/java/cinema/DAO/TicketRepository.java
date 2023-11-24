package cinema.DAO;

import cinema.entity.ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<ticket, Integer> {
}
