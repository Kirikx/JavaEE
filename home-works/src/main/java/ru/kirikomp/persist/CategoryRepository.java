package ru.kirikomp.persist;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Named
@ApplicationScoped
public class CategoryRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Transactional
    public void insert(Category category) {
        em.persist(category);
    }

    @Transactional
    public void update(Category category) {
        em.merge(category);
    }

    @Transactional
    public void delete(Long id) {
        Category category = em.find(Category.class, id);
        if (category != null) {
            em.remove(category);
        }
    }

    public Category findById(Long id) {
        return em.find(Category.class, id);
    }

    public List<Category> findAll() {
        return em.createQuery("from Category", Category.class).getResultList();
    }
}
