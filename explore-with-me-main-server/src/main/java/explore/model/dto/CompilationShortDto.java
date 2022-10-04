package explore.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompilationShortDto {
    private Integer id;
    private List<Integer> events;
    private Boolean pinned;
    private String title;
}
