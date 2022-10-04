package explore.impl;

import explore.mapper.CommentMapper;
import explore.model.Comment;
import explore.model.dto.CommentDto;
import explore.model.dto.CommentShortDto;
import explore.repository.CommentRepository;
import explore.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public CommentDto setComment(Integer eventId, CommentDto commentDto, Integer userId) {
        Comment comment = commentRepository.save(CommentMapper.toComment(commentDto));

        return CommentMapper.toCommentDto(comment);
    }

    @Override
    public void deleteComment(Integer userId, Integer commentId, Integer id) throws ValidationException {
        Comment comment = commentRepository.findById(commentId).get();

        if (!Objects.equals(comment.getCommentator().getId(), id)) {
            throw new ValidationException("Комментарий должен принадлежать пользователю.");
        }

        commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentShortDto> getCommentOfEvent(Integer eventId) {
        List<CommentShortDto> comments = commentRepository.getCommentOfEvent(eventId).stream()
                .map(CommentMapper::toCommentShortDto)
                .sorted((o1, o2) -> o2.getDateCreate().compareTo(o1.getDateCreate()))
                .collect(Collectors.toList());

        return comments;
    }

    @Override
    public void moderateComment(Integer commentId, Boolean status) {
        commentRepository.setStatus(status, commentId);
    }
}
