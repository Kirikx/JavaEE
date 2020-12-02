package ru.kirikomp.controller;

import ru.kirikomp.persist.Category;
import ru.kirikomp.persist.CategoryRepository;
import ru.kirikomp.persist.Product;
import ru.kirikomp.persist.ProductRepository;

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
    private ProductRepository productRepository;

    @Inject
    private CategoryRepository categoryRepository;

    private Product product;

    private List<Product> products;

    private List<Category> categories;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        this.products = productRepository.findAll();
        this.categories = categoryRepository.findAll();
    }

    public List<Product> getAll() throws SQLException {
        return products;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String edit(Product product) {
        this.product = product;
        return "/product_form.xhtml?faces-redirect=true";
    }

    public void delete(Product todo) throws SQLException {
        productRepository.delete(todo.getId());
    }

    public String save() throws SQLException {
        if (this.product.getId() == null) {
            productRepository.insert(product);
        } else {
            productRepository.update(product);
        }
        return "/products.xhtml?faces-redirect=true";
    }

    public String create() {
        this.product = new Product();
        return "/product_form.xhtml?faces-redirect=true";
    }

    public List<Category> allCategories() {
        return categories;
    }
}
