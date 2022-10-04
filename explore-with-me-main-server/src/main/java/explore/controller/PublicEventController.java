package explore.controller;

import explore.model.dto.CompilationDto;
import explore.model.dto.EventDto;
import explore.model.dto.EventShortDto;
import explore.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
@Slf4j
public class PublicEventController {

    private final EventService eventService;

    @GetMapping("/events")
    public List<EventShortDto> getEvents(@RequestParam(required = false) String text,
                                         @RequestParam(required = false) Integer categoryId,
                                         @RequestParam(required = false) Boolean paid,
                                         @RequestParam(required = false) String rangeStart,
                                         @RequestParam(required = false) String rangeEnd,
                                         @RequestParam(required = false) Boolean onlyAvailable,
                                         @RequestParam(required = false) String sort,
                                         @RequestParam(defaultValue = "0", required = false) Integer from,
                                         @RequestParam(defaultValue = "10", required = false) Integer size,
                                         HttpServletRequest request) {
        log.debug("Публичный запрос на просмотр всех событий.");

        return eventService.getEvents(text, categoryId, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size, request);
    }

    @GetMapping("/events/{id}")
    public EventDto getEventById(@PathVariable Integer id,
                                 HttpServletRequest request) {
        log.debug("Публичный запрос на просмотр события по id " + id);

        return eventService.getEventById(id, request);
    }

    @GetMapping("/compilations")
    public List<EventDto> getCompilation(@RequestParam Boolean pinned,
                                         @RequestParam(defaultValue = "0") Integer from,
                                         @RequestParam(defaultValue = "10") Integer size) {
        log.debug("Публичный запрос на просмотр подборок.");

        return eventService.getCompilation(pinned, from, size);
    }

    @GetMapping("/compilations/{id}")
    public CompilationDto getCompilationById(@PathVariable Integer id) {
        log.debug("Публичный запрос на просмотр подборки по id " + id);

        return eventService.getCompilationById(id);
    }
}
