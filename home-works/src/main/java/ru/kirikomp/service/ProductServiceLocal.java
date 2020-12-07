package ru.kirikomp.service;

import ru.kirikomp.dto.ProductDto;

import javax.ejb.Local;
import java.util.List;
import java.util.concurrent.Future;

@Local
public interface ProductServiceLocal {

    void insert(ProductDto productDto);

    void update(ProductDto productDto);

    void delete(long id);

    ProductDto findById(long id);

    List<ProductDto> findAll();

    Future<ProductDto> findByIdAsync(long id);
}
