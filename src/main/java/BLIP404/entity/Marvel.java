package BLIP404.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

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

    @Column(nullable = false)
    private String poster;

    protected Marvel() {}

    public Marvel(String title, LocalDate releaseDate, LocalDate timelineDate, String poster) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.timelineDate = timelineDate;
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public LocalDate getTimelineDate() {
        return timelineDate;
    }

    public String getPoster() {
        return poster;
    }
}
