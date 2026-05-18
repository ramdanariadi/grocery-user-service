package id.grocery.tunas.category;

import id.grocery.tunas.category.dto.CategoryDTO;
import id.grocery.tunas.category.dto.FindAllCategoryDTO;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/category")
@Validated
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(CategoryController.class);

    @GetMapping
    public ResponseEntity<Object> allCategories(FindAllCategoryDTO.Request requestParam){
        LOGGER.info("thread name {}", Thread.currentThread().toString());
        FindAllCategoryDTO.Response allCategory = categoryService.findAllCategory(requestParam);
        return ResponseEntity.ok(allCategory);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> categoryById(@PathVariable("id") UUID id){
        Category category = categoryService.findById(id);
        JsonObject result = new JsonObject();
        result.put("data", category);
        return ResponseEntity.ok(result.getMap());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> destroyCategory(@PathVariable("id") UUID id){
        int nModified = categoryService.destroyCategory(id);
        if(nModified > 0) return ResponseEntity.ok().build();
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable("id") UUID id,@RequestBody CategoryDTO category){
        int nModified = categoryService.updateCategory(id, category);
        if(nModified > 0) return ResponseEntity.ok().build();
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }

    @PostMapping
    public ResponseEntity<Object> addCategory(@RequestBody CategoryDTO category){
        LOGGER.info("thread name {}", Thread.currentThread().toString());
        categoryService.addCategory(category);
        return ResponseEntity.ok().build();
    }
}
