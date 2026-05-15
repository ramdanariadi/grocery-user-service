package id.grocery.tunas.cart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public interface CartRepository extends CrudRepository<Cart, UUID> {

    @Query("select c.id as id, c.product.name as name, c.product.price as price, c.product.weight as weight, c.product.category as category, " +
            "c.product.perUnit as perUnit, c.product.imageUrl as imageUrl, c.total as total, c.product as product " +
            "from Cart c where c.user.id = :id")
    List<ICharts> findChartsByUserId(@Param("id") String id);

    interface ICharts{
        @Value("#{target.product.imageUrl}")
        String getImageUrl();
        @Value("#{target.product.weight}")
        Integer getWeight();
        @Value("#{target.product.name}")
        String getName();
        @Value("#{target.product.price}")
        BigDecimal getPrice();
        @Value("#{target.product.perUnit}")
        Integer getPerUnit();
        Integer getTotal();
        @Value("#{target.product.id}")
        UUID getProduct();
        String getCategory();
        @Value("#{target.id}")
        UUID getId();
    }

    Optional<Cart> findChartByUserIdAndProductId(@Param("userId") UUID userId, @Param("productId") UUID productId);

    @Transactional
    @Modifying
    @Query("delete from Cart c where c.user.id = :userId and c.product.id = :productId")
    int removeFromChart(@Param("userId") String userId, @Param("productId") UUID productId);

    @Transactional
    @Modifying
    @Query("update Cart c set c.total = (c.total + 1) where c.user.id = :userId and c.product.id = :productId")
    int incrementProductTotalInChart(@Param("userId") UUID userId, @Param("productId") UUID productId);

    @Transactional
    @Modifying
    @Query("delete from Cart c where c.user.id = :userId")
    int destroyUserCart(@Param("userId") UUID userId);
}
