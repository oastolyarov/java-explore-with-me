package explore.impl;

import explore.mapper.CommentMapper;
import explore.model.Comment;
import explore.model.dto.CommentDto;
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
    public Comment setComment(Integer eventId, Comment comment, Integer userId) {

        CommentDto commentDto = CommentMapper.toCommentDto(comment);

        return commentRepository.save(CommentMapper.toComment(commentDto));
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
    public List<Comment> getEventComments(Integer eventId) {

        return commentRepository.getCommentOfEvent(eventId).stream()
                .sorted((o1, o2) -> o2.getDateCreate().compareTo(o1.getDateCreate()))
                .collect(Collectors.toList());
    }

    @Override
    public void moderateComment(Integer commentId, Boolean status) {
        commentRepository.setStatus(status, commentId);
    }
}
