package ru.kirikomp.service;

import ru.kirikomp.dto.ProductDto;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface ProductServiceWs {

    @WebMethod
    List<ProductDto> findAll();

    @WebMethod
    ProductDto findById(long id);

    @WebMethod
    void insert(ProductDto toDoRepr);
}
