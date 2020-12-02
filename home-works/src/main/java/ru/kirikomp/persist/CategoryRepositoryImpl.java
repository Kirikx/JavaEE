package ru.kirikomp.persist;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CategoryRepositoryImpl implements CategoryRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @TransactionAttribute
    @Override
    public void insert(Category category) {
        em.persist(category);
    }

    @TransactionAttribute
    @Override
    public void update(Category category) {
        em.merge(category);
    }

    @Override
    public void delete(Long id) {
        Category category = em.find(Category.class, id);
        if (category != null) {
            em.remove(category);
        }
    }

    @Override
    public Category findById(Long id) {
        return em.find(Category.class, id);
    }

    @Override
    public List<Category> findAll() {
        return em.createQuery("from Category", Category.class).getResultList();
    }
}
