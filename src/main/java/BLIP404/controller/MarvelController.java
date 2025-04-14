package BLIP404.controller;

import BLIP404.entity.Marvel;
import BLIP404.repository.MarvelRepository;
import BLIP404.service.MarvelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This is the BLIP404.controller for the home page.
 */
@Controller
public class MarvelController {
    private final MarvelService marvelService;
    private final MarvelRepository marvelRepository;

    public MarvelController(MarvelService marvelService, MarvelRepository marvelRepository) {
        this.marvelService = marvelService;
        this.marvelRepository = marvelRepository;
    }

    /**
     * Returns the home page.
     * @return the home page.
     */
    @GetMapping("/")
    public String home(Model model) {
        Marvel nextRelease = marvelService.getNextRelease();
        model.addAttribute("nextReleaseTitle", nextRelease.getTitle());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String date = nextRelease.getReleaseDate().format(formatter);
        model.addAttribute("nextReleaseDate", date);
        model.addAttribute("untilRelease", Period.between(LocalDate.now(), nextRelease.getReleaseDate()).getDays());
        return "home";
    }

    @GetMapping("/upcoming")
    public String upcoming(Model model) {
        Iterable<Marvel> upcoming = marvelService.getAllUpcoming();
        model.addAttribute("upcoming", upcoming);
        return "upcoming";
    }

    @GetMapping("/chronological")
    public String chronological(Model model,
                                @RequestParam(name = "pageNumber", required = false) Integer pageNumber) {
        int pageSize = 5; // 5 per page
        Pageable page = PageRequest.of(Objects.requireNonNullElse(pageNumber, 0), pageSize);

        Page<Marvel> chronological = marvelService.getChronological(page);
        model.addAttribute("chronological", chronological.getContent());
        model.addAttribute("totalPages", chronological.getTotalPages());
        model.addAttribute("pageNumber", chronological.getNumber());
        model.addAttribute("pageSize", pageSize);
        return "chronological";
    }

    @GetMapping("/release")
    public String release(Model model,
                          @RequestParam(name = "pageNumber", required = false) Integer pageNumber) {
        int pageSize = 5; // 5 per page
        Pageable page = PageRequest.of(Objects.requireNonNullElse(pageNumber, 0), pageSize);

        Page<Marvel> release = marvelService.getRelease(page);
        model.addAttribute("release", release.getContent());
        model.addAttribute("totalPages", release.getTotalPages());
        model.addAttribute("pageNumber", release.getNumber());
        model.addAttribute("pageSize", pageSize);
        return "release";
    }

    @GetMapping("/new")
    public String getNewMovie() {
        return "newAnnouncement";
    }

    @PostMapping("/new")
    public String postNewMovie(@RequestParam String title,
                               @RequestParam LocalDate releaseDate,
                               @RequestParam LocalDate timelineDate,
                               @RequestParam String poster,
                               Model model) {
        model.addAttribute("title", title);
        model.addAttribute("releaseDate", releaseDate);
        model.addAttribute("timelineDate", timelineDate);
        model.addAttribute("poster", poster);
        Marvel marvel = new Marvel(title, releaseDate, timelineDate, null, null, poster);
        marvelRepository.save(marvel);
        return "redirect:/upcoming";
    }

    @PostMapping("/watched/{id:[0-9]+}")
    public String postWatchedMovie(@PathVariable Long id, @RequestParam("date") LocalDate watchedDate, @RequestParam("rate") Long watchRate, @RequestParam("page") String page) {
        Marvel marvel = marvelService.getMarvelById(id);
        List<LocalDate> watched = marvel.getWatched();
        List<Long> ratings = marvel.getRating();
        if (watched == null || ratings == null) {
            watched = new ArrayList<>();
            ratings = new ArrayList<>();
            watched.add(watchedDate);
            ratings.add(watchRate);
        } else {
            int index = watched.indexOf(watchedDate);
            if (watchRate == 0 && watched.contains(watchedDate)) {
                watched.remove(index);
                ratings.remove(index);
            } else if (index != -1) {
                ratings.set(index, watchRate);
            } else {
                watched.add(watchedDate);
                ratings.add(watchRate);
            }
        }
        marvel.setWatched(watched);
        marvel.setRating(ratings);
        marvelRepository.save(marvel);
        return String.format("redirect:/%s?page=%s", page, page);
    }
}
