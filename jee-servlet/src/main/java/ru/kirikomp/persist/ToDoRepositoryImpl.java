package ru.kirikomp.persist;

import ru.kirikomp.service.ToDoRepr;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ToDoRepositoryImpl implements ToDoRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @TransactionAttribute
    public void insert(ToDo toDo) {
        em.persist(toDo);
    }

    @TransactionAttribute
    public void update(ToDo toDo) {
        em.merge(toDo);
    }

    @TransactionAttribute
    public void delete(Long id) {
        ToDo toDo = em.find(ToDo.class, id);
        if (toDo != null) {
            em.remove(toDo);
        }
    }

    public ToDo findById(Long id) {
        return em.find(ToDo.class, id);
    }

    public List<ToDo> findAll() {
        return em.createQuery("from ToDo", ToDo.class).getResultList();
    }

    @Override
    public ToDoRepr findToDoReprById(long id) {
        return em.createQuery("select new ru.kirikomp.service.ToDoRepr(t.id, t.description, t.targetDate, c) " +
                "from ToDo t " +
                " left join t.toDoCategory c " +
                "where t.id = :id", ToDoRepr.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<ToDoRepr> findAllToDoRepr() {
        return em.createQuery("select new ru.kirikomp.service.ToDoRepr(t.id, t.description, t.targetDate, c) " +
                "from ToDo t " +
                " left join t.toDoCategory c ", ToDoRepr.class)
                .getResultList();
    }
}
