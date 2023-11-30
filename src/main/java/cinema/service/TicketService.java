package cinema.service;

import cinema.entity.movie;
import cinema.entity.ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {

    List<ticket> findAll();
    ticket createTicket(ticket Ticket);

    ticket findById(int id);

    void deleteById(int id);

    List<ticket> getAllTickets();

    List<ticket> findTicketsByMovie(movie movie);

    boolean isSeatAvailable(int movieId, int seatNumber);
}
