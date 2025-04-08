package BLIP404.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Upcoming {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String platform;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String poster;

    protected  Upcoming() {}

    public Upcoming(String title, String platform, LocalDate date, String poster) {
        this.title = title;
        this.platform = platform;
        this.date = date;
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getPoster() {
        return poster;
    }
}
