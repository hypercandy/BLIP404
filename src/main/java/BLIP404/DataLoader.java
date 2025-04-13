package BLIP404;

import BLIP404.entity.Marvel;
import BLIP404.repository.MarvelRepository;
import BLIP404.service.MarvelService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
    private final MarvelRepository marvelRepository;
    private final MarvelService marvelService;

    public DataLoader(MarvelRepository marvelRepository, MarvelService marvelService) {
        this.marvelRepository = marvelRepository;
        this.marvelService = marvelService;
    }

    // see this https://www.baeldung.com/running-setup-logic-on-startup-in-spring
    // and https://stackoverflow.com/questions/69714828/what-is-the-exact-purpose-of-the-commandlinerunner-in-spring-boot

    @Override
    public void run(String[] args) {
        final var marvel = (List<Marvel>) marvelService.getAllMarvel();
        if (marvel.isEmpty()) {
            marvel.add(new Marvel("Captain America: The First Avenger", LocalDate.of(2011, 7, 22), LocalDate.of(1943, 6, 1), null, null, "https://cdn.marvel.com/content/1x/captainamericathefirstavenger_lob_crd_01_0.jpg"));
            marvel.add(new Marvel("Captain Marvel", LocalDate.of(2019, 3, 8), LocalDate.of(1995, 7, 1), null, null,"https://cdn.marvel.com/content/1x/captainmarvel_lob_crd_06.jpg"));
            marvel.add(new Marvel("Iron Man", LocalDate.of(2008, 4, 29), LocalDate.of(2008, 5, 1), null, null,"https://cdn.marvel.com/content/1x/ironman_lob_crd_01_4.jpg"));
            marvel.add(new Marvel("Iron Man 2", LocalDate.of(2010, 5, 7), LocalDate.of(2010, 3, 1), null, null, "https://cdn.marvel.com/content/1x/ironman2_lob_crd_01_4.jpg"));
            marvel.add(new Marvel("The Incredible Hulk", LocalDate.of(2008, 6, 13), LocalDate.of(2010, 4, 1), null, null, "https://cdn.marvel.com/content/1x/theincrediblehulk_lob_crd_03.jpg"));
            marvel.add(new Marvel("Thor", LocalDate.of(2011, 5, 6), LocalDate.of(2010, 5, 1), null, null, "https://cdn.marvel.com/content/1x/thor_lob_crd_01_1.jpg"));
            marvel.add(new Marvel("The Avengers", LocalDate.of(2012, 5, 4), LocalDate.of(2012, 5, 1), null, null, "https://cdn.marvel.com/content/1x/theavengers_lob_crd_03_0.jpg"));
            marvel.add(new Marvel("Thor: The Dark World", LocalDate.of(2013, 11, 8), LocalDate.of(2013, 8, 1), null, null, "https://cdn.marvel.com/content/1x/thorthedarkworld_lob_crd_02.jpg"));
            marvel.add(new Marvel("Iron Man 3", LocalDate.of(2013, 5, 3), LocalDate.of(2013, 12, 1), null, null, "https://cdn.marvel.com/content/1x/ironman3_lob_crd_01_11.jpg"));
            marvel.add(new Marvel("Captain America: The Winter Soldier", LocalDate.of(2014, 4, 4), LocalDate.of(2014, 5, 1), null, null, "https://cdn.marvel.com/content/1x/captainamericathewintersoldier_lob_crd_01_2.jpg"));
            marvel.add(new Marvel("Guardians of the Galaxy", LocalDate.of(2014, 7, 1), LocalDate.of(2014, 6, 1), null, null, "https://cdn.marvel.com/content/1x/guardiansofthegalaxy_lob_crd_03_0.jpg"));
            marvel.add(new Marvel("Guardian of the Galaxy Vol. 2", LocalDate.of(2017, 5, 5), LocalDate.of(2014, 8, 1), null, null, "https://cdn.marvel.com/content/1x/guardiansofthegalaxyvol.2_lob_crd_01_0.jpg"));
            marvel.add(new Marvel("Avengers: Age of Ultron", LocalDate.of(2015, 5, 1), LocalDate.of(2015, 5, 1), null, null, "https://cdn.marvel.com/content/1x/avengersageofultron_lob_crd_03_0.jpg"));
            marvel.add(new Marvel("Ant-Man", LocalDate.of(2017, 7, 17), LocalDate.of(2015, 6, 1), null, null, "https://cdn.marvel.com/content/1x/ant-man_lob_crd_01_9.jpg"));
            marvel.add(new Marvel("Captain America: Civil War", LocalDate.of(2016, 5, 6), LocalDate.of(2016, 5, 1), null, null, "https://cdn.marvel.com/content/1x/captainamericacivilwar_lob_crd_01_10.jpg"));
            marvel.add(new Marvel("Black Widow", LocalDate.of(2021, 7, 9), LocalDate.of(2016, 5, 1), null, null, "https://cdn.marvel.com/content/1x/blackwidow_lob_crd_06.jpg"));
            marvel.add(new Marvel("Black Panther", LocalDate.of(2018, 2, 16), LocalDate.of(2016, 6, 1), null, null, "https://cdn.marvel.com/content/1x/blackpanther_lob_crd_01_5.jpg"));
            marvel.add(new Marvel("Spider-Man: Homecoming", LocalDate.of(2017, 7, 7), LocalDate.of(2016, 8, 1), null, null, "https://cdn.marvel.com/content/1x/spider-manhomecoming_lob_crd_02.jpg"));
            marvel.add(new Marvel("Doctor Strange", LocalDate.of(2016, 11, 4), LocalDate.of(2016, 9, 1), null, null, "https://cdn.marvel.com/content/1x/doctorstrange_lob_crd_01_7.jpg"));
            marvel.add(new Marvel("Thor: Ragnarok", LocalDate.of(2017, 11, 3), LocalDate.of(2017, 8, 1), null, null, "https://cdn.marvel.com/content/1x/thorragnarok_lob_crd_03_0.jpg"));
            marvel.add(new Marvel("Ant-Man and the Wasp", LocalDate.of(2018, 7, 6), LocalDate.of(2018, 8, 1), null, null, "https://cdn.marvel.com/content/1x/ant-manthewasp_lob_crd_01_0.jpg"));
            marvel.add(new Marvel("Avengers: Infinity War", LocalDate.of(2018, 4, 28), LocalDate.of(2018, 9, 1), null, null, "https://cdn.marvel.com/content/1x/avengersinfinitywar_lob_crd_02.jpg"));
            marvel.add(new Marvel("Avengers: Endgame", LocalDate.of(2019, 4, 26), LocalDate.of(2023, 8, 1), null, null, "https://cdn.marvel.com/content/1x/avengersendgame_lob_crd_05.jpg"));
            marvel.add(new Marvel("Shang-Chi and the Legend of the Ten Rings", LocalDate.of(2021, 9, 3), LocalDate.of(2024, 5, 1), null, null, "https://cdn.marvel.com/content/1x/shangchi_lob_crd_07.jpg"));
            marvel.add(new Marvel("Spider-Man: Far From Home", LocalDate.of(2019, 7, 2), LocalDate.of(2024, 6, 1), null, null, "https://cdn.marvel.com/content/1x/spider-manfarfromhome_lob_crd_04_0.jpg"));
            marvel.add(new Marvel("Spider-Man: No Way Home", LocalDate.of(2021, 12, 17), LocalDate.of(2024, 8, 1), null, null, "https://cdn.marvel.com/content/1x/spider-mannowayhome_lob_crd_03.jpg"));
            marvel.add(new Marvel("Eternals", LocalDate.of(2021, 11, 5), LocalDate.of(2024, 9, 1), null, null, "https://cdn.marvel.com/content/1x/eternals_lob_crd_06.jpg"));
            marvel.add(new Marvel("Doctor Strange in the Multiverse of Madness", LocalDate.of(2022, 5, 6), LocalDate.of(2024, 10, 1), null, null, "https://cdn.marvel.com/content/1x/doctorstrangeinthemultiverseofmadness_lob_crd_02_3.jpg"));
            marvel.add(new Marvel("Black Panther: Wakanda Forever", LocalDate.of(2022, 11, 11), LocalDate.of(2025, 4, 1), null, null, "https://cdn.marvel.com/content/1x/blackpantherwakandaforever_lob_crd_06.jpg"));
            marvel.add(new Marvel("Thor: Love and Thunder", LocalDate.of(2022, 7, 8), LocalDate.of(2025, 10, 1), null, null, "https://cdn.marvel.com/content/1x/thorloveandthunder_lob_crd_04.jpg"));
            marvel.add(new Marvel("Ant-Man and the Wasp: Quantumania", LocalDate.of(2023, 2, 17), LocalDate.of(2025, 11, 1), null, null, "https://cdn.marvel.com/content/1x/antmanandthewaspquantumania_lob_crd_03.jpg"));
            marvel.add(new Marvel("Guardians of the Galaxy Vol. 3", LocalDate.of(2023, 5, 5), LocalDate.of(2025, 12, 27), null, null, "https://cdn.marvel.com/content/1x/guardiansofthegalaxyvolume3_lob_crd_03.jpg"));
            marvel.add(new Marvel("The Marvels", LocalDate.of(2023, 11, 10), LocalDate.of(2026, 1, 1), null, null, "https://cdn.marvel.com/content/1x/themarvels_lob_crd_05.jpg"));
            marvel.add(new Marvel("Deadpool and Wolverine", LocalDate.of(2024, 7, 6), LocalDate.of(2026, 2, 1), null, null, "https://cdn.marvel.com/content/1x/deadpoolandwolverine_lob_crd_03.jpg"));
            marvel.add(new Marvel("Captain America: Brave New World", LocalDate.of(2025, 2, 14), LocalDate.of(2027, 4, 1), null, null, "https://cdn.marvel.com/content/1x/captainamericabravenewworld_lob_crd_05.jpg"));
            marvel.add(new Marvel("Thunderbolts", LocalDate.of(2025, 5, 2), null, null, null, "https://cdn.marvel.com/content/1x/thunderbolts_lob_crd_03.jpg"));
            marvel.add(new Marvel("The Fantastic Four: First Steps", LocalDate.of(2025, 7, 25), null, null, null, "https://cdn.marvel.com/content/1x/thefantasticfourfirststeps_lob_crd_02.jpg"));
            marvel.add(new Marvel("Avengers: Doomsday", LocalDate.of(2026, 5, 1), null, null, null, "https://cdn.marvel.com/content/1x/avengersdoomsday_lob_crd_01.jpg"));
            marvel.add(new Marvel("Spider-Man: Brand New Day", LocalDate.of(2026, 7, 31), null, null, null, "https://cdn.marvel.com/content/1x/spidermanbrandnewday_lob_crd_01.jpg"));
            marvel.add(new Marvel("Avengers: Secret Wars", LocalDate.of(2027, 5, 7), null, null, null, "https://cdn.marvel.com/content/1x/avengerssecretwars_lob_crd_02.jpg"));
            marvel.add(new Marvel("WandaVision", LocalDate.of(2021, 1, 15), LocalDate.of(2023, 11, 23), null, null, "https://cdn.marvel.com/content/1x/wandavision_lob_crd_06.jpg"));
            marvel.add(new Marvel("Hawkeye", LocalDate.of(2021, 11, 24), LocalDate.of(2024, 12, 1), null, null, "https://cdn.marvel.com/content/1x/hawkeye_lob_crd_04.jpg"));
            marvel.add(new Marvel("Loki: Season 1", LocalDate.of(2021, 6, 9), LocalDate.of(2023, 9, 1), null, null, "https://cdn.marvel.com/content/1x/loki_lob_crd_04.jpg"));
            marvel.add(new Marvel("The Falcon and The Winter Soldier", LocalDate.of(2021, 3, 19), LocalDate.of(2024, 6, 1), null, null, "https://cdn.marvel.com/content/1x/falcws_lob_crd_03.jpg"));
            marvel.add(new Marvel("I Am Groot: Season 1",  LocalDate.of(2022, 8, 10), LocalDate.of(2014, 9, 1), null, null, "https://cdn.marvel.com/content/1x/iamgroot_lob_crd_04.jpg"));
            marvel.add(new Marvel("I Am Groot: Season 2", LocalDate.of(2023, 9, 6), LocalDate.of(2014, 9, 2), null, null, "https://cdn.marvel.com/content/1x/iamgroots2_lob_crd_01.jpg"));
            marvel.add(new Marvel("Loki: Season 2",  LocalDate.of(2023, 10, 5), LocalDate.of(2023, 9, 2), null, null, "https://cdn.marvel.com/content/1x/lokis2_lob_crd_04.jpg"));
            marvel.add(new Marvel("Agatha All Along", LocalDate.of(2024, 9, 18), LocalDate.of(2026, 12, 1), null, null, "https://cdn.marvel.com/content/1x/agathaallalong_lob_crd_02.jpg"));
            marvelRepository.saveAll(marvel);
        }
    }
}