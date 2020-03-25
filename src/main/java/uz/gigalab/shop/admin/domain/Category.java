package uz.gigalab.shop.admin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A Category.
 */
@Entity
@Table(name = "category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name_uz", length = 255, nullable = false)
    private String nameUz;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "alias", length = 255, nullable = false, unique = true)
    private String alias;

    @NotNull
    @Min(value = -9999)
    @Max(value = 9999)
    @Column(name = "sorting", nullable = false)
    private Integer sorting;

    @NotNull
    @Column(name = "status", nullable = false)
    private Boolean status;

    @NotNull
    @Column(name = "main", nullable = false)
    private Boolean main;

    @OneToOne
    @JoinColumn(unique = true)
    private File file;

    @OneToOne
    @JoinColumn(unique = true)
    private Page page;

    @OneToOne
    @JoinColumn(unique = true)
    private Page pageUz;

    @OneToMany(mappedBy = "parent")
    private Set<Category> children = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("children")
    private Category parent;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Category createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Category updatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public Category name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameUz() {
        return nameUz;
    }

    public Category nameUz(String nameUz) {
        this.nameUz = nameUz;
        return this;
    }

    public void setNameUz(String nameUz) {
        this.nameUz = nameUz;
    }

    public String getAlias() {
        return alias;
    }

    public Category alias(String alias) {
        this.alias = alias;
        return this;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getSorting() {
        return sorting;
    }

    public Category sorting(Integer sorting) {
        this.sorting = sorting;
        return this;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
    }

    public Boolean isStatus() {
        return status;
    }

    public Category status(Boolean status) {
        this.status = status;
        return this;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean isMain() {
        return main;
    }

    public Category main(Boolean main) {
        this.main = main;
        return this;
    }

    public void setMain(Boolean main) {
        this.main = main;
    }

    public File getFile() {
        return file;
    }

    public Category file(File file) {
        this.file = file;
        return this;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Page getPage() {
        return page;
    }

    public Category page(Page page) {
        this.page = page;
        return this;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Page getPageUz() {
        return pageUz;
    }

    public Category pageUz(Page page) {
        this.pageUz = page;
        return this;
    }

    public void setPageUz(Page page) {
        this.pageUz = page;
    }

    public Set<Category> getChildren() {
        return children;
    }

    public Category children(Set<Category> categories) {
        this.children = categories;
        return this;
    }

    public Category addChildren(Category category) {
        this.children.add(category);
        category.setParent(this);
        return this;
    }

    public Category removeChildren(Category category) {
        this.children.remove(category);
        category.setParent(null);
        return this;
    }

    public void setChildren(Set<Category> categories) {
        this.children = categories;
    }

    public Category getParent() {
        return parent;
    }

    public Category parent(Category category) {
        this.parent = category;
        return this;
    }

    public void setParent(Category category) {
        this.parent = category;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Category)) {
            return false;
        }
        return id != null && id.equals(((Category) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Category{" +
            "id=" + getId() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", name='" + getName() + "'" +
            ", nameUz='" + getNameUz() + "'" +
            ", alias='" + getAlias() + "'" +
            ", sorting=" + getSorting() +
            ", status='" + isStatus() + "'" +
            ", main='" + isMain() + "'" +
            "}";
    }
}
