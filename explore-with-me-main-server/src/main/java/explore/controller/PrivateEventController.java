package explore.controller;

import explore.mapper.EventMapper;
import explore.model.dto.EventCreatingDto;
import explore.model.dto.EventDto;
import explore.model.dto.RequestDto;
import explore.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

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
        log.debug("Приватный запрос на просмотр списка событий пользователя с id " + userId);

        return eventService.getAllByUser(userId, from, size).stream()
                .map(EventMapper::toEventDto)
                .collect(Collectors.toList());
    }

    @PatchMapping("/users/{userId}/events")
    public EventDto changeEventByUser(@PathVariable Integer userId,
                                      @RequestBody EventDto eventDto) throws ValidationException {
        log.debug("Приватный запрос на изменение события пользователя c id " + userId);

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
        log.debug("Приватный запрос на отмену события пользователя с id " + eventId);

        eventService.closeEventByUser(userId, eventId);
    }

    @GetMapping("/users/{userId}/events/{eventId}/requests")
    public List<RequestDto> getRequestByUser(@PathVariable Integer userId,
                                             @PathVariable Integer eventId) {
        log.debug("Запрос от пользователя на просмотр запросов на событие с id " + eventId);

        return eventService.getRequestByUser(userId, eventId);
    }

    @PatchMapping("/users/{userId}/events/{eventId}/requests/{reqId}/confirm")
    public void acceptRequestByUser(@PathVariable Integer userId,
                                    @PathVariable Integer eventId,
                                    @PathVariable Integer reqId) {
        log.debug("Приватный запрос на подтверждение участия в событии с id " + eventId);

        eventService.acceptRequestByUser(userId, eventId, reqId);
    }

    @PatchMapping("/users/{userId}/events/{eventId}/requests/{reqId}/reject")
    public void rejectRequestByUser(@PathVariable Integer userId,
                                    @PathVariable Integer eventId,
                                    @PathVariable Integer reqId) {
        log.debug("Приватный запрос на отклонение участия в событии с id " + eventId);

        eventService.rejectRequestByUser(userId, eventId, reqId);
    }
}