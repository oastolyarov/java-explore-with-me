package explore.repository;

import explore.model.Compilation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface CompilationRepository extends JpaRepository<Compilation, Integer> {
    @Transactional
    @Modifying
    @Query("update Compilation c set c.pinned = ?1 where c.id = ?2")
    void toPin(Boolean pinned, Integer compId);
}
