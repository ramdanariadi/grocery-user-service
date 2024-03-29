//package id.grocery.tunas;
//
//import id.grocery.tunas.category.Category;
//import id.grocery.tunas.category.CategoryRepository;
//import id.grocery.tunas.product.Product;
//import id.grocery.tunas.product.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.UUID;
//
//@Component
//public class Seeder implements CommandLineRunner {
//
//    @Autowired
//    CategoryRepository categoryRepository;
//
//    @Autowired
//    ProductRepository productRepository;
//
//    private UUID categoryID;
//    private UUID productID;
//
//    @Override
//    public void run(String... args) throws Exception {
//        categoryID = UUID.fromString("8c2c184e-11d2-11ec-82a8-0242ac130003");
//        productID = UUID.fromString("a3c02e8c-11d2-11ec-82a8-0242ac130003");
//        loadDataCategory();
//        loadDataProduct();
//    }
//
//    private void loadDataCategory(){
//        Category categoryCheck = categoryRepository.findCategoryById(categoryID);
//        if(categoryCheck == null){
//            Category category = new Category();
//            category.setCategory("Vegetables");
//            category.setId(categoryID);
//            categoryRepository.save(category);
//        }
//    }
//
//    private void loadDataProduct(){
//        Product productCheck = productRepository.findProductById(productID);
//        if(productCheck == null){
//            Product product = new Product();
//            product.setId(productID);
//            product.setName("Broccoli");
//            product.setDescription("Green vegetable, good for eyes");
//            product.setPrice(1100L);
//            product.setWeight(1500);
//            product.setPerUnit(100);
//            Category category = categoryRepository.findCategoryById(categoryID);
//            product.setCategory(category);
//            productRepository.save(product);
//        }
//    }
//}
