package id.grocery.tunas.product;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.Optional;

@Repository
public class ProductDAO {

    @PersistenceContext
    private EntityManager em;

    @Value("${spring.jpa.properties.hibernate.default_schema}")
    private Optional<String> schema;

    public Query getAllProducts(boolean isCount){
        StringBuilder query = new StringBuilder("SELECT ");
        if(isCount){
            query.append("COUNT(1) ");
        }else{
            query.append("p.id\\:\\:text, shop_id\\:\\:text, s.name AS shop_name, price, weight, category, per_unit, description, p.image_url, p.name AS product_name ");
        }
        query.append(String.format("FROM %s.products p " +
                "LEFT JOIN %s.shops s ON s.id = p.shop_id AND s.deleted_at IS NULL " +
                "JOIN %s.categories c ON c.id = p.category_id AND c.deleted_at IS NULL " +
                "WHERE p.deleted_at IS NULL " +
                "GROUP BY p.id, s.id, c.id ", schema.orElse("public"),schema.orElse("public"),schema.orElse("public")));
        Query nativeQuery = em.createNativeQuery(query.toString());
        return nativeQuery;
    }
}
