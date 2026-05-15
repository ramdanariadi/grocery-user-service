package id.grocery.tunas.cart;

import id.grocery.tunas.cart.dto.FindUserCartDTO;
import io.vertx.core.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/{productId}/{total}")
    public ResponseEntity<Object> addToChart(HttpServletRequest request, @PathVariable UUID productId, @PathVariable Integer total){
        JsonObject result = new JsonObject(request.getHeader("x-custom-id"));
        cartService.storeToChart(UUID.fromString(result.getString("userId")),productId,total);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Object> removeFromChart(HttpServletRequest request, @PathVariable UUID cartId){
        JsonObject result = new JsonObject(request.getHeader("x-custom-id"));
        cartService.removeFromChart(result.getString("userId"), cartId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Object> customerChart(HttpServletRequest request, FindUserCartDTO.Request requestParam){
        JsonObject result = new JsonObject(request.getHeader("x-custom-id"));
        FindUserCartDTO.Response response = cartService.chartList(result.getString("userId"), requestParam);
        return ResponseEntity.ok(response);
    }
}
