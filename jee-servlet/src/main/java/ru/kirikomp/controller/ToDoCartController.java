package ru.kirikomp.controller;

import ru.kirikomp.service.ToDoCartService;
import ru.kirikomp.service.ToDoRepr;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class ToDoCartController implements Serializable {

    //    @EJB
    private ToDoCartService cartService;

    // TODO дописать контроллер и создать представление для корзины

    public void add(ToDoRepr todo) {

    }
}
