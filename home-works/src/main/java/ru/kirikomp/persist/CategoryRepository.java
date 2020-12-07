package ru.kirikomp.persist;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CategoryRepository {

    void insert(Category category);

    void update(Category category);

    void delete(Long id);

    Category findById(Long id);

    List<Category> findAll();
}