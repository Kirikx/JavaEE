package ru.kirikomp.persist;

import ru.kirikomp.service.ToDoRepr;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ToDoRepository {

    void insert(ToDo toDo);

    void update(ToDo toDo);

    void delete(Long id);

    ToDo findById(Long id);

    List<ToDo> findAll();

    ToDoRepr findToDoReprById(long id);

    List<ToDoRepr> findAllToDoRepr();
}
