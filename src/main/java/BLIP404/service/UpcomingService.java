package BLIP404.service;
//
//import nz.ac.canterbury.seng302.homehelper.BLIP404.entity.RenovationFormResult;
//import nz.ac.canterbury.seng302.homehelper.BLIP404.repository.RenovationFormRepository;
//import java.util.Optional;
//import nz.ac.canterbury.seng302.homehelper.util.UnicodeUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//

import BLIP404.entity.Upcoming;
import BLIP404.repository.UpcomingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpcomingService {
    private final UpcomingRepository upcomingRepository;

    @Autowired
    public UpcomingService(UpcomingRepository upcomingRepository) {
        this.upcomingRepository = upcomingRepository;
    }

    /**
     * Gets all renovation records
     * @return a list of all renovation records for the user
     */
    public Iterable<Upcoming> getAllUpcoming() {
        return upcomingRepository.findAll();
    }

    public void newUpcoming(Upcoming upcoming) {
        upcomingRepository.save(upcoming);
    }

    public Upcoming getNextRelease() {
        return upcomingRepository.findMinDate();
    }
}
