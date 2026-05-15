package id.grocery.tunas.cart;

import id.grocery.tunas.base.BaseModel;
import id.grocery.tunas.product.Product;
import id.grocery.tunas.user.User;
import lombok.Data;

import jakarta.persistence.*;

@Entity
@Table(name = "carts")
@Data
public class Cart extends BaseModel {
    private Integer total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    User user;

    @OneToOne
    Product product;

    public void setProduct(Product product) {
        this.product = product;
    }
}
