package id.grocery.tunas.product;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Component
public interface ProductRepository extends CrudRepository<Product, UUID> {

    @Query("SELECT p FROM Product p WHERE p.id IN :ids AND p.deletedAt IS NULL")
    List<Product> findProductsByIds(@Param("ids") UUID[] ids);

    @Query("select p.id as id, p.name as name, p.price as price, p.weight as weight, p.imageUrl as imageUrl, p.category as category from Product p where p.deletedAt is null")
    List<ICustomSelect> findCustomColumn();

    interface ICustomSelect {
        String getImageUrl();
        Integer getWeight();
        String getName();
        Long getPrice();
        @Value("#{target.category.category}")
        String getCategory();
        @Value("#{target.id}")
        UUID getId();
        @Value("#{target.category.id}")
        UUID getCategoryId();
    }

    @Query("select p.id as id, p.name as name, p.price as price, p.weight as weight, p.imageUrl as imageUrl, p.category as category from Product p where p.deletedAt is null and p.category.id = :category_id")
    List<ICustomSelect> findProductsByCategory(@Param("category_id") UUID id);

    Product findProductById(UUID id);

    @Modifying
    @Transactional
    @Query("update Product p set p.name = :name, p.price = :price, p.imageUrl = :imageUrl where p.id = :id")
    int updateProduct(@Param("id") UUID id,
                      @Param("name") String name,
                      @Param("price") BigDecimal price,
                      @Param("imageUrl") String imageUrl);

    @Modifying
    @Transactional
    @Query("update Product p set p.deletedAt = CURRENT_TIMESTAMP where p.id = :id")
    int destroyProduct(@Param("id") UUID id);
}
