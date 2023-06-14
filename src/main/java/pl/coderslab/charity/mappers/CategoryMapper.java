package pl.coderslab.charity.mappers;

import org.springframework.stereotype.Component;
import pl.coderslab.charity.dtos.CategoryDTO;
import pl.coderslab.charity.entities.Category;

@Component
public class CategoryMapper {
    public Category toEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setId(categoryDTO.getId());
        return category;
    }

    public CategoryDTO toDto(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(category.getName());
        categoryDTO.setId(category.getId());
        return categoryDTO;
    }
}
