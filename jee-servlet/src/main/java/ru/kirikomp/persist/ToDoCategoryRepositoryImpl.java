package ru.kirikomp.persist;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ToDoCategoryRepositoryImpl implements ToDoCategoryRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @TransactionAttribute
    public void insert(ToDoCategory toDoCategory) {
        em.persist(toDoCategory);
    }

    @TransactionAttribute
    public void update(ToDoCategory toDoCategory) {
        em.merge(toDoCategory);
    }

    @TransactionAttribute
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
