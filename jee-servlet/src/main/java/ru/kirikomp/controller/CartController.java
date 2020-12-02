package ru.kirikomp.controller;

import ru.kirikomp.service.CartService;
import ru.kirikomp.service.ToDoRepr;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class CartController implements Serializable {

    @EJB
    private CartService cartService;

    // TODO дописать контроллер и создать представление для корзины

    public void add(ToDoRepr todo) {

    }
}
