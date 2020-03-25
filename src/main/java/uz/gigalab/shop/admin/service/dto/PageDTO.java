package uz.gigalab.shop.admin.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link uz.gigalab.shop.admin.domain.Page} entity.
 */
public class PageDTO implements Serializable {
    
    private Long id;

    @Size(max = 255)
    private String seoName;

    @Size(max = 255)
    private String seoTitle;

    @Size(max = 255)
    private String seoDescription;

    @Size(max = 255)
    private String seoKeys;

    private String contentTop;

    private String contentBottom;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeoName() {
        return seoName;
    }

    public void setSeoName(String seoName) {
        this.seoName = seoName;
    }

    public String getSeoTitle() {
        return seoTitle;
    }

    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle;
    }

    public String getSeoDescription() {
        return seoDescription;
    }

    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription;
    }

    public String getSeoKeys() {
        return seoKeys;
    }

    public void setSeoKeys(String seoKeys) {
        this.seoKeys = seoKeys;
    }

    public String getContentTop() {
        return contentTop;
    }

    public void setContentTop(String contentTop) {
        this.contentTop = contentTop;
    }

    public String getContentBottom() {
        return contentBottom;
    }

    public void setContentBottom(String contentBottom) {
        this.contentBottom = contentBottom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PageDTO pageDTO = (PageDTO) o;
        if (pageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PageDTO{" +
            "id=" + getId() +
            ", seoName='" + getSeoName() + "'" +
            ", seoTitle='" + getSeoTitle() + "'" +
            ", seoDescription='" + getSeoDescription() + "'" +
            ", seoKeys='" + getSeoKeys() + "'" +
            ", contentTop='" + getContentTop() + "'" +
            ", contentBottom='" + getContentBottom() + "'" +
            "}";
    }
}
