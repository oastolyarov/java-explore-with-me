package explore.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatsView {
    private String app;
    private String uri;
    private Integer hits;
}
