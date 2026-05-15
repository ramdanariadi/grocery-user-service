package id.grocery.tunas.wishlist;

import id.grocery.tunas.base.BaseModel;
import id.grocery.tunas.product.Product;
import id.grocery.tunas.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "wishlists")
@Data
@NoArgsConstructor
public class Wishlist extends BaseModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @OneToOne(fetch = FetchType.LAZY)
    Product product;

}
