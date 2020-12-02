package ru.kirikomp.persist;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Named
@ApplicationScoped
public class OrderItemRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Transactional
    public void insert(OrderItem orderItem) {
        em.persist(orderItem);
    }

    @Transactional
    public void update(OrderItem orderItem) {
        em.merge(orderItem);
    }

    @Transactional
    public void delete(Long id) {
        OrderItem orderItem = em.find(OrderItem.class, id);
        if (orderItem != null) {
            em.remove(orderItem);
        }
    }

    public OrderItem findById(Long id) {
        return em.find(OrderItem.class, id);
    }

    public List<OrderItem> findAll() {
        return em.createQuery("from OrderItem", OrderItem.class).getResultList();
    }
}
