package explore.controller;

import explore.model.dto.EventCreatingDto;
import explore.model.dto.EventDto;
import explore.model.dto.RequestDto;
import explore.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
@Slf4j
public class PrivateEventController {

    private final EventService eventService;

    @GetMapping("/users/{userId}/events")
    public List<EventDto> getAllByUser(@PathVariable Integer userId,
                                       @RequestParam(defaultValue = "0") Integer from,
                                       @RequestParam(defaultValue = "10") Integer size) {
        log.debug("Приватный запрос на просмотр списка событий пользователя.");
        return eventService.getAllByUser(userId, from, size);
    }

    @PatchMapping("/users/{userId}/events")
    public EventDto changeEventByUser(@PathVariable Integer userId,
                                      @RequestBody EventDto eventDto) throws ValidationException {
        log.debug("Приватный запрос на изменение события пользователя.");

        return eventService.changeEventByUser(userId, eventDto);
    }

    @PostMapping("/users/{userId}/events")
    public EventCreatingDto createEventByUser(@PathVariable Integer userId,
                                              @RequestBody EventCreatingDto eventDto) {
        log.debug("Приватный запрос на создание события пользователем.");

        return eventService.createEventByUser(userId, eventDto);
    }

    @PatchMapping("/users/{userId}/events/{eventId}")
    public void closeEventByUser(@PathVariable Integer userId,
                                 @PathVariable Integer eventId) throws ValidationException {
        log.debug("Приватный запрос на отмену события пользователя.");

        eventService.closeEventByUser(userId, eventId);
    }

    @GetMapping("/users/{userId}/events/{eventId}/requests")
    public List<RequestDto> getRequestByUser(@PathVariable Integer userId,
                                             @PathVariable Integer eventId) {
        log.debug("Запрос от пользователя на просмотр запросов на события.");

        return eventService.getRequestByUser(userId, eventId);
    }

    @PatchMapping("/users/{userId}/events/{eventId}/requests/{reqId}/confirm")
    public void acceptRequestByUser(@PathVariable Integer userId,
                                    @PathVariable Integer eventId,
                                    @PathVariable Integer reqId) {
        log.debug("Приватный запрос на подтверждение участия в событии.");

        eventService.acceptRequestByUser(userId, eventId, reqId);
    }

    @PatchMapping("/users/{userId}/events/{eventId}/requests/{reqId}/reject")
    public void rejectRequestByUser(@PathVariable Integer userId,
                                    @PathVariable Integer eventId,
                                    @PathVariable Integer reqId) {
        log.debug("Приватный запрос на отклонение участия в событии.");

        eventService.rejectRequestByUser(userId, eventId, reqId);
    }
}