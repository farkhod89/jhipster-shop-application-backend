package uz.gigalab.shop.admin.service;

import uz.gigalab.shop.admin.service.dto.FileDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link uz.gigalab.shop.admin.domain.File}.
 */
public interface FileService {

    /**
     * Save a file.
     *
     * @param fileDTO the entity to save.
     * @return the persisted entity.
     */
    FileDTO save(FileDTO fileDTO);

    /**
     * Get all the files.
     *
     * @return the list of entities.
     */
    List<FileDTO> findAll();

    /**
     * Get the "id" file.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FileDTO> findOne(Long id);

    /**
     * Delete the "id" file.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
