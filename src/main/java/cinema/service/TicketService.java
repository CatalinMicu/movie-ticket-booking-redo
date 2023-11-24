package cinema.service;

import cinema.entity.ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {

    ticket createTicket(ticket Ticket);

    Optional<ticket> getTicketById(int id);

    List<ticket> getAllTickets();

}
