package explore.controller;

import explore.client.StatsClient;
import explore.model.dto.CompilationDto;
import explore.model.dto.CompilationShortDto;
import explore.model.dto.EventDto;
import explore.model.dto.EventEditedDto;
import explore.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
@Slf4j
public class AdminEventController {

    private final EventService eventService;
    private final StatsClient statsClient;

    @GetMapping("/admin/events")
    public List<EventDto> getAllByParams(@RequestParam Integer userId,
                                         @RequestParam String states,
                                         @RequestParam String categories,
                                         @RequestParam LocalDateTime rangeStart,
                                         @RequestParam LocalDateTime rangeEnd,
                                         @RequestParam(defaultValue = "0") Integer from,
                                         @RequestParam(defaultValue = "10") Integer size) {
        log.debug("Администратор: запрос на просмотр всех событий.");

        return eventService.getAllByParams(userId, states, categories, rangeStart, rangeEnd, from, size);
    }

    @PutMapping("/admin/events/{eventId}")
    public EventDto changeEventByAdmin(@PathVariable Integer eventId,
                                       @RequestBody EventEditedDto eventEditedDto) {
        log.debug("Администратор: запрос на изменения события по id " + eventId);

        return eventService.changeEventByAdmin(eventId, eventEditedDto);
    }

    @PatchMapping("/admin/events/{eventId}/publish")
    public EventDto publishEvent(@PathVariable Integer eventId) throws ValidationException {
        log.debug("Администратор: публикация события.");

        return eventService.publishEvent(eventId);
    }

    @PatchMapping("/admin/events/{eventId}/reject")
    public EventDto rejectEvent(@PathVariable Integer eventId) throws ValidationException {
        log.debug("Администратор: отклонение события.");

        return eventService.rejectEvent(eventId);
    }

    @PostMapping("/admin/compilations")
    public CompilationDto createCompilation(@RequestBody CompilationShortDto compilationShortDto) {
        log.debug("Администратор: запрос на создание подборки.");

        return eventService.createCompilation(compilationShortDto);
    }

    @DeleteMapping("/admin/compilations/{compId}")
    public void deleteCompilation(@PathVariable Integer compId) {
        log.debug("Администратор: удаление подборки по id " + compId);

        eventService.deleteCompilation(compId);
    }

    @DeleteMapping("/admin/compilations/{compId}/events/{eventId}")
    public CompilationDto deleteEventFromCompilation(@PathVariable Integer compId,
                                                     @PathVariable Integer eventId) {
        log.debug(String.format("Администратор: запрос на удаление события eventId = %d из подборки compId = %d",
                compId,
                eventId));

        return eventService.deleteEventFromCompilation(compId, eventId);
    }

    @PatchMapping("/admin/compilations/{compId}/events/{eventId}")
    public CompilationDto addEventToCompilation(@PathVariable Integer compId,
                                                @PathVariable Integer eventId) {
        log.debug(String.format("Администратор: запрос на добавление событя eventId = %d в подборку compId = %d",
                compId,
                eventId));

        return eventService.addEventToCompilation(compId, eventId);
    }

    @DeleteMapping("/admin/compilations/{compId}/pin")
    public void unpinCompilation(@PathVariable Integer compId) {
        log.debug("Администратор: запрос на открепление подборки.");

        eventService.unpinCompilation(compId);
    }

    @PatchMapping("/admin/compilations/{compId}/pin")
    public void pinCompilation(@PathVariable Integer compId) {
        log.debug("Администратор: запрос на закрепление подборки.");

        eventService.pinCompilation(compId);
    }

    @GetMapping
    public Object getStats(@RequestParam LocalDateTime start,
                           @RequestParam LocalDateTime end,
                           @RequestParam List<String> uris,
                           @RequestParam String unique) {
        return statsClient.getStats(start, end, uris, unique);
    }
}
