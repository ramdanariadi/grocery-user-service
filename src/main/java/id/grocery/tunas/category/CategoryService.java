package id.grocery.tunas.category;

import com.google.common.base.Strings;
import id.grocery.tunas.category.dto.CategoryDTO;
import id.grocery.tunas.category.dto.FindAllCategoryDTO;
import id.grocery.tunas.exception.ApiRequestException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.persistence.Query;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {

    private final Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);

    private final CategoryRepository categoryRepository;
    private final CategoryDAO categoryDAO;

    public Category findById(UUID id){
        return categoryRepository.findCategoryById(id);
    }

    public FindAllCategoryDTO.Response findAllCategory(FindAllCategoryDTO.Request request){
        Query allCategoriesCount = categoryDAO.getAllCategories(true);
        Query allCategoriesData = categoryDAO.getAllCategories(false);
        allCategoriesData.setFirstResult(request.getPageIndex() * request.getPageSize()).setMaxResults(request.getPageSize());
        List<Object[]> resultList = allCategoriesData.getResultList();
        List<FindAllCategoryDTO.SimpleCategoryDTO> collect = resultList.stream().map(objects -> {
            FindAllCategoryDTO.SimpleCategoryDTO simpleCategoryDTO = new FindAllCategoryDTO.SimpleCategoryDTO();
            simpleCategoryDTO.setId(UUID.fromString((String) objects[0]));
            simpleCategoryDTO.setCategory((String) objects[1]);
            simpleCategoryDTO.setImageUrl((String) objects[2]);
            return simpleCategoryDTO;
        }).collect(Collectors.toList());
        FindAllCategoryDTO.Response response = new FindAllCategoryDTO.Response();
        response.setData(collect);
        response.setTotalData(allCategoriesCount.getResultList().size());
        response.setPageSize(request.getPageSize());
        response.setPageIndex(request.getPageIndex());
        return response;
    }

    public void addCategory(CategoryDTO categoryDto){
        if(Strings.isNullOrEmpty(categoryDto.getCategory())){
            throw new ApiRequestException("BAD_REQUEST");
        }
        Category category = new Category();
        category.setCategory(categoryDto.getCategory());
        category.setImageUrl(categoryDto.getImageUrl());
        categoryRepository.save(category);
    }

    public int updateCategory(UUID id, CategoryDTO category){
        if(Strings.isNullOrEmpty(category.getCategory())){
            throw new ApiRequestException("BAD_REQUEST");
        }
        return categoryRepository.updateCategory(id, category.getCategory(), category.getImageUrl());
    }

    public int destroyCategory(UUID id){
        return categoryRepository.destroyCategoryById(id);
    }
}
