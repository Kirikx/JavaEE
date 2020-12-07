package ru.kirikomp.dto;

import ru.kirikomp.persist.Category;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ProductDto implements Serializable {

    private Long id;

    @NotEmpty
    private String title;

    @NotNull
    private Long cost;

    private Long categoryId;

    private String categoryName;

    public ProductDto() {
    }

    public ProductDto(Long id, String title, Long cost, Category category) {
        this.id = id;
        this.title = title;
        this.cost = cost;
        this.categoryId = category != null ? category.getId() : null;
        this.categoryName = category != null ? category.getName() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
