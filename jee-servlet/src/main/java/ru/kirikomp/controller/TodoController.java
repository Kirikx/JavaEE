package ru.kirikomp.controller;

import ru.kirikomp.persist.ToDoCategory;
import ru.kirikomp.persist.ToDoCategoryRepository;
import ru.kirikomp.service.ToDoRepr;
import ru.kirikomp.service.ToDoServiceLocal;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Named
@SessionScoped
public class TodoController implements Serializable {

    @Inject
    private ToDoServiceLocal toDoServiceLocal;

    @Inject
    private ToDoCategoryRepository toDoCategoryRepository;

    private ToDoRepr todo;

    private List<ToDoRepr> todos;

    private List<ToDoCategory> toDoCategories;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        this.todos = toDoServiceLocal.findAll();
        this.toDoCategories = toDoCategoryRepository.findAll();
    }

    public List<ToDoRepr> getAllTodos() throws SQLException {
        return todos;
    }

    public ToDoRepr getTodo() {
        return todo;
    }

    public void setTodo(ToDoRepr todo) {
        this.todo = todo;
    }

    public String editTodo(ToDoRepr todo) {
        this.todo = todo;
        return "/todo.xhtml?faces-redirect=true";
    }

    public void deleteTodo(ToDoRepr todo) throws SQLException {
        toDoServiceLocal.delete(todo.getId());
    }

    public String saveTodo() throws SQLException {
        if (this.todo.getId() == null) {
            toDoServiceLocal.insert(todo);
        } else {
            toDoServiceLocal.update(todo);
        }
        return "/index.xhtml?faces-redirect=true";
    }

    public String createTodo() {
        this.todo = new ToDoRepr();
        return "/todo.xhtml?faces-redirect=true";
    }

    public List<ToDoCategory> allCategories() {
        return toDoCategories;
    }
}
