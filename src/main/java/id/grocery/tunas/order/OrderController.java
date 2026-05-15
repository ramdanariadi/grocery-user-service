package id.grocery.tunas.order;

import id.grocery.tunas.order.dto.AddOrderDTO;
import jakarta.servlet.http.HttpServletRequest;
import io.vertx.core.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Object> addOrder(HttpServletRequest request, @RequestBody AddOrderDTO.Request body){
        JsonObject userCustomId = new JsonObject(request.getHeader("x-custom-id"));
        orderService.createOrder(UUID.fromString(userCustomId.getString("userId")), body);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrders(@PathVariable UUID id){
        OrderData transaction = orderService.getOrderById(id);
        JsonObject result = new JsonObject();
        result.put("data", transaction);
        return ResponseEntity.ok(result.getMap());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable UUID id){
        int nModified = orderService.deleteOrder(id);
        if(nModified > 0) return ResponseEntity.ok().build();
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }

    @GetMapping
    public ResponseEntity<Object> getCustomerOrders(HttpServletRequest request){
        JsonObject userCustomId = new JsonObject(request.getHeader("x-custom-id"));
        List<OrderData> transactions = orderService.getOrderByCustomerId(UUID.fromString(userCustomId.getString("userId")));
        JsonObject result = new JsonObject();
        result.put("data", transactions);
        return ResponseEntity.ok(result.getMap());
    }
}
