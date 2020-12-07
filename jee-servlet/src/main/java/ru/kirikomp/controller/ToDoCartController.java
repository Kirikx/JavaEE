package ru.kirikomp.controller;

import ru.kirikomp.service.ToDoCartService;
import ru.kirikomp.service.ToDoRepr;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ToDoCartController implements Serializable {

    @EJB
    private ToDoCartService cartService;

    public List<ToDoRepr> getAllItems() {
        return cartService.getAll();
    }

    public void add(ToDoRepr todo) {
        cartService.add(todo);
    }

    public void delete(ToDoRepr todo) {
        cartService.delete(todo.getId());
    }
}
