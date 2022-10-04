package explore.repository;

import explore.model.Comment;
import explore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Transactional
    @Modifying
    @Query("update Comment c set c.isPublished = ?1 where c.id = ?2")
    void setStatus(Boolean isPublished, Integer id);

    @Query("select c from Comment c where c.event.id = ?1")
    List<Comment> getCommentOfEvent(Integer eventId);
}
