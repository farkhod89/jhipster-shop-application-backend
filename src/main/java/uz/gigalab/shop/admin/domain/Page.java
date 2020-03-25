package uz.gigalab.shop.admin.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Page.
 */
@Entity
@Table(name = "page")
public class Page implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "seo_name", length = 255)
    private String seoName;

    @Size(max = 255)
    @Column(name = "seo_title", length = 255)
    private String seoTitle;

    @Size(max = 255)
    @Column(name = "seo_description", length = 255)
    private String seoDescription;

    @Size(max = 255)
    @Column(name = "seo_keys", length = 255)
    private String seoKeys;

    @Column(name = "content_top")
    private String contentTop;

    @Column(name = "content_bottom")
    private String contentBottom;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeoName() {
        return seoName;
    }

    public Page seoName(String seoName) {
        this.seoName = seoName;
        return this;
    }

    public void setSeoName(String seoName) {
        this.seoName = seoName;
    }

    public String getSeoTitle() {
        return seoTitle;
    }

    public Page seoTitle(String seoTitle) {
        this.seoTitle = seoTitle;
        return this;
    }

    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle;
    }

    public String getSeoDescription() {
        return seoDescription;
    }

    public Page seoDescription(String seoDescription) {
        this.seoDescription = seoDescription;
        return this;
    }

    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription;
    }

    public String getSeoKeys() {
        return seoKeys;
    }

    public Page seoKeys(String seoKeys) {
        this.seoKeys = seoKeys;
        return this;
    }

    public void setSeoKeys(String seoKeys) {
        this.seoKeys = seoKeys;
    }

    public String getContentTop() {
        return contentTop;
    }

    public Page contentTop(String contentTop) {
        this.contentTop = contentTop;
        return this;
    }

    public void setContentTop(String contentTop) {
        this.contentTop = contentTop;
    }

    public String getContentBottom() {
        return contentBottom;
    }

    public Page contentBottom(String contentBottom) {
        this.contentBottom = contentBottom;
        return this;
    }

    public void setContentBottom(String contentBottom) {
        this.contentBottom = contentBottom;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Page)) {
            return false;
        }
        return id != null && id.equals(((Page) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Page{" +
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
