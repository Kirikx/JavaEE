package ru.kirikomp.persist;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class OrderItemRepositoryImpl implements OrderItemRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @TransactionAttribute
    @Override
    public void insert(OrderItem orderItem) {
        em.persist(orderItem);
    }

    @TransactionAttribute
    @Override
    public void update(OrderItem orderItem) {
        em.merge(orderItem);
    }

    @TransactionAttribute
    @Override
    public void delete(Long id) {
        OrderItem orderItem = em.find(OrderItem.class, id);
        if (orderItem != null) {
            em.remove(orderItem);
        }
    }

    @Override
    public OrderItem findById(Long id) {
        return em.find(OrderItem.class, id);
    }

    @Override
    public List<OrderItem> findAll() {
        return em.createQuery("from OrderItem", OrderItem.class).getResultList();
    }
}
