package id.grocery.tunas.order;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface OrderRepository extends CrudRepository<Order, UUID> {

    @Query("select o.id as id, o.totalPrice as totalPrice, o.createdAt as orderDate " +
            "from Order o where o.id = :id")
    IOrderData getOrderById(@Param("id") UUID id);

    @Query("select o.id as id, o.totalPrice as totalPrice, o.createdAt as orderDate " +
            "from Order o where o.user.id = :id")
    List<IOrderData> getOrdersByUserId(@Param("id") UUID id);

    interface IOrderData {
        UUID getId();
        Long getTotalPrice();
        Date getOrderDate();
    }

    @Transactional
    @Modifying
    @Query("delete from Order o where o.id = :id")
    int deleteOrder(@Param("id") UUID id);

    @Transactional
    @Modifying
    @Query("delete from OrderItem oi where oi.order.id = :id")
    int deleteOrderItems(@Param("id") UUID id);
}
