package explore.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventEditedDto {

    private Integer id;
    private String title;
    private Integer category;
    private String description;
    private String annotation;
    private String eventDate;
    private Double lat;
    private Double lon;
    private Boolean paid;
    private Integer participantLimit;
    private Boolean requestModeration;
}
