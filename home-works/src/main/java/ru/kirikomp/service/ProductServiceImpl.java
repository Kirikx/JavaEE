package ru.kirikomp.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kirikomp.dto.ProductDto;
import ru.kirikomp.persist.Category;
import ru.kirikomp.persist.CategoryRepository;
import ru.kirikomp.persist.Product;
import ru.kirikomp.persist.ProductRepository;
import ru.kirikomp.rest.ProductServiceRs;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import javax.jws.WebService;
import java.util.List;
import java.util.concurrent.Future;

@Stateless
@WebService(endpointInterface = "ru.kirikomp.service.ProductServiceWs", serviceName = "ProductService")
public class ProductServiceImpl implements ProductServiceLocal, ProductServiceRs, ProductServiceWs {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Inject
    private ProductRepository productRepository;

    @Inject
    private CategoryRepository categoryRepository;

    @TransactionAttribute
    @Override
    public void insert(ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategoryId());
        productRepository.insert(new Product(null, productDto.getTitle(), productDto.getCost(), category));
    }

    @TransactionAttribute
    @Override
    public void update(ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategoryId());
        productRepository.update(new Product(productDto.getId(), productDto.getTitle(), productDto.getCost(), category));
    }

    @TransactionAttribute
    @Override
    public void delete(long id) {
        productRepository.delete(id);
    }

    @Override
    public ProductDto findById(long id) {
        return productRepository.findDtoById(id);
    }

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAllDto();
    }

    @Asynchronous
    @Override
    public Future<ProductDto> findByIdAsync(long id) {
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<>(productRepository.findDtoById(id));
    }
}
