package BLIP404.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This the the BLIP404.controller for the home page.
 */
@Controller
public class HomeController {
    Logger logger = LoggerFactory.getLogger(HomeController.class);

    /**
     * Returns the home page.
     * @return the home page.
     */
    @GetMapping("/")
    public String home() {
        logger.info("GET /");
        return "home";
    }

}
