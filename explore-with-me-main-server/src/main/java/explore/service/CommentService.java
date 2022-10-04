package explore.service;

import explore.model.dto.CommentDto;
import explore.model.dto.CommentShortDto;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface CommentService {
    CommentDto setComment(Integer eventId,
                          CommentDto commentDto,
                          Integer userId);

    void deleteComment(Integer userId,
                       Integer commentId,
                       Integer id) throws ValidationException;

    List<CommentShortDto> getCommentOfEvent(Integer eventId);

    void moderateComment(Integer commentId,
                         Boolean status);
}
