package explore.mapper;

import explore.model.Comment;
import explore.model.dto.CommentDto;
import explore.model.dto.CommentShortDto;

public abstract class CommentMapper {
    public static CommentDto toCommentDto(Comment comment) {
        return new CommentDto(comment.getId(),
                comment.getComment(),
                comment.getEvent(),
                comment.getCommentator(),
                comment.getDateCreate());
    }

    public static CommentShortDto toCommentShortDto(Comment comment) {
        return new CommentShortDto(comment.getId(),
                comment.getComment(),
                comment.getCommentator(),
                comment.getDateCreate());
    }

    public static Comment toComment(CommentDto commentDto) {
        return new Comment(commentDto.getId(),
                commentDto.getComment(),
                commentDto.getEvent(),
                commentDto.getCommentator(),
                commentDto.getDateCreate(),
                false);
    }
}
