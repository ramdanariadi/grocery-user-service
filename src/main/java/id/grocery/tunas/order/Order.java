package id.grocery.tunas.order;

import id.grocery.tunas.base.BaseModel;
import id.grocery.tunas.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "orders")
@NoArgsConstructor
public class Order extends BaseModel {

    @Column(name = "price_total", nullable = false, precision = 19, scale = 2)
    private BigDecimal totalPrice;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
