package explore.model.dto;

import explore.model.Event;
import explore.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RequestDto {
    private Integer id;
    private Event event;
    private LocalDateTime created;
    private User requester;
}
