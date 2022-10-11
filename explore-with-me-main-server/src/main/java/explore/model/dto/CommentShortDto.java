package explore.model.dto;

import explore.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CommentShortDto {
    private Integer id;
    private String comment;
    private User commentator;
    private LocalDateTime dateCreate;
}
