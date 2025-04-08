package BLIP404.controller;

import BLIP404.entity.Upcoming;
import BLIP404.service.UpcomingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * This is the BLIP404.controller for the home page.
 */
@Controller
public class HomeController {
    private final UpcomingService upcomingService;
    Logger logger = LoggerFactory.getLogger(HomeController.class);

    public HomeController(UpcomingService upcomingService) {
        this.upcomingService = upcomingService;
    }

    /**
     * Returns the home page.
     * @return the home page.
     */
    @GetMapping("/")
    public String home(Model model) {
        Upcoming nextRelease = upcomingService.getNextRelease();
        model.addAttribute("nextReleaseTitle", nextRelease.getTitle());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String date = nextRelease.getDate().format(formatter);
        model.addAttribute("nextReleaseDate", date);
        model.addAttribute("untilRelease", Period.between(LocalDate.now(), nextRelease.getDate()).getDays());
        return "home";
    }
}
