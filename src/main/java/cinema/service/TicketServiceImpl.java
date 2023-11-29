package cinema.service;

import cinema.DAO.TicketRepository;
import cinema.entity.movie;
import cinema.entity.ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService{

    private TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository theTicketRepository) {
        ticketRepository = theTicketRepository;
    }

    @Override
    public List<ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public ticket createTicket(ticket Ticket) {
        ticketRepository.save(Ticket);
        return Ticket;
    }

    @Override
    public Optional<ticket> getTicketById(int id) {
        return ticketRepository.findById(id);
    }

    @Override
    public List<ticket> getAllTickets() {
        return null;
    }

    public List<ticket> findTicketsByMovie(movie Movie) {
        return ticketRepository.findByMovie(Movie);
    }

    @Override
    public boolean isSeatAvailable(int movieId, int seatNumber) {
        // Fetch all tickets for the given movie
        List<ticket> ticketsForMovie = ticketRepository.findAll().stream()
                .filter(t -> t.getMovie().getId() == movieId)
                .collect(Collectors.toList());

        for (ticket t : ticketsForMovie) {
            if (t.getSeatNumber() == seatNumber) {
                return false;
            }
        }

        return true;
    }

}
