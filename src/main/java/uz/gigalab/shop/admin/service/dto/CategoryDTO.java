package uz.gigalab.shop.admin.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link uz.gigalab.shop.admin.domain.Category} entity.
 */
public class CategoryDTO implements Serializable {
    
    private Long id;

    private Instant createdAt;

    private Instant updatedAt;

    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    @NotNull
    @Size(min = 1, max = 255)
    private String nameUz;

    @NotNull
    @Size(min = 1, max = 255)
    private String alias;

    @NotNull
    @Min(value = -9999)
    @Max(value = 9999)
    private Integer sorting;

    @NotNull
    private Boolean status;

    @NotNull
    private Boolean main;


    private Long fileId;

    private Long pageId;

    private Long pageUzId;

    private Long parentId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameUz() {
        return nameUz;
    }

    public void setNameUz(String nameUz) {
        this.nameUz = nameUz;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getSorting() {
        return sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean isMain() {
        return main;
    }

    public void setMain(Boolean main) {
        this.main = main;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public Long getPageUzId() {
        return pageUzId;
    }

    public void setPageUzId(Long pageId) {
        this.pageUzId = pageId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long categoryId) {
        this.parentId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CategoryDTO categoryDTO = (CategoryDTO) o;
        if (categoryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), categoryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
            "id=" + getId() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", name='" + getName() + "'" +
            ", nameUz='" + getNameUz() + "'" +
            ", alias='" + getAlias() + "'" +
            ", sorting=" + getSorting() +
            ", status='" + isStatus() + "'" +
            ", main='" + isMain() + "'" +
            ", fileId=" + getFileId() +
            ", pageId=" + getPageId() +
            ", pageUzId=" + getPageUzId() +
            ", parentId=" + getParentId() +
            "}";
    }
}
