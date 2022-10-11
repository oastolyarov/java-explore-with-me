package explore.service;

import explore.model.Comment;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface CommentService {
    Comment setComment(int eventId,
                          Comment comment,
                          int userId);

    void deleteComment(int userId,
                       int commentId,
                       int id) throws ValidationException;

    List<Comment> getEventComments(int eventId);

    void moderateComment(int commentId,
                         boolean status);
}
