package explore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    @Column(name = "date_create")
    private LocalDateTime created;
    @ManyToOne
    @JoinColumn(name = "requestor_id")
    private User requester;
    @Column(name = "status")
    private String status;
}
