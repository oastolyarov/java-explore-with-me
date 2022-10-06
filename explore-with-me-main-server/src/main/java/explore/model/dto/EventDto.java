package explore.model.dto;

import explore.model.Category;
import explore.model.State;
import explore.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Double lat;
    private Double lon;
    private Boolean paid;
    private Integer participantLimit;
    private State state;
    private Integer confirmedRequests;
    private Boolean requestModeration;
    private Integer views;
}
