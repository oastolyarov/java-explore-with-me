package explore.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import explore.model.Category;
import explore.model.Location;
import explore.model.State;
import explore.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {

    private Integer id;
    private String title;
    private String description;
    private Category category;
    private String annotation;
    private User initiator;
    private String createdOn;
    private String publishedOn;
    private String eventDate;
    private Location location;
    private Boolean paid;
    private Integer participantLimit;
    private State state;
    private Integer confirmedRequests;
    private Boolean requestModeration;
    private Integer views;
}
