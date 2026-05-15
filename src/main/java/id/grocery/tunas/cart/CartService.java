package id.grocery.tunas.cart;

import id.grocery.tunas.cart.dto.FindUserCartDTO;
import id.grocery.tunas.exception.ApiRequestException;
import id.grocery.tunas.product.Product;
import id.grocery.tunas.product.ProductRepository;
import id.grocery.tunas.user.User;
import id.grocery.tunas.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jakarta.persistence.Query;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartDAO cartDAO;

    public void storeToChart(UUID userId,UUID productId,Integer total){
        Optional<Cart> optionalChart = cartRepository.findChartByUserIdAndProductId(userId,productId);
        if(optionalChart.isPresent()){
            cartRepository.incrementProductTotalInChart(userId,productId);
            return;
        }
        Product product = productRepository.findProductById(productId);
        Optional<User> user = userRepository.findById(userId);

        if(user.isEmpty()){
            throw new ApiRequestException(ApiRequestException.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
        }

        Cart cart = new Cart();
        cart.setTotal(total);
        cart.setProduct(product);
        cart.setUser(user.get());
    }

    public FindUserCartDTO.Response chartList(String userId, FindUserCartDTO.Request request){
        Query userCartCount = cartDAO.getUserCart(userId, request.getSearch(), true);
        Query userCartData = cartDAO.getUserCart(userId, request.getSearch(), false);
        userCartData.setFirstResult(request.getPageIndex() * request.getPageSize()).setMaxResults(request.getPageSize());
        List<Object[]> resultList = userCartData.getResultList();
        FindUserCartDTO.Response response = new FindUserCartDTO.Response();
        response.setData(resultList.stream().map(objects -> {
            FindUserCartDTO.SimpleCartDTO simpleCartDTO = new FindUserCartDTO.SimpleCartDTO();
                    simpleCartDTO.setId((String) objects[0]);;
                    simpleCartDTO.setProductId((String) objects[1]);
                    simpleCartDTO.setShopId((String) objects[2]);
                    simpleCartDTO.setShopName((String) objects[3]);
                    simpleCartDTO.setPrice((BigDecimal) objects[4]);
                    simpleCartDTO.setWeight((int) objects[5]);
                    simpleCartDTO.setCategory((String) objects[6]);
                    simpleCartDTO.setPerUnit((int) objects[7]);
                    simpleCartDTO.setDescription((String) objects[8]);
                    simpleCartDTO.setImageUrl((String) objects[9]);
                    simpleCartDTO.setName((String) objects[10]);
                    simpleCartDTO.setTotal((int) objects[11]);
            return simpleCartDTO;
        }).collect(Collectors.toList()));
        response.setTotalData(userCartCount.getResultList().size());
        response.setPageIndex(request.getPageIndex());
        response.setPageSize(request.getPageSize());
        return response;
    }

    public Integer removeFromChart(String userId, UUID productId) {
        return cartRepository.removeFromChart(userId,productId);
    }
}
