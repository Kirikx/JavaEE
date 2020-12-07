package ru.kirikomp.rest;

import ru.kirikomp.dto.ProductDto;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/v1/product")
public interface ProductServiceRs {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(ProductDto productDto);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(ProductDto productDto);

    @DELETE
    @Path("/{id}")
    void delete(@PathParam("id") long id);

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    ProductDto findById(@PathParam("id") long id);

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductDto> findAll();

}
