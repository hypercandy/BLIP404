package BLIP404.controller;

import BLIP404.entity.Marvel;
import BLIP404.repository.MarvelRepository;
import BLIP404.service.MarvelService;
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
    public String chronological(Model model) {
        Iterable<Marvel> chronological = marvelService.getChronological();
        model.addAttribute("chronological", chronological);
        return "chronological";
    }

    @GetMapping("/release")
    public String release(Model model) {
        Iterable<Marvel> release = marvelService.getRelease();
        model.addAttribute("release", release);
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
        Marvel marvel = new Marvel(title, releaseDate, timelineDate, null, poster);
        marvelRepository.save(marvel);
        return "redirect:/upcoming";
    }

    @PostMapping("/watched/{id:[0-9]+}")
    public String postWatchedMovie(@PathVariable Long id, @RequestParam("watched") LocalDate watchedDate, @RequestParam("page") String page) {
        Marvel marvel = marvelService.getMarvelById(id);
        if (marvel.getWatched() == null) {
            List<LocalDate> dateWatched = new ArrayList<>();
            dateWatched.add(watchedDate);
            marvel.setWatched(dateWatched);
            marvelRepository.save(marvel);
            return String.format("redirect:/%s", page);
        } else {
            marvel.getWatched().add(watchedDate);
            marvelRepository.save(marvel);
            return String.format("redirect:/%s", page);
        }
    }
}
