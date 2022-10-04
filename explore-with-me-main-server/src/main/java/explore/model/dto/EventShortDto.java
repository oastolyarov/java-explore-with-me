package explore.model.dto;

import explore.model.Category;
import explore.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventShortDto {

    private Integer id;
    private String title;
    private Category category;
    private String annotation;
    private User initiator;
    private String eventDate;
    private Boolean paid;
    private Integer confirmedRequests;
    private Integer views;
}
