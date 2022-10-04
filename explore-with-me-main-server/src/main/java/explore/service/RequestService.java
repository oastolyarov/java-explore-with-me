package explore.service;

import explore.model.dto.RequestDto;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface RequestService {
    List<RequestDto> getUserRequests(Integer userId);

    RequestDto addRequest(Integer userId, Integer eventId);

    RequestDto cancelRequest(Integer userId, Integer requestId) throws ValidationException;
}
