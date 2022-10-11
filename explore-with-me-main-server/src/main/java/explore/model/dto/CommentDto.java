package explore.model.dto;

import explore.model.Event;
import explore.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CommentDto {
    private Integer id;
    private String comment;
    private Event event;
    private User commentator;
    private LocalDateTime dateCreate;
}
