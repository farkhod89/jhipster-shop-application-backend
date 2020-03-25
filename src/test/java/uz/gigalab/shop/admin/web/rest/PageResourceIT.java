package uz.gigalab.shop.admin.web.rest;

import uz.gigalab.shop.admin.JhipsterShopApplicationApp;
import uz.gigalab.shop.admin.domain.Page;
import uz.gigalab.shop.admin.repository.PageRepository;
import uz.gigalab.shop.admin.service.PageService;
import uz.gigalab.shop.admin.service.dto.PageDTO;
import uz.gigalab.shop.admin.service.mapper.PageMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PageResource} REST controller.
 */
@SpringBootTest(classes = JhipsterShopApplicationApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class PageResourceIT {

    private static final String DEFAULT_SEO_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SEO_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SEO_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_SEO_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_SEO_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_SEO_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_SEO_KEYS = "AAAAAAAAAA";
    private static final String UPDATED_SEO_KEYS = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENT_TOP = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT_TOP = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENT_BOTTOM = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT_BOTTOM = "BBBBBBBBBB";

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private PageMapper pageMapper;

    @Autowired
    private PageService pageService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPageMockMvc;

    private Page page;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Page createEntity(EntityManager em) {
        Page page = new Page()
            .seoName(DEFAULT_SEO_NAME)
            .seoTitle(DEFAULT_SEO_TITLE)
            .seoDescription(DEFAULT_SEO_DESCRIPTION)
            .seoKeys(DEFAULT_SEO_KEYS)
            .contentTop(DEFAULT_CONTENT_TOP)
            .contentBottom(DEFAULT_CONTENT_BOTTOM);
        return page;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Page createUpdatedEntity(EntityManager em) {
        Page page = new Page()
            .seoName(UPDATED_SEO_NAME)
            .seoTitle(UPDATED_SEO_TITLE)
            .seoDescription(UPDATED_SEO_DESCRIPTION)
            .seoKeys(UPDATED_SEO_KEYS)
            .contentTop(UPDATED_CONTENT_TOP)
            .contentBottom(UPDATED_CONTENT_BOTTOM);
        return page;
    }

    @BeforeEach
    public void initTest() {
        page = createEntity(em);
    }

    @Test
    @Transactional
    public void createPage() throws Exception {
        int databaseSizeBeforeCreate = pageRepository.findAll().size();

        // Create the Page
        PageDTO pageDTO = pageMapper.toDto(page);
        restPageMockMvc.perform(post("/api/pages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pageDTO)))
            .andExpect(status().isCreated());

        // Validate the Page in the database
        List<Page> pageList = pageRepository.findAll();
        assertThat(pageList).hasSize(databaseSizeBeforeCreate + 1);
        Page testPage = pageList.get(pageList.size() - 1);
        assertThat(testPage.getSeoName()).isEqualTo(DEFAULT_SEO_NAME);
        assertThat(testPage.getSeoTitle()).isEqualTo(DEFAULT_SEO_TITLE);
        assertThat(testPage.getSeoDescription()).isEqualTo(DEFAULT_SEO_DESCRIPTION);
        assertThat(testPage.getSeoKeys()).isEqualTo(DEFAULT_SEO_KEYS);
        assertThat(testPage.getContentTop()).isEqualTo(DEFAULT_CONTENT_TOP);
        assertThat(testPage.getContentBottom()).isEqualTo(DEFAULT_CONTENT_BOTTOM);
    }

    @Test
    @Transactional
    public void createPageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pageRepository.findAll().size();

        // Create the Page with an existing ID
        page.setId(1L);
        PageDTO pageDTO = pageMapper.toDto(page);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPageMockMvc.perform(post("/api/pages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Page in the database
        List<Page> pageList = pageRepository.findAll();
        assertThat(pageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPages() throws Exception {
        // Initialize the database
        pageRepository.saveAndFlush(page);

        // Get all the pageList
        restPageMockMvc.perform(get("/api/pages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(page.getId().intValue())))
            .andExpect(jsonPath("$.[*].seoName").value(hasItem(DEFAULT_SEO_NAME)))
            .andExpect(jsonPath("$.[*].seoTitle").value(hasItem(DEFAULT_SEO_TITLE)))
            .andExpect(jsonPath("$.[*].seoDescription").value(hasItem(DEFAULT_SEO_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].seoKeys").value(hasItem(DEFAULT_SEO_KEYS)))
            .andExpect(jsonPath("$.[*].contentTop").value(hasItem(DEFAULT_CONTENT_TOP)))
            .andExpect(jsonPath("$.[*].contentBottom").value(hasItem(DEFAULT_CONTENT_BOTTOM)));
    }
    
    @Test
    @Transactional
    public void getPage() throws Exception {
        // Initialize the database
        pageRepository.saveAndFlush(page);

        // Get the page
        restPageMockMvc.perform(get("/api/pages/{id}", page.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(page.getId().intValue()))
            .andExpect(jsonPath("$.seoName").value(DEFAULT_SEO_NAME))
            .andExpect(jsonPath("$.seoTitle").value(DEFAULT_SEO_TITLE))
            .andExpect(jsonPath("$.seoDescription").value(DEFAULT_SEO_DESCRIPTION))
            .andExpect(jsonPath("$.seoKeys").value(DEFAULT_SEO_KEYS))
            .andExpect(jsonPath("$.contentTop").value(DEFAULT_CONTENT_TOP))
            .andExpect(jsonPath("$.contentBottom").value(DEFAULT_CONTENT_BOTTOM));
    }

    @Test
    @Transactional
    public void getNonExistingPage() throws Exception {
        // Get the page
        restPageMockMvc.perform(get("/api/pages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePage() throws Exception {
        // Initialize the database
        pageRepository.saveAndFlush(page);

        int databaseSizeBeforeUpdate = pageRepository.findAll().size();

        // Update the page
        Page updatedPage = pageRepository.findById(page.getId()).get();
        // Disconnect from session so that the updates on updatedPage are not directly saved in db
        em.detach(updatedPage);
        updatedPage
            .seoName(UPDATED_SEO_NAME)
            .seoTitle(UPDATED_SEO_TITLE)
            .seoDescription(UPDATED_SEO_DESCRIPTION)
            .seoKeys(UPDATED_SEO_KEYS)
            .contentTop(UPDATED_CONTENT_TOP)
            .contentBottom(UPDATED_CONTENT_BOTTOM);
        PageDTO pageDTO = pageMapper.toDto(updatedPage);

        restPageMockMvc.perform(put("/api/pages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pageDTO)))
            .andExpect(status().isOk());

        // Validate the Page in the database
        List<Page> pageList = pageRepository.findAll();
        assertThat(pageList).hasSize(databaseSizeBeforeUpdate);
        Page testPage = pageList.get(pageList.size() - 1);
        assertThat(testPage.getSeoName()).isEqualTo(UPDATED_SEO_NAME);
        assertThat(testPage.getSeoTitle()).isEqualTo(UPDATED_SEO_TITLE);
        assertThat(testPage.getSeoDescription()).isEqualTo(UPDATED_SEO_DESCRIPTION);
        assertThat(testPage.getSeoKeys()).isEqualTo(UPDATED_SEO_KEYS);
        assertThat(testPage.getContentTop()).isEqualTo(UPDATED_CONTENT_TOP);
        assertThat(testPage.getContentBottom()).isEqualTo(UPDATED_CONTENT_BOTTOM);
    }

    @Test
    @Transactional
    public void updateNonExistingPage() throws Exception {
        int databaseSizeBeforeUpdate = pageRepository.findAll().size();

        // Create the Page
        PageDTO pageDTO = pageMapper.toDto(page);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPageMockMvc.perform(put("/api/pages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Page in the database
        List<Page> pageList = pageRepository.findAll();
        assertThat(pageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePage() throws Exception {
        // Initialize the database
        pageRepository.saveAndFlush(page);

        int databaseSizeBeforeDelete = pageRepository.findAll().size();

        // Delete the page
        restPageMockMvc.perform(delete("/api/pages/{id}", page.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Page> pageList = pageRepository.findAll();
        assertThat(pageList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
