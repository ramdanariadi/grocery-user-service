package id.grocery.tunas.category;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends CrudRepository<Category, UUID> {

    @Query("select c from Category c where c.id = :id and c.deletedAt IS NULL")
    Category findCategoryById(@Param("id") UUID id);

    @Query("select c from Category c where c.deletedAt IS NULL")
    List<Category> findAllCategories();

    Category findCategoryByCategory(String category);

    @Modifying
    @Transactional
    @Query("update Category c set c.category = :category, c.imageUrl = :imageUrl where c.id = :id")
    int updateCategory(@Param("id") UUID id,@Param("category") String category, @Param("imageUrl") String imageUrl);

    @Modifying
    @Transactional
    @Query("update Category c set c.deletedAt = CURRENT_TIMESTAMP where c.id = :id")
    int destroyCategoryById(@Param("id") UUID id);
}
