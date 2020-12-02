package ru.kirikomp.controller;

import ru.kirikomp.dto.ProductDto;
import ru.kirikomp.persist.Category;
import ru.kirikomp.persist.CategoryRepository;
import ru.kirikomp.service.ProductServiceLocal;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {

    @Inject
    private ProductServiceLocal productServiceLocal;

    @Inject
    private CategoryRepository categoryRepository;

    private ProductDto product;

    private List<ProductDto> products;

    private List<Category> categories;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        this.products = productServiceLocal.findAll();
        this.categories = categoryRepository.findAll();
    }

    public List<ProductDto> getAll() throws SQLException {
        return products;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public String edit(ProductDto product) {
        this.product = product;
        return "/product_form.xhtml?faces-redirect=true";
    }

    public void delete(ProductDto todo) throws SQLException {
        productServiceLocal.delete(todo.getId());
    }

    public String save() throws SQLException {
        if (this.product.getId() == null) {
            productServiceLocal.insert(product);
        } else {
            productServiceLocal.update(product);
        }
        return "/products.xhtml?faces-redirect=true";
    }

    public String create() {
        this.product = new ProductDto();
        return "/product_form.xhtml?faces-redirect=true";
    }

    public List<Category> allCategories() {
        return categories;
    }
}
