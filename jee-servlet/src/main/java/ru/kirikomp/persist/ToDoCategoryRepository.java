package ru.kirikomp.persist;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Named
@ApplicationScoped
public class ToDoCategoryRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Transactional
    public void insert(ToDoCategory toDoCategory) {
        em.persist(toDoCategory);
    }

    @Transactional
    public void update(ToDoCategory toDoCategory) {
        em.merge(toDoCategory);
    }

    @Transactional
    public void delete(Long id) {
        ToDoCategory toDoCategory = em.find(ToDoCategory.class, id);
        if (toDoCategory != null) {
            em.remove(toDoCategory);
        }
    }

    public ToDoCategory findById(Long id) {
        return em.find(ToDoCategory.class, id);
    }

    public List<ToDoCategory> findAll() {
        return em.createQuery("from ToDoCategory ", ToDoCategory.class).getResultList();
    }
}
