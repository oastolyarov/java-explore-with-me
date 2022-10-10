package explore.model.dto;

import explore.model.Event;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompilationDto {
    private Integer id;
    private List<Event> events;
    private Boolean pinned;
    private String title;
}
