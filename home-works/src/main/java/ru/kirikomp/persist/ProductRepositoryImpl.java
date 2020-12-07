package ru.kirikomp.persist;

import ru.kirikomp.dto.ProductDto;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @TransactionAttribute
    @Override
    public void insert(Product product) {
        em.persist(product);
    }

    @TransactionAttribute
    @Override
    public void update(Product product) {
        em.merge(product);
    }

    @TransactionAttribute
    @Override
    public void delete(Long id) {
        Product product = em.find(Product.class, id);
        if (product != null) {
            em.remove(product);
        }
    }

    public Product findById(Long id) {
        return em.find(Product.class, id);
    }

    public List<Product> findAll() {
        return em.createQuery("from Product", Product.class).getResultList();
    }

    @Override
    public ProductDto findDtoById(long id) {
        return em.createQuery("select new ru.kirikomp.dto.ProductDto(p.id, p.title, p.cost, c) " +
                "from Product p " +
                " left join p.category c " +
                "where p.id = :id", ProductDto.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<ProductDto> findAllDto() {
        return em.createQuery("select new ru.kirikomp.dto.ProductDto(p.id, p.title, p.cost, c) " +
                "from Product p " +
                " left join p.category c ", ProductDto.class)
                .getResultList();
    }
}
