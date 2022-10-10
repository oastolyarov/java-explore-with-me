package explore.repository;

import explore.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Transactional
    @Modifying
    @Query("update Comment c set c.isPublished = ?1 where c.id = ?2")
    void setStatus(Boolean isPublished, int id);

    @Query("select c from Comment c where c.event.id = ?1")
    List<Comment> getCommentOfEvent(int eventId);
}
