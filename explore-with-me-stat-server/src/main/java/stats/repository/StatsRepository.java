package stats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import stats.model.Stats;
import stats.model.StatsView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface StatsRepository extends JpaRepository<Stats, Integer> {
    @Query("select s.app, s.uri, count (s.id)" +
            "from Stats s " +
            "where s.timestamp between ?1 and ?2" +
            " group by s.app, s.uri")
    List<Optional<StatsView>> getStat(LocalDateTime start, LocalDateTime end);

    @Query("select s.app, s.uri, count (s.id) from Stats s " +
            "where s.timestamp between ?1 and ?2" +
            "and s.uri = ?3 group by s.app, s.uri")
    Optional<StatsView> getStatByUri(LocalDateTime start, LocalDateTime end, String uri);

    @Query("select s.app, s.uri, count (distinct s.ip) from Stats s " +
            "where s.timestamp between ?1 and ?2" +
            "group by s.app, s.uri")
    List<Optional<StatsView>> getStatUniq(LocalDateTime start, LocalDateTime end);

    @Query("select s.app, s.uri, count (distinct s.ip) from Stats s " +
            "where s.timestamp between ?1 and ?2" +
            "and s.uri = ?3 group by s.app, s.uri")
    Optional<StatsView> getStatByUriUniq(LocalDateTime start, LocalDateTime end, String uri);

}
