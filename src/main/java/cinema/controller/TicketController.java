package cinema.controller;

import cinema.entity.movie;
import cinema.entity.ticket;
import cinema.entity.user;
import cinema.service.MovieService;
import cinema.service.TicketService;
import cinema.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

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
}