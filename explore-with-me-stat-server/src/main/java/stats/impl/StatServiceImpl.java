package stats.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import stats.model.Stats;
import stats.model.StatsView;
import stats.repository.StatsRepository;
import stats.service.StatService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StatServiceImpl implements StatService {

    private final StatsRepository repository;

    @Override
    public void saveRequest(Stats stats) {

        repository.save(stats);
    }

    @Override
    public List<StatsView> getStats(LocalDateTime start,
                                    LocalDateTime end,
                                    List<String> uris,
                                    String unique) {
        if (uris == null && unique == null) {
            return repository.getStat(start, end).stream()
                    .map(Optional::get)
                    .toList();
        } else if (uris == null) {
            return repository.getStatUniq(start, end).stream()
                    .map(Optional::get)
                    .toList();
        } else if (unique == null) {
            List<StatsView> statsViews = new ArrayList<>();
            for (String s : uris) {
                StatsView stats = repository.getStatByUri(start, end, s).get();
                statsViews.add(stats);
            }
            return statsViews;
        } else {
            List<StatsView> statsViewsUniq = new ArrayList<>();
            for (String s : uris) {
                StatsView statsView = repository.getStatByUriUniq(start, end, s).get();
                statsViewsUniq.add(statsView);
            }
            return statsViewsUniq;
        }
    }
}
