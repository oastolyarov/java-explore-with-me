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
            "e.lat = ?6, e.lon = ?7, e.paid = ?8, e.participantLimit = ?9," +
            "e.requestModeration = ?10 where e.id = ?11")
    void updateEvent(String title, Integer id, String description, String annotation,
                     LocalDateTime eventDate, Double lat, Double lon, Boolean paid,
                     Integer participantLimit, Boolean requestModeration, Integer eventId);

    @Transactional
    @Modifying
    @Query("update Event e set e.title = ?1, e.description = ?2, e.category = ?3, e.annotation = ?4," +
            "e.initiator = ?5, e.createdOn = ?6, e.publishedOn = ?7, e.eventDate = ?8, e.lat = ?9, e.lon = ?10, " +
            "e.paid = ?11, e.participantLimit = ?12, e.state = ?13, e.requestModeration = ?14" +
            "where e.id = ?15")
    void updateEventByUser(String title, String description, Category category, String annotation,
                           User initiator, LocalDateTime createdOn, LocalDateTime publishedOn,
                           LocalDateTime eventDate, Double lat, Double lon, Boolean paid, Integer participantLimit,
                           State state, Boolean requestModeration, Integer eventId);
}
