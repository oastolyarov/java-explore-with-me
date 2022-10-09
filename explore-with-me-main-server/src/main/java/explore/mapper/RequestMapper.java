package explore.mapper;

import explore.model.Request;
import explore.model.dto.RequestDto;
import org.springframework.stereotype.Component;

@Component
public class RequestMapper {
    public static RequestDto toRequestDto(Request request) {
        return new RequestDto(request.getId(),
                request.getEvent(),
                request.getCreated(),
                request.getRequester());
    }

    public static Request toRequest(RequestDto requestDto, String status) {
        return new Request(requestDto.getId(),
                requestDto.getEvent(),
                requestDto.getCreated(),
                requestDto.getRequester(),
                status);
    }
}
