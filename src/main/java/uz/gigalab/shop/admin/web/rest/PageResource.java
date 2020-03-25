package uz.gigalab.shop.admin.web.rest;

import uz.gigalab.shop.admin.service.PageService;
import uz.gigalab.shop.admin.web.rest.errors.BadRequestAlertException;
import uz.gigalab.shop.admin.service.dto.PageDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link uz.gigalab.shop.admin.domain.Page}.
 */
@RestController
@RequestMapping("/api")
public class PageResource {

    private final Logger log = LoggerFactory.getLogger(PageResource.class);

    private static final String ENTITY_NAME = "page";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PageService pageService;

    public PageResource(PageService pageService) {
        this.pageService = pageService;
    }

    /**
     * {@code POST  /pages} : Create a new page.
     *
     * @param pageDTO the pageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pageDTO, or with status {@code 400 (Bad Request)} if the page has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pages")
    public ResponseEntity<PageDTO> createPage(@Valid @RequestBody PageDTO pageDTO) throws URISyntaxException {
        log.debug("REST request to save Page : {}", pageDTO);
        if (pageDTO.getId() != null) {
            throw new BadRequestAlertException("A new page cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PageDTO result = pageService.save(pageDTO);
        return ResponseEntity.created(new URI("/api/pages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pages} : Updates an existing page.
     *
     * @param pageDTO the pageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pageDTO,
     * or with status {@code 400 (Bad Request)} if the pageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pages")
    public ResponseEntity<PageDTO> updatePage(@Valid @RequestBody PageDTO pageDTO) throws URISyntaxException {
        log.debug("REST request to update Page : {}", pageDTO);
        if (pageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PageDTO result = pageService.save(pageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, pageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pages} : get all the pages.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pages in body.
     */
    @GetMapping("/pages")
    public List<PageDTO> getAllPages() {
        log.debug("REST request to get all Pages");
        return pageService.findAll();
    }

    /**
     * {@code GET  /pages/:id} : get the "id" page.
     *
     * @param id the id of the pageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pages/{id}")
    public ResponseEntity<PageDTO> getPage(@PathVariable Long id) {
        log.debug("REST request to get Page : {}", id);
        Optional<PageDTO> pageDTO = pageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pageDTO);
    }

    /**
     * {@code DELETE  /pages/:id} : delete the "id" page.
     *
     * @param id the id of the pageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pages/{id}")
    public ResponseEntity<Void> deletePage(@PathVariable Long id) {
        log.debug("REST request to delete Page : {}", id);
        pageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}