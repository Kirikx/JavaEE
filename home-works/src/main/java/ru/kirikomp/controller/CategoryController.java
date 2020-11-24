package ru.kirikomp.controller;

import ru.kirikomp.persist.Category;
import ru.kirikomp.persist.CategoryRepository;
import ru.kirikomp.persist.Product;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Named
@SessionScoped
public class CategoryController implements Serializable {

    @Inject
    private CategoryRepository categoryRepository;

    private Category category;

    private List<Category> categories;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        this.categories = categoryRepository.findAll();
    }

    public List<Category> getAll() throws SQLException {
        return categories;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String edit(Category category) {
        this.category = category;
        return "/category_form.xhtml?faces-redirect=true";
    }

    public void delete(Category category) throws SQLException {
        categoryRepository.delete(category.getId());
    }

    public String save() throws SQLException {
        if (this.category.getId() == null) {
            categoryRepository.insert(category);
        } else {
            categoryRepository.update(category);
        }
        return "/categories.xhtml?faces-redirect=true";
    }

    public String create() {
        this.category = new Category();
        return "/category_form.xhtml?faces-redirect=true";
    }
}
