package explore.client;


import explore.client.BaseClient;
import explore.model.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StatsClient extends BaseClient {
    private static final String API_PREFIX = "";

    @Autowired
    public StatsClient(@Value("${explore-with-me-stat-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .build()
        );
    }

    public void saveRequest(Stats stats) {
        post("/hit", stats);
    }

    public Object getStats(LocalDateTime start,
                           LocalDateTime end,
                           List<String> uris,
                           String unique) {
        return get("/stats?start=" + start + "&end=" + end + "&uris=" + uris + "&unique=" + unique);
    }
}