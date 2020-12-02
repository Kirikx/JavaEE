package ru.kirikomp.controller;

import ru.kirikomp.dto.ProductDto;
import ru.kirikomp.persist.OrderItem;
import ru.kirikomp.service.CartService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CartController implements Serializable {

    @EJB
    private CartService cartService;

    public List<OrderItem> getAll() {
        return cartService.getAll();
    }

    public void add(ProductDto productDto) {
        cartService.add(productDto);
    }

    public void delete(ProductDto productDto) {
        cartService.delete(productDto);
    }
}
