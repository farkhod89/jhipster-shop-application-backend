package uz.gigalab.shop.admin.service.mapper;


import uz.gigalab.shop.admin.domain.*;
import uz.gigalab.shop.admin.service.dto.PageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Page} and its DTO {@link PageDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PageMapper extends EntityMapper<PageDTO, Page> {



    default Page fromId(Long id) {
        if (id == null) {
            return null;
        }
        Page page = new Page();
        page.setId(id);
        return page;
    }
}
