package BLIP404.controller;

import BLIP404.entity.Upcoming;
import BLIP404.service.UpcomingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class UpcomingController {
    private final UpcomingService upcomingService;

    public UpcomingController(UpcomingService upcomingService) {
        this.upcomingService = upcomingService;
    }

    @GetMapping("/upcoming")
    public String upcoming(Model model) {
        Iterable<Upcoming> upcoming = upcomingService.getAllUpcoming();
        model.addAttribute("upcoming", upcoming);
        return "upcoming";
    }
}
