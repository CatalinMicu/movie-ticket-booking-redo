package cinema.controller;

import cinema.entity.movie;
import cinema.entity.ticket;
import cinema.entity.user;
import cinema.service.MovieService;
import cinema.service.TicketService;
import cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class TicketController {

    private final TicketService ticketService;
    private final MovieService movieService;
    private final UserService userService;

    @Autowired
    public TicketController(TicketService ticketService, MovieService movieService, UserService userService) {
        this.ticketService = ticketService;
        this.movieService = movieService;
        this.userService = userService;
    }

    @PostMapping("/buyTicket/{movie_id}")
    public ResponseEntity<?> buyTicket(@PathVariable("movie_id") int movieId, @RequestBody Map<String, Integer> payload) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        user loggedInUser = userService.findByUsername(username);
        if (loggedInUser == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        movie selectedMovie = movieService.findById(movieId);
        if (selectedMovie == null) {
            return ResponseEntity.badRequest().body("Movie not found");
        }

        Integer seatNumber = payload.get("seatNumber");
        if (seatNumber == null) {
            return ResponseEntity.badRequest().body("Seat number is required.");
        }

        boolean seatAvailable = ticketService.isSeatAvailable(movieId, seatNumber);
        if (!seatAvailable) {
            return ResponseEntity.badRequest().body("Seat number " + seatNumber + " is already taken.");
        }

        ticket newTicket = new ticket(loggedInUser, selectedMovie, new Date(), seatNumber);

        ticketService.createTicket(newTicket);

        return ResponseEntity.ok(newTicket);
    }

    @GetMapping("/tickets")
    public List<ticket> findAll() {
        return ticketService.findAll();
    }

    @GetMapping("/tickets/{ticket_id}")
    @ResponseBody
    public ticket getTicket(@PathVariable int ticket_id) {
        ticket theTicket = ticketService.findById(ticket_id);
        if (theTicket == null) {
            throw new TicketNotFoundException("User id not found -" + theTicket);
        }
        return theTicket;
    }

    @DeleteMapping("tickets/{ticket_id}")
    public String deleteTicket(@PathVariable int ticket_id) {
        ticket tempTicket = ticketService.findById(ticket_id);

        if (tempTicket == null) {
            throw new RuntimeException("Ticket id not found - " + tempTicket);
        }
        ticketService.deleteById(ticket_id);
        return "Deleted ticket id - " + ticket_id;
    }


}