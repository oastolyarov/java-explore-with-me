package explore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(name = "annotation")
    private String annotation;
    @ManyToOne
    @JoinColumn(name = "initiator")
    private User initiator;
    @Column(name = "date_create")
    private LocalDateTime createdOn;
    @Column(name = "date_published")
    private LocalDateTime publishedOn;
    @Column(name = "date_event")
    private LocalDateTime eventDate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location;
    @Column(name = "paid")
    private Boolean paid;
    private Integer participantLimit;
    @Column(name = "state")
    private State state;
    @Column(name = "request_moderation")
    private Boolean requestModeration;
}
