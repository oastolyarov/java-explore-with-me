package stats.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import stats.model.Stats;
import stats.model.StatsView;
import stats.service.StatService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class StatsController {

    private final StatService service;

    @PostMapping("/hit")
    public void saveRequest(@RequestBody Stats stats) {
        log.debug("Запрос на просмотр события.");
        service.saveRequest(stats);
    }

    @GetMapping("/stats")
    public List<StatsView> getStats(LocalDateTime start,
                                    LocalDateTime end,
                                    List<String> uris,
                                    String unique) {
        log.debug("Запрос на просмотр статистики.");
        return service.getStats(start, end, uris, unique);
    }
}
