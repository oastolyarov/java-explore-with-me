package stats.model;

import lombok.Data;

@Data
public class StatsView {
    private String app;
    private String uri;
    private Integer hits;
}
