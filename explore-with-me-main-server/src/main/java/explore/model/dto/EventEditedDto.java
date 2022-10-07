package explore.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
