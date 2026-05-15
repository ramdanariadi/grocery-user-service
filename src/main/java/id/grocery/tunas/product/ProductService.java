package id.grocery.tunas.product;

import com.google.common.base.Strings;
import id.grocery.tunas.category.Category;
import id.grocery.tunas.category.CategoryRepository;
import id.grocery.tunas.exception.ApiRequestException;
import id.grocery.tunas.product.dto.AddProductDTO;
import id.grocery.tunas.product.dto.FindAllProductDTO;
import id.grocery.tunas.shop.Shop;
import id.grocery.tunas.shop.ShopRepository;
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
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductDAO productDAO;
    private final CategoryRepository categoryRepository;
    private final ShopRepository shopRepository;

    public FindAllProductDTO.Response getAll(FindAllProductDTO.Request request){
        Query allProductsCount = productDAO.getAllProducts(true);
        Query allProductsData = productDAO.getAllProducts(false);
        allProductsData.setFirstResult(request.getPageIndex() * request.getPageSize()).setMaxResults(request.getPageSize());
        List<Object[]> resultList = allProductsData.getResultList();
        FindAllProductDTO.Response response = new FindAllProductDTO.Response();
        response.setTotalData(allProductsCount.getResultList().size());
        response.setPageIndex(request.getPageIndex());
        response.setPageSize(request.getPageSize());
        response.setData(resultList.stream().map(objects -> {
            FindAllProductDTO.SimpleProductDTO simpleProductDTO = new FindAllProductDTO.SimpleProductDTO();
            simpleProductDTO.setId(UUID.fromString((String) objects[0]));
            simpleProductDTO.setShopId(null == objects[1] ? null : UUID.fromString((String) objects[1]));
            simpleProductDTO.setShopName(null == objects[2] ? null : (String) objects[2]);
            simpleProductDTO.setPrice((BigDecimal) objects[3]);
            simpleProductDTO.setWeight((int) objects[4]);
            simpleProductDTO.setCategory((String) objects[5]);
            simpleProductDTO.setPerUnit((int) objects[6]);
            simpleProductDTO.setDescription((String) objects[7]);
            simpleProductDTO.setImageUrl((String) objects[8]);
            simpleProductDTO.setName((String) objects[9]);
            return simpleProductDTO;
        }).collect(Collectors.toList()));
        return response;
    }

    public Product findProductById(UUID id){
        return productRepository.findProductById(id);
    }

    public List<ProductRepository.ICustomSelect> getAllBYCategory(UUID categoryId){
        return productRepository.findProductsByCategory(categoryId);
    }

    public void saveProduct(UUID userId, AddProductDTO requestBody){
        if(requestBody.getWeight() <= 0 ||
            requestBody.getPerUnit() <= 0 ||
            null == requestBody.getPrice() || requestBody.getPrice().compareTo(BigDecimal.ONE) < 0 ||
            Strings.isNullOrEmpty(requestBody.getCategoryId()) ||
            Strings.isNullOrEmpty(requestBody.getName()))
        {
            throw new ApiRequestException(ApiRequestException.BAD_REQUEST, HttpStatus.BAD_REQUEST);
        }

        Shop shopByUserId = shopRepository.findShopByUserId(userId);

        if(null == shopByUserId){
            throw new ApiRequestException("USER_SHOP_DOES_NOT_EXIST", HttpStatus.BAD_REQUEST);
        }

        Optional<Category> category = categoryRepository.findById(UUID.fromString(requestBody.getCategoryId()));
        if(category.isEmpty()){
            throw new ApiRequestException(ApiRequestException.BAD_REQUEST, HttpStatus.BAD_REQUEST);
        }

        Product product = new Product();
        product.setName(requestBody.getName());
        product.setDescription(requestBody.getDescription());
        product.setPrice(requestBody.getPrice());
        product.setPerUnit(requestBody.getPerUnit());
        product.setWeight(requestBody.getWeight());
        product.setImageUrl(requestBody.getImageUrl());
        product.setShop(shopByUserId);

        product.setCategory(category.get());
        productRepository.save(product);
    }

    public int updateProduct(Product product){
        if(Strings.isNullOrEmpty(product.getName())){
            throw new ApiRequestException("PRODUCT_NAME_CANNOT_EMPTY");
        }
        return productRepository.updateProduct(product.getId(),product.getName(),product.getPrice(),product.getImageUrl());
    }

    public int destroyProduct(UUID id){
        return productRepository.destroyProduct(id);
    }
}
