package ru.kirikomp.persist;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ToDoCategoryRepository {

    void insert(ToDoCategory toDoCategory);

    void update(ToDoCategory toDoCategory);

    void delete(Long id);

    ToDoCategory findById(Long id);

    public List<ToDoCategory> findAll();
}
