package BLIP404;

import BLIP404.entity.Upcoming;
import BLIP404.repository.UpcomingRepository;
import BLIP404.service.UpcomingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
    private final UpcomingRepository upcomingRepository;
    private final UpcomingService upcomingService;

    public DataLoader(UpcomingRepository upcomingRepository, UpcomingService upcomingService) {
        this.upcomingRepository = upcomingRepository;
        this.upcomingService = upcomingService;
    }

    // see this https://www.baeldung.com/running-setup-logic-on-startup-in-spring
    // and https://stackoverflow.com/questions/69714828/what-is-the-exact-purpose-of-the-commandlinerunner-in-spring-boot

    @Override
    public void run(String[] args) {
        final var upcoming = (List<Upcoming>)upcomingService.getAllUpcoming();
        if (upcoming.isEmpty()) {
            List<Upcoming> newUpcoming = new ArrayList<>();
            newUpcoming.add(new Upcoming("Thunderbolts*", "Marvel Studios", LocalDate.of(2025, 5, 2), "https://cdn.marvel.com/content/1x/thunderbolts_lob_crd_03.jpg"));
            newUpcoming.add(new Upcoming("The Fantastic Four: First Steps", "Marvel Studios", LocalDate.of(2025, 7, 25), "https://cdn.marvel.com/content/1x/thefantasticfourfirststeps_lob_crd_02.jpg"));
            newUpcoming.add(new Upcoming("Avengers: Doomsday", "Marvel Studios", LocalDate.of(2026, 5, 1), "https://cdn.marvel.com/content/1x/avengersdoomsday_lob_crd_01.jpg"));
            newUpcoming.add(new Upcoming("Spider-Man: Brand New Day", "Marvel Studios", LocalDate.of(2026, 7, 31), "https://cdn.marvel.com/content/1x/spidermanbrandnewday_lob_crd_01.jpg"));
            newUpcoming.add(new Upcoming("Avengers: Secret Wars", "Marvel Studios", LocalDate.of(2027, 5, 7), "https://cdn.marvel.com/content/1x/avengerssecretwars_lob_crd_02.jpg"));
            upcomingRepository.saveAll(newUpcoming);
        }
    }
}