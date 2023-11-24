package cinema.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Time;

@Entity
@Table(name = "movies")
public class movie {

    public movie() {
    }

    public movie(String name, int duration, String genre, Time playTime) {
        this.name = name;
        this.duration = duration;
        this.genre = genre;
        this.playTime = playTime;
    }

    @Id
    @Column(name = "movie_id")
    private int id;
    @Column(name = "name")
    private String name;

    @Column(name = "duration")
    private int duration;

    @Column(name = "genre")
    private String genre;

    @Column(name = "play_time")
    private Time playTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Time getPlayTime() {
        return playTime;
    }

    public void setPlayTime(Time playTime) {
        this.playTime = playTime;
    }

    @Override
    public String toString() {
        return "movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", genre='" + genre + '\'' +
                ", playTime=" + playTime +
                '}';
    }
}
