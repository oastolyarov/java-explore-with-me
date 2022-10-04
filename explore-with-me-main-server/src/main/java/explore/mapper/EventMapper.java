package explore.mapper;

import explore.model.Category;
import explore.model.Event;
import explore.model.State;
import explore.model.User;
import explore.model.dto.EventCreatingDto;
import explore.model.dto.EventDto;
import explore.model.dto.EventShortDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventMapper {

    public static EventDto toEventDto(Event event) {
        return new EventDto(event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getCategory(),
                event.getAnnotation(),
                event.getInitiator(),
                event.getCreatedOn().toString(),
                event.getPublishedOn().toString(),
                event.getEventDate().toString(),
                event.getLocation(),
                event.getPaid(),
                event.getParticipantLimit(),
                event.getState(),
                null,
                event.getRequestModeration(),
                null);
    }

    public static EventShortDto toEventSnortDto(Event event) {
        return new EventShortDto(event.getId(),
                event.getTitle(),
                event.getCategory(),
                event.getAnnotation(),
                event.getInitiator(),
                event.getEventDate().toString(),
                event.getPaid(),
                event.getParticipantLimit(),
                null);
    }

    public static EventCreatingDto toEventCreatingDto(Event event) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatEventDate = event.getEventDate().format(formatter);
        return new EventCreatingDto(event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getCategory().getId(),
                event.getAnnotation(),
                formatEventDate,
                event.getLocation(),
                event.getPaid(),
                event.getParticipantLimit(),
                event.getRequestModeration());
    }

    public static Event toEvent(EventCreatingDto eventCreatingDto, User user, Category category) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime anotherDateTime = LocalDateTime.parse(eventCreatingDto.getEventDate(), formatter);

        return new Event(eventCreatingDto.getId(),
                eventCreatingDto.getTitle(),
                eventCreatingDto.getDescription(),
                category,
                eventCreatingDto.getAnnotation(),
                user,
                LocalDateTime.now(),
                null,
                anotherDateTime,
                eventCreatingDto.getLocation(),
                eventCreatingDto.getPaid(),
                eventCreatingDto.getParticipantLimit(),
                State.PENDING,
                eventCreatingDto.getRequestModeration());
    }

    public static Event toEvent(EventDto eventDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime createdDateTime = LocalDateTime.parse(eventDto.getCreatedOn(), formatter);
        LocalDateTime publishedDateTime = LocalDateTime.parse(eventDto.getPublishedOn(), formatter);
        LocalDateTime eventDateTime = LocalDateTime.parse(eventDto.getEventDate(), formatter);

        return new Event(eventDto.getId(),
                eventDto.getTitle(),
                eventDto.getDescription(),
                eventDto.getCategory(),
                eventDto.getAnnotation(),
                eventDto.getInitiator(),
                createdDateTime,
                publishedDateTime,
                eventDateTime,
                eventDto.getLocation(),
                eventDto.getPaid(),
                eventDto.getParticipantLimit(),
                eventDto.getState(),
                eventDto.getRequestModeration());
    }
}
