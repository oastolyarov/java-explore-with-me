package explore.controller;

import explore.mapper.CommentMapper;
import explore.model.Comment;
import explore.model.dto.CommentDto;
import explore.model.dto.CommentShortDto;
import explore.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/event/{eventId}/comments")
    public CommentDto setComment(@PathVariable Integer eventId,
                                 @RequestBody CommentDto commentDto,
                                 @RequestHeader("X-User-Id") Integer userId) {
        log.debug("Публикация комментария от пользователя с id" + userId);
        Comment comment = CommentMapper.toComment(commentDto);
        return CommentMapper.toCommentDto(commentService.setComment(eventId, comment, userId));
    }

    @DeleteMapping("/users/{userId}/comments/{commentId}")
    public void deleteComment(@PathVariable Integer userId,
                              @PathVariable Integer commentId,
                              @RequestHeader("X-User-Id") Integer id) throws ValidationException {
        log.debug("Удаление комментария пользователем.");
        commentService.deleteComment(userId, commentId, id);
    }

    @GetMapping("/event/{eventId}/comments")
    public List<CommentShortDto> getCommentOfEvent(@PathVariable Integer eventId) {
        log.debug("Просмотр комментариев у события с id " + eventId);

        return commentService.getEventComments(eventId).stream()
                .map(CommentMapper::toCommentShortDto)
                .collect(Collectors.toList());
    }

    @PatchMapping("/admin/comments/{commentId}")
    public void moderateComment(@PathVariable Integer commentId,
                                @RequestParam Boolean status) {
        log.debug("Модерация комментария, статус " + status);

        commentService.moderateComment(commentId, status);
    }
}
