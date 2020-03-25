package uz.gigalab.shop.admin.service;

import uz.gigalab.shop.admin.service.dto.PageDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link uz.gigalab.shop.admin.domain.Page}.
 */
public interface PageService {

    /**
     * Save a page.
     *
     * @param pageDTO the entity to save.
     * @return the persisted entity.
     */
    PageDTO save(PageDTO pageDTO);

    /**
     * Get all the pages.
     *
     * @return the list of entities.
     */
    List<PageDTO> findAll();

    /**
     * Get the "id" page.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PageDTO> findOne(Long id);

    /**
     * Delete the "id" page.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
