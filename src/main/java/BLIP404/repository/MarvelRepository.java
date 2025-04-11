package BLIP404.repository;

import BLIP404.entity.Marvel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MarvelRepository extends CrudRepository<Marvel, Long> {
    @Query("SELECT m from Marvel m WHERE (m.releaseDate = (select min(m.releaseDate) from Marvel m where m.releaseDate > now()))")
    Marvel findNextRelease();

    @Query("SELECT m from Marvel m WHERE (m.releaseDate > now()) ORDER BY m.releaseDate")
    Iterable<Marvel> findAllUpcoming();

    @Query("SELECT m from Marvel m WHERE (m.releaseDate < now()) ORDER BY m.timelineDate")
    Iterable<Marvel> findChronologically();

    @Query("SELECT m from Marvel m WHERE (m.releaseDate < now()) ORDER BY m.releaseDate")
    Iterable<Marvel> findReleaseOrder();
}
