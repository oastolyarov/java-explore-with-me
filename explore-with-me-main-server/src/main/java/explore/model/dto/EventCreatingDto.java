package explore.model.dto;

import explore.model.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventCreatingDto {

    private Integer id;
    private String title;
    private String description;
    private Integer category;
    private String annotation;
    private String eventDate;
    private Double lat;
    private Double lon;
    private Boolean paid;
    private Integer participantLimit;
    private Boolean requestModeration;
}
