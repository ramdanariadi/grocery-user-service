package id.grocery.tunas.order;

import id.grocery.tunas.cart.CartRepository;
import id.grocery.tunas.exception.ApiRequestException;
import id.grocery.tunas.order.OrderRepository.IOrderData;
import id.grocery.tunas.order.dto.AddOrderDTO;
import id.grocery.tunas.product.Product;
import id.grocery.tunas.product.ProductRepository;
import id.grocery.tunas.user.User;
import id.grocery.tunas.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private final UserService userService;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final CartRepository cartRepository;

    @Transactional
    public OrderData createOrder(UUID userId, AddOrderDTO.Request body){
        Optional<User> userOptional = userService.findById(userId);

        if(userOptional.isEmpty()){
            throw new ApiRequestException(ApiRequestException.BAD_REQUEST,HttpStatus.UNAUTHORIZED);
        }

        Order order = new Order();
        User user = userOptional.get();
        order.setUser(user);

        List<AddOrderDTO.OrderItem> products = body.getProducts();
        Map<UUID, Product> productMap = productRepository.findProductsByIds(products.stream().map(AddOrderDTO.OrderItem::getProductId).toArray(UUID[]::new))
                .stream().collect(Collectors.toMap(Product::getId, Function.identity()));

        List<OrderItem> orderItems = products.stream().map(oi -> {
            Product product = productMap.get(oi.getProductId());
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setImageUrl(product.getImageUrl());
            orderItem.setName(product.getName());
            orderItem.setPerUnit(product.getPerUnit());
            orderItem.setWeight(product.getWeight());
            orderItem.setPrice(product.getPrice());
            orderItem.setTotal(oi.getTotal());
            orderItem.setProduct(product);
            orderItem.setOrder(order);
            return orderItem;
        }).collect(Collectors.toList());

        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderItem d: orderItems){
            BigDecimal weight = BigDecimal.valueOf(d.getWeight());
            BigDecimal total = BigDecimal.valueOf(d.getTotal());
            BigDecimal perUnit = BigDecimal.valueOf(d.getPerUnit());
            totalPrice = totalPrice.add(((weight.multiply(total)).divide(perUnit)).multiply(d.getPrice()));
//            Product product = productRepository.findProductById(d.getId());
//            d.setProduct(product);
//            d.setOrder(order);
        }
        order.setTotalPrice(totalPrice);
        orderRepository.save(order);
        orderDetailRepository.saveAll(orderItems);
        cartRepository.destroyUserCart(user.getId());
        IOrderData savedTransaction = orderRepository.getOrderById(order.getId());
        List<OrderDetailRepository.IOrderItems> savedDetailTransaction = orderDetailRepository.
                getOrderItemsByOrderId(order.getId());
        return new OrderData(savedTransaction,savedDetailTransaction);
    }

    OrderData getOrderById(UUID id){
        IOrderData savedTransaction = orderRepository.getOrderById(id);
        List<OrderDetailRepository.IOrderItems> savedDetailTransaction = orderDetailRepository.
                getOrderItemsByOrderId(id);
        return new OrderData(savedTransaction,savedDetailTransaction);
    }

    @Transactional(rollbackOn = ApiRequestException.class)
    public int deleteOrder(UUID id){
        orderRepository.deleteOrderItems(id);
        int transactionDestroy = orderRepository.deleteOrder(id);
        if(transactionDestroy < 1){
            throw new ApiRequestException("",HttpStatus.NOT_MODIFIED);
        }
        return transactionDestroy;
    }

    public List<OrderData> getOrderByCustomerId(UUID id){
        List<IOrderData> iTransactionResponses = orderRepository.getOrdersByUserId(id);
        List<OrderDetailRepository.IOrderItems> iDetailTransactions = orderDetailRepository.getOrderItemsByUserId(id);
        List<OrderData> transactionResponseList = iTransactionResponses.stream().map((t) -> {
            OrderData transactionResponse = new OrderData();
            transactionResponse.setTransaction(t);
            transactionResponse.setDetailTransaction(iDetailTransactions.stream().filter(e -> e.getOrderId().toString().equals(t.getId().toString())).collect(Collectors.toList()));
            return transactionResponse;
        }).collect(Collectors.toList());
        return transactionResponseList;
    }
}
