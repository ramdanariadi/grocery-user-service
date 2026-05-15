package id.grocery.tunas.shop;

import id.grocery.tunas.shop.dto.GetShopDTO;
import id.grocery.tunas.shop.dto.ShopDTO;
import jakarta.servlet.http.HttpServletRequest;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/shop")
@AllArgsConstructor
public class ShopController {

    private ShopService shopService;

    @PostMapping
    public ResponseEntity<Object> addStore(HttpServletRequest request, @RequestBody ShopDTO shop){
        JsonObject userCustomId = new JsonObject(request.getHeader("x-custom-id"));
        shopService.saveShop(UUID.fromString(userCustomId.getString("userId")), shop);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Object> getStore(HttpServletRequest request){
        JsonObject userCustomId = new JsonObject(request.getHeader("x-custom-id"));
        GetShopDTO.Response response = shopService.getUserShop(UUID.fromString(userCustomId.getString("userId")));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStore(HttpServletRequest request, @PathVariable("id") UUID id, @RequestBody ShopDTO shop){
        JsonObject userCustomId = new JsonObject(request.getHeader("x-custom-id"));
        shopService.updateShop(UUID.fromString(userCustomId.getString("userId")), id, shop);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStore(HttpServletRequest request, @PathVariable("id") UUID id){
        JsonObject userCustomId = new JsonObject(request.getHeader("x-custom-id"));
        shopService.deleteShop(UUID.fromString(userCustomId.getString("userId")), id);
        return ResponseEntity.ok().build();
    }

}
