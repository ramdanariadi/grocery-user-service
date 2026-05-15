package id.grocery.tunas.cart;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.google.common.base.Strings;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class CartDAO {

    @PersistenceContext
    private EntityManager em;

    @Value("${spring.jpa.properties.hibernate.default_schema}")
    private String schema;

    public Query getUserCart(String userId, String search, boolean isCount){
        StringBuilder queryString = new StringBuilder("SELECT ");
        Map<String, Object> parameters = new HashMap<>();
        if(isCount){
            queryString.append("COUNT(1) ");
        }else{
            queryString.append("c.id\\:\\:text, c.product_id\\:\\:text, p.shop_id\\:\\:text, s.name AS shopName, p.price, p.weight, ct.category, p.per_unit, p.description, p.image_url, p.name AS productName, c.total ");
        }

        queryString.append(String.format("FROM %s.carts c " +
                        "JOIN %s.products p ON c.product_id = p.id AND p.deleted_at IS NULL " +
                        "JOIN %s.shops s ON s.id = p.shop_id AND s.deleted_at IS NULL " +
                        "JOIN %s.categories ct ON ct.id = p.category_id AND c.deleted_at IS NULL " +
                        "WHERE c.deleted_at IS NULL AND c.user_id = :userId ",
                schema,
                schema,
                schema,
                schema));
        parameters.put("userId", userId);

        if(!Strings.isNullOrEmpty(search)){
            queryString.append("AND p.name ILIKE :search ");
            parameters.put("search", search.toLowerCase(Locale.ROOT));
        }
        queryString.append("GROUP BY c.id, p.id, ct.id, s.id ");
        Query nativeQuery = em.createNativeQuery(queryString.toString());
        parameters.forEach(nativeQuery::setParameter);
        return nativeQuery;
    }
}
