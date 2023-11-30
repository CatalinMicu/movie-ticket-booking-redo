package cinema.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tickets")
public class ticket {

    public ticket() {
    }

    public ticket(cinema.entity.user user, cinema.entity.movie movie, Date purchaseDate, int seatNumber) {
        this.user = user;
        this.movie = movie;
        this.purchaseDate = purchaseDate;
        this.seatNumber = seatNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private user user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private movie movie;

    @Column(name = "purchase_date")
    private Date purchaseDate;

    @Column(name = "seat_number")
    private int seatNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public cinema.entity.user getUser() {
        return user;
    }

    public void setUser(cinema.entity.user user) {
        this.user = user;
    }

    public cinema.entity.movie getMovie() {
        return movie;
    }

    public void setMovie(cinema.entity.movie movie) {
        this.movie = movie;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return "ticket{" +
                "id=" + id +
                ", user=" + user +
                ", movie=" + movie +
                ", purchaseDate=" + purchaseDate +
                ", seatNumber=" + seatNumber +
                '}';
    }
}
