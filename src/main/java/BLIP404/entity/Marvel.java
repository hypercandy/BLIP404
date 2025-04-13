package BLIP404.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Marvel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate releaseDate;

    @Column
    private LocalDate timelineDate;

    @ElementCollection
    private List<LocalDate> watched;

    @ElementCollection
    private List<Long> rating;

    @Column(nullable = false)
    private String poster;

    protected Marvel() {}

    public Marvel(String title, LocalDate releaseDate, LocalDate timelineDate, List<LocalDate> watched, List<Long> rating, String poster) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.timelineDate = timelineDate;
        this.watched = watched;
        this.rating = rating;
        this.poster = poster;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public List<LocalDate> getWatched() {
        return watched;
    }

    public List<Long> getRating() {
        return rating;
    }

    public String getPoster() {
        return poster;
    }

    public void setWatched(List<LocalDate> watched) {
        this.watched = watched;
    }

    public void setRating(List<Long> rating) {
        this.rating = rating;
    }
}
