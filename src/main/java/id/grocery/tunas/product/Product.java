package id.grocery.tunas.product;

import id.grocery.tunas.base.BaseModel;
import id.grocery.tunas.category.Category;
import id.grocery.tunas.shop.Shop;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products", indexes = {
        @Index(name = "idx_product_name", columnList = "name"),
        @Index(name = "idx_product_category",columnList = "category_id")
})
@Data
@NoArgsConstructor
public class Product extends BaseModel {
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "price", nullable = false, precision = 19, scale = 2)
    private BigDecimal price; // per unit

    @Column(name = "per_unit", nullable = false)
    private Integer perUnit; // gram

    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "weight", nullable = false)
    private Integer weight; // on gram

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "is_top")
    private Boolean isTop = false;

    @Column(name = "is_recommended")
    private Boolean isRecommended = false;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    Category category;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    Shop shop;
}
