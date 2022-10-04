package explore.controller;

import explore.model.dto.RequestDto;
import explore.service.RequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/categories")
@Slf4j
public class PrivateRequestController {

    private final RequestService requestService;

    @GetMapping("/users/{userId}/requests")
    public List<RequestDto> getUserRequests(@PathVariable Integer userId) {

        log.debug("Приватный запрос на получение запросов пользователя.");

        return requestService.getUserRequests(userId);
    }

    @PostMapping("/users/{userId}/requests")
    public RequestDto addRequest(@PathVariable Integer userId,
                                 @RequestParam Integer eventId) {
        log.debug("Приватный запрос на добавление запроса на участие в событии.");

        return requestService.addRequest(userId, eventId);
    }

    @PatchMapping("/users/{userId}/requests/{requestId}/cancel")
    public RequestDto cancelRequest(@PathVariable Integer userId,
                                    @PathVariable Integer requestId) throws ValidationException {
        log.debug("Приватный запрос на удаление запроса на участие в событии.");

        return requestService.cancelRequest(userId, requestId);
    }
}
