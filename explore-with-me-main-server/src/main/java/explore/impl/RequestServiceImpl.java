package explore.impl;

import explore.mapper.RequestMapper;
import explore.model.Event;
import explore.model.Request;
import explore.model.User;
import explore.model.dto.RequestDto;
import explore.repository.EventRepository;
import explore.repository.RequestRepository;
import explore.repository.UserRepository;
import explore.service.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Override
    public List<RequestDto> getUserRequests(Integer userId) {

        return requestRepository.getUserRequests(userId).stream()
                .map(RequestMapper::toRequestDto)
                .collect(Collectors.toList());
    }

    @Override
    public RequestDto addRequest(Integer userId, Integer eventId) {
        Event event = eventRepository.findById(eventId).get();
        User user = userRepository.findById(userId).get();
        Request newRequest = new Request(0, event, LocalDateTime.now(), user, "PENDING");

        Request request = requestRepository.save(newRequest);

        return RequestMapper.toRequestDto(request);
    }

    @Override
    public RequestDto cancelRequest(Integer userId, Integer requestId) throws ValidationException {
        Request request = requestRepository.findById(requestId).get();

        if (!Objects.equals(request.getRequester().getId(), userId)) {
            throw new ValidationException("Запрос с id "
                    + requestId
                    + " не принадлежит пользователю с id "
                    + userId + ".");
        }
        requestRepository.setStateById("CANCELED", requestId);

        return RequestMapper.toRequestDto(request);
    }
}
