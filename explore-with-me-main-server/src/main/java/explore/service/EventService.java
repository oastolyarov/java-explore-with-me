package explore.service;

import explore.model.Event;
import explore.model.dto.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;
import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    List<EventDto> getAllByParams(Integer userId,
                                  String states,
                                  String categories,
                                  LocalDateTime rangeStart,
                                  LocalDateTime rangeEnd,
                                  String sort,
                                  Integer from,
                                  Integer size) throws ValidationException;

    EventDto changeEventByAdmin(Integer eventId, EventEditedDto eventEditedDto);

    EventDto publishEvent(Integer eventId) throws ValidationException;

    EventDto rejectEvent(Integer eventId) throws ValidationException;

    CompilationDto createCompilation(CompilationShortDto compilationShortDto);

    void deleteCompilation(Integer compId);

    CompilationDto deleteEventFromCompilation(Integer compId,
                                              Integer eventId);

    CompilationDto addEventToCompilation(Integer compId,
                                         Integer eventId);

    void unpinCompilation(Integer compId);

    void pinCompilation(Integer compId);

    List<Event> getAllByUser(Integer userId,
                             Integer from,
                             Integer size);

    EventDto changeEventByUser(Integer userId, EventDto eventDto) throws ValidationException;

    EventCreatingDto createEventByUser(Integer userId, EventCreatingDto eventCreatingDto);

    void closeEventByUser(Integer userId,
                          Integer eventId) throws ValidationException;

    List<RequestDto> getRequestByUser(Integer userId,
                                      Integer eventId);

    void acceptRequestByUser(Integer userId,
                             Integer eventId,
                             Integer reqId);

    void rejectRequestByUser(Integer userId,
                             Integer eventId,
                             Integer reqId);

    List<EventShortDto> getEvents(String text,
                                  Integer categoryId,
                                  Boolean paid,
                                  String rangeStart,
                                  String rangeEnd,
                                  Boolean onlyAvailable,
                                  String sort,
                                  Integer from,
                                  Integer size,
                                  HttpServletRequest request);

    EventDto getEventById(Integer id, HttpServletRequest request);

    List<EventDto> getCompilation(Boolean pinned,
                                  Integer from,
                                  Integer size);

    CompilationDto getCompilationById(Integer id);
}
