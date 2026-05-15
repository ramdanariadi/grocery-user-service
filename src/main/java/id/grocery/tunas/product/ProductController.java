package id.grocery.tunas.product;

import id.grocery.tunas.product.dto.AddProductDTO;
import id.grocery.tunas.product.dto.FindAllProductDTO;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<Object> getProducts(FindAllProductDTO.Request request){
        FindAllProductDTO.Response products = productService.getAll(request);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Object> getProduct(@PathVariable UUID id){
        Product product = productService.findProductById(id);
        JsonObject result = new JsonObject();
        result.put("data", product);
        return ResponseEntity.ok(result.getMap());
    }

    @GetMapping("/category/{id}")
    @ResponseBody
    public ResponseEntity<Object> getProductByCategory(@PathVariable UUID id){
        List<ProductRepository.ICustomSelect> products = productService.getAllBYCategory(id);
        JsonObject result = new JsonObject();
        result.put("data", products);
        return ResponseEntity.ok(result.getMap());
    }

    @PostMapping
    public ResponseEntity<Object> addProduct(HttpServletRequest request, @RequestBody AddProductDTO requestBody){
        JsonObject userCustomId = new JsonObject(request.getHeader("x-custom-id"));
        productService.saveProduct(UUID.fromString(userCustomId.getString("userId")), requestBody);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Object> updateProduct(@RequestBody Product product){
        int nModified = productService.updateProduct(product);
        if(nModified > 0) return ResponseEntity.ok().build();
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroyProduct(@PathVariable UUID id){
        int nModified = productService.destroyProduct(id);
        if(nModified > 0) return ResponseEntity.ok().build();
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }

}
