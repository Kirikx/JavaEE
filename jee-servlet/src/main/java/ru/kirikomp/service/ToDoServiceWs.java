package ru.kirikomp.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface ToDoServiceWs {

    @WebMethod
    List<ToDoRepr> findAll();

    @WebMethod
    ToDoRepr findById(long id);

    @WebMethod
    void insert(ToDoRepr toDoRepr);
}
