package explore.model.dto;

import explore.model.Event;
import explore.model.User;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class RequestDto {
    private Integer id;
    private Event event;
    private LocalDateTime created;
    private User requester;
}
