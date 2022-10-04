package explore.repository;

import explore.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

public interface EventRepository extends JpaRepository<Event, Integer> {
    @Transactional
    @Modifying
    @Query("update Event e set e.state = ?1 where e.id = ?2")
    void updateEventStatus(State state, Integer eventId);

    @Transactional
    @Modifying
    @Query("update Event e set e.title = ?1, e.category = ?2," +
            "e.description = ?3, e.annotation = ?4, e.eventDate = ?5," +
            "e.location.id = ?6, e.paid = ?7, e.participantLimit = ?8," +
            "e.requestModeration = ?9 where e.id = ?10")
    void updateEvent(String title, Integer id, String description, String annotation,
                     LocalDateTime eventDate, Integer locationId, Boolean paid,
                     Integer participantLimit, Boolean requestModeration, Integer eventId);

    @Transactional
    @Modifying
    @Query("update Event e set e.title = ?1, e.description = ?2, e.category = ?3, e.annotation = ?4," +
            "e.initiator = ?5, e.createdOn = ?6, e.publishedOn = ?7, e.eventDate = ?8, e.location = ?9," +
            "e.paid = ?10, e.participantLimit = ?11, e.state = ?12, e.requestModeration = ?13" +
            "where e.id = ?14")
    void updateEventByUser(String title, String description, Category category, String annotation,
                           User initiator, LocalDateTime createdOn, LocalDateTime publishedOn,
                           LocalDateTime eventDate, Location location, Boolean paid, Integer participantLimit,
                           State state, Boolean requestModeration, Integer eventId);
}
