package uz.gigalab.shop.admin.service.mapper;


import uz.gigalab.shop.admin.domain.*;
import uz.gigalab.shop.admin.service.dto.CategoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Category} and its DTO {@link CategoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {FileMapper.class, PageMapper.class})
public interface CategoryMapper extends EntityMapper<CategoryDTO, Category> {

    @Mapping(source = "file.id", target = "fileId")
    @Mapping(source = "page.id", target = "pageId")
    @Mapping(source = "pageUz.id", target = "pageUzId")
    @Mapping(source = "parent.id", target = "parentId")
    CategoryDTO toDto(Category category);

    @Mapping(source = "fileId", target = "file")
    @Mapping(source = "pageId", target = "page")
    @Mapping(source = "pageUzId", target = "pageUz")
    @Mapping(target = "children", ignore = true)
    @Mapping(target = "removeChildren", ignore = true)
    @Mapping(source = "parentId", target = "parent")
    Category toEntity(CategoryDTO categoryDTO);

    default Category fromId(Long id) {
        if (id == null) {
            return null;
        }
        Category category = new Category();
        category.setId(id);
        return category;
    }
}
