package BLIP404.repository;
import BLIP404.entity.Upcoming;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpcomingRepository extends CrudRepository<Upcoming, Long> {
    @Query("SELECT u from Upcoming u WHERE (u.date = (select min(u.date) from Upcoming u))")
    Upcoming findMinDate();
}
