package ru.kirikomp.persist;

import ru.kirikomp.dto.ProductDto;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProductRepository {

    void insert(Product product);

    void update(Product product);

    void delete(Long id);

    Product findById(Long id);

    List<Product> findAll();

    ProductDto findDtoById(long id);

    List<ProductDto> findAllDto();
}
