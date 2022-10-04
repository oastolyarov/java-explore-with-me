package explore.model.dto;

import explore.model.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompilationDto {
    private Integer id;
    private List<Event> events;
    private Boolean pinned;
    private String title;
}
