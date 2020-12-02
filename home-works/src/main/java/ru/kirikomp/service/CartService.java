package ru.kirikomp.service;

import ru.kirikomp.dto.ProductDto;
import ru.kirikomp.persist.OrderItem;
import ru.kirikomp.persist.ProductRepository;

import javax.ejb.Stateful;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Stateful
public class CartService implements Serializable {

    @Inject
    private ProductRepository productRepository;

    List<OrderItem> orderItems = new ArrayList<>();

    public List<OrderItem> getAll() {
        return orderItems;
    }

    public void add(ProductDto productDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(productRepository.findById(productDto.getId()));
        for (OrderItem item : orderItems) {
            if (item.getProduct() != null && item.getProduct().getId().equals(productDto.getId())) {
                orderItem = item;
            }
        }
        orderItem.setCount(orderItem.getCount() + 1);
    }

    public void delete(ProductDto productDto) {
        List<OrderItem> newList = new ArrayList<>();
        for (OrderItem item : orderItems) {
            if (!item.getProduct().getId().equals(productDto.getId())) {
                newList.add(item);
            }
        }
        orderItems = newList;
    }
}
