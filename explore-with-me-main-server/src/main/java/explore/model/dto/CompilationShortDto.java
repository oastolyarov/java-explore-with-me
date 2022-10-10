package explore.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompilationShortDto {
    private Integer id;
    private List<Integer> events;
    private Boolean pinned;
    private String title;
}
