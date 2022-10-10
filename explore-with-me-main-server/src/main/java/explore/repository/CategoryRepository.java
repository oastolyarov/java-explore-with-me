package explore.repository;

import explore.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Transactional
    @Modifying
    @Query("update Category c set c.name = ?1 where c.id = ?2")
    void changeCategory(String name, Integer id);
}
