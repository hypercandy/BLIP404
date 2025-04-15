package BLIP404.repository;

import BLIP404.entity.Marvel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MarvelRepository extends CrudRepository<Marvel, Long> {
    @Query("SELECT m from Marvel m WHERE (m.releaseDate = (select min(m.releaseDate) from Marvel m where m.releaseDate > now()))")
    Marvel findNextRelease();

    @Query("SELECT m from Marvel m WHERE (m.releaseDate > now()) ORDER BY m.releaseDate")
    Iterable<Marvel> findAllUpcoming();

    @Query("SELECT m from Marvel m WHERE (m.releaseDate < now() AND m.timelineDate IS NOT NULL) ORDER BY m.timelineDate")
    Page<Marvel> findChronologically(Pageable page);

    @Query("SELECT m from Marvel m WHERE (m.releaseDate < now()) ORDER BY m.releaseDate")
    Page<Marvel> findReleaseOrder(Pageable page);
}
