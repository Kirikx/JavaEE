package ru.kirikomp.controller;

import ru.kirikomp.persist.Product;
import ru.kirikomp.persist.ProductRepository;

import javax.enterprise.context.SessionScoped;
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

    private Product product;

    public List<Product> getAll() throws SQLException {
        return productRepository.findAll();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String edit(Product todo) {
        this.product = todo;
        return "/product_form.xhtml?faces-redirect=true";
    }

    public void delete(Product todo) throws SQLException {
        productRepository.deleteById(todo.getId());
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
}
