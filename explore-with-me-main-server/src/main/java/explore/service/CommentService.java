package explore.service;

import explore.model.Comment;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface CommentService {
    Comment setComment(Integer eventId,
                          Comment comment,
                          Integer userId);

    void deleteComment(Integer userId,
                       Integer commentId,
                       Integer id) throws ValidationException;

    List<Comment> getEventComments(Integer eventId);

    void moderateComment(Integer commentId,
                         Boolean status);
}
