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

    @Column(nullable = false)
    private String poster;

    protected Marvel() {}

    public Marvel(String title, LocalDate releaseDate, LocalDate timelineDate, List<LocalDate> watched, String poster) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.timelineDate = timelineDate;
        this.watched = watched;
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

    public String getPoster() {
        return poster;
    }

    public void setWatched(List<LocalDate> watched) {
        this.watched = watched;
    }
}
