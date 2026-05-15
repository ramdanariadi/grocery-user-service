package id.grocery.tunas.category;

import id.grocery.tunas.base.BaseModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@Data
public class Category extends BaseModel {
    @Column(name = "category", length = 100, nullable = false)
    private String category;

    @Column(name = "image_url")
    private String imageUrl;

}
