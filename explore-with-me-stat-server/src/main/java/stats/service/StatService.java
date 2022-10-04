package stats.service;

import stats.model.Stats;
import stats.model.StatsView;

import java.time.LocalDateTime;
import java.util.List;

public interface StatService {
    void saveRequest(Stats stats);

    List<StatsView> getStats(LocalDateTime start,
                             LocalDateTime end,
                             List<String> uris,
                             String unique);
}
