package explore.impl;

import explore.client.StatsClient;
import explore.mapper.CompilationMapper;
import explore.mapper.EventMapper;
import explore.mapper.RequestMapper;
import explore.model.*;
import explore.model.dto.*;
import explore.repository.*;
import explore.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final RequestRepository requestRepository;
    private final LocationRepository locationRepository;
    private final CompilationRepository compilationRepository;
    private final JdbcTemplate jdbcTemplate;
    private final StatsClient client;

    @Override
    public List<EventDto> getAllByParams(Integer userId,
                                         String states,
                                         String categories,
                                         LocalDateTime rangeStart,
                                         LocalDateTime rangeEnd,
                                         Integer from,
                                         Integer size) {

        return null;
    }

    @Override
    public EventDto changeEventByAdmin(Integer eventId, EventEditedDto eventEditedDto) {
        Event event = (Event) checkOptional(eventRepository.findById(eventId));

        if (event == null) {
            throw new NullPointerException("Событие с id " + eventId + " не найдено.");
        }

        Category category = (Category) checkOptional(categoryRepository.findById(eventEditedDto.getCategory()));

        if (category == null) {
            throw new NullPointerException("Категория с id " + eventEditedDto.getCategory() + " не найдена.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime eventDateTime = LocalDateTime.parse(eventEditedDto.getEventDate(), formatter);

        eventRepository.updateEvent(event.getTitle(),
                eventEditedDto.getCategory(),
                eventEditedDto.getDescription(),
                event.getAnnotation(),
                eventDateTime,
                eventEditedDto.getLat(),
                eventEditedDto.getLon(),
                eventEditedDto.getPaid(),
                eventEditedDto.getParticipantLimit(),
                eventEditedDto.getRequestModeration(),
                eventId);

        Event updatedEvent = eventRepository.findById(eventId).get();

        return EventMapper.toEventDto(updatedEvent);
    }

    @Override
    public EventDto publishEvent(Integer eventId) throws ValidationException {

        Event event = (Event) checkOptional(eventRepository.findById(eventId));

        if (event == null) {
            throw new NullPointerException("Событие не найдено.");
        }

        if (!event.getState().equals(State.PENDING)) {
            throw new ValidationException("Событие должно быть в статусе PENDING");
        }

        Duration timeToEvent = Duration.between(event.getEventDate(), LocalDateTime.now());

        if (timeToEvent.toHours() < 1) {
            throw new ValidationException("Время до события меньше часа.");
        }

        eventRepository.updateEventStatus(State.PUBLISHED, eventId);

        event.setState(State.PUBLISHED);

        return EventMapper.toEventDto(event);
    }

    @Override
    public EventDto rejectEvent(Integer eventId) throws ValidationException {
        Event event = (Event) checkOptional(eventRepository.findById(eventId));

        if (event == null) {
            throw new NullPointerException("Событие не найдено.");
        }

        if (event.getState().equals(State.PUBLISHED)) {
            throw new ValidationException("Событие не должно быть в опубликованно.");
        }

        eventRepository.updateEventStatus(State.CANCELED, eventId);

        event.setState(State.CANCELED);

        return EventMapper.toEventDto(event);
    }

    @Override
    public CompilationDto createCompilation(CompilationShortDto compilationShortDto) {
        List<Event> events = new ArrayList<>();

        for (Integer eventId : compilationShortDto.getEvents()) {
            events.add(eventRepository.findById(eventId).get());
        }

        Compilation compilation = new Compilation();

        compilation.setEvents(events);
        compilation.setTitle(compilationShortDto.getTitle());
        compilation.setPinned(compilationShortDto.getPinned());

        compilationRepository.save(compilation);

        return CompilationMapper.toCompilationDto(compilation);
    }

    @Override
    public void deleteCompilation(Integer compId) {
        compilationRepository.deleteById(compId);
    }

    @Override
    public CompilationDto deleteEventFromCompilation(Integer compId, Integer eventId) {
        String sqlDeleteEvent = "DELETE FROM event_to_compilation " +
                "WHERE compilation_id = ? AND event_id = ?";

        jdbcTemplate.update(sqlDeleteEvent,
                compId, eventId);

        return getCompilationById(compId);
    }

    @Override
    public CompilationDto addEventToCompilation(Integer compId, Integer eventId) {
        String sqlInsertEvent = "INSERT INTO event_to_compilation (event_id, compilation_id) " +
                "values (?, ?)";

        jdbcTemplate.update(sqlInsertEvent, eventId, compId);

        return getCompilationById(compId);
    }

    @Override
    public void unpinCompilation(Integer compId) {
        compilationRepository.toPin(false, compId);
    }

    @Override
    public void pinCompilation(Integer compId) {
        compilationRepository.toPin(true, compId);
    }

    @Override
    public List<EventDto> getAllByUser(Integer userId, Integer from, Integer size) {
        List<Event> allEvents = eventRepository.findAll();

        List<EventDto> events = allEvents.stream()
                .filter(s -> Objects.equals(s.getInitiator().getId(), userId))
                .map(EventMapper::toEventDto)
                .collect(Collectors.toList());

        return events;
    }

    @Override
    public EventDto changeEventByUser(Integer userId, EventDto eventDto) throws ValidationException {
        Event event = (Event) checkOptional(eventRepository.findById(eventDto.getId()));

        if (event == null) {
            throw new NullPointerException("Событие с id " + eventDto.getId() + " не найдено.");
        }

        if (!Objects.equals(event.getInitiator().getId(), userId)) {
            throw new ValidationException(String
                    .format("Событие id %d не принадлежит пользователю с id %d", eventDto.getId(), userId));
        }

        // чтобы не создавать отдельный конвертер для форматирования строки в дату
        // сделан перевод из EventDto в Event через mapper
        Event intermediateEvent = EventMapper.toEvent(eventDto);

        eventRepository.updateEventByUser(intermediateEvent.getTitle(),
                intermediateEvent.getDescription(),
                intermediateEvent.getCategory(),
                intermediateEvent.getAnnotation(),
                intermediateEvent.getInitiator(),
                intermediateEvent.getCreatedOn(),
                intermediateEvent.getPublishedOn(),
                intermediateEvent.getEventDate(),
                intermediateEvent.getLat(),
                intermediateEvent.getLon(),
                intermediateEvent.getPaid(),
                intermediateEvent.getParticipantLimit(),
                intermediateEvent.getState(),
                intermediateEvent.getRequestModeration(),
                event.getId());

        return EventMapper.toEventDto(intermediateEvent);
    }

    @Override
    public EventCreatingDto createEventByUser(Integer userId, EventCreatingDto eventCreatingDto) {
        User user = (User) checkOptional(userRepository.findById(userId));

        if (user == null) {
            throw new NullPointerException(String.format("Пользователь с id %d не найден.", userId));
        }

        Category category = (Category) checkOptional(categoryRepository.findById(eventCreatingDto.getCategory()));

        if (category == null) {
            throw new NullPointerException("Категория не найдена");
        }

        eventCreatingDto.setId(0);
        Event event = eventRepository.save(EventMapper.toEvent(eventCreatingDto, user, category));
        return EventMapper.toEventCreatingDto(event);
    }

    @Override
    public void closeEventByUser(Integer userId, Integer eventId) throws ValidationException {
        Event event = (Event) checkOptional(eventRepository.findById(eventId));

        assert event != null;
        if (!Objects.equals(event.getInitiator().getId(), userId)) {
            throw new ValidationException(String
                    .format("Событие id %d не принадлежит пользователю с id %d", eventId, userId));
        }

        eventRepository.updateEventStatus(State.CANCELED, eventId);
    }

    @Override
    public List<RequestDto> getRequestByUser(Integer userId, Integer eventId) {

        List<Request> requests = requestRepository.getRequestByUser(userId, eventId);

        return requests.stream().map(RequestMapper::toRequestDto).collect(Collectors.toList());
    }

    @Override
    public void acceptRequestByUser(Integer userId, Integer eventId, Integer reqId) {

        requestRepository.setStateById("ACCEPTED", reqId);
    }

    @Override
    public void rejectRequestByUser(Integer userId, Integer eventId, Integer reqId) {

        requestRepository.setStateById("REJECTED", reqId);
    }

    @Override
    public List<EventShortDto> getEvents(String text,
                                         Integer categoryId,
                                         Boolean paid,
                                         String rangeStart,
                                         String rangeEnd,
                                         Boolean onlyAvailable,
                                         String sort,
                                         Integer from,
                                         Integer size,
                                         HttpServletRequest request) {

        Stats stats = new Stats(0,
                "explore-with-me",
                request.getRequestURI(),
                request.getRemoteAddr(),
                LocalDateTime.now());


        client.saveRequest(stats);

        return eventRepository.findAll().stream()
                .map(EventMapper::toEventSnortDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventDto getEventById(Integer id, HttpServletRequest request) {
        Event event = (Event) checkOptional(eventRepository.findById(id));

        if (event == null) {
            throw new NullPointerException("Такое событие не найдено.");
        }


        Stats stats = new Stats(0,
                "explore-with-me",
                request.getRequestURI(),
                request.getRemoteAddr(),
                LocalDateTime.now());

        client.saveRequest(stats);

        return EventMapper.toEventDto(event);
    }

    @Override
    public List<EventDto> getCompilation(Boolean pinned, Integer from, Integer size) {
        List<EventDto> allEventList = eventRepository.findAll().stream()
                .map(EventMapper::toEventDto)
                .collect(Collectors.toList());
        List<EventDto> eventList = new ArrayList<>();

        if (allEventList.size() < from) {
            throw new IndexOutOfBoundsException("Не верно заданы параметры пагинации.");
        }

        int sum = from + size - 1;

        if (sum > allEventList.size()) {
            sum = allEventList.size();
        }

        for (int i = from; i < sum; i++) {
            eventList.add(allEventList.get(i));
        }

        return eventList;
    }

    @Override
    public CompilationDto getCompilationById(Integer id) {
        Compilation compilation = (Compilation) checkOptional(compilationRepository.findById(id));

        if (compilation == null) {
            throw new NullPointerException("Подборка с id " + id + " не найдена.");
        }

        return CompilationMapper.toCompilationDto(compilation);
    }

    private Object checkOptional(Optional optional) {
        if (optional.isPresent()) {
            return optional.get();
        } else return null;
    }
}
