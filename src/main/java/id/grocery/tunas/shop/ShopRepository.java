package id.grocery.tunas.shop;

import java.util.UUID;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface ShopRepository extends CrudRepository<Shop, UUID> {

    @Query("SELECT s FROM Shop s WHERE s.id = :id AND s.deletedAt IS NULL")
    public Shop findShopById(@Param("id") UUID id);

    @Query("SELECT s FROM Shop s WHERE s.user.id = :userId AND s.deletedAt IS NULL")
    public Shop findShopByUserId(@Param("userId") UUID userID);

    @Modifying
    @Transactional
    @Query("UPDATE Shop s set s.name = :name, s.imageUrl = :imageUrl WHERE s.id = :id ")
    public void updateShop(@Param("id") UUID id, @Param("name") String name, @Param("imageUrl") String imageUrl);

    @Modifying
    @Transactional
    @Query("UPDATE Shop s set s.deletedAt = CURRENT_TIMESTAMP WHERE s.id = :id AND s.user.id = :userId")
    public void deleteById(@Param("id") UUID id, @Param("userId") UUID userId);
}
