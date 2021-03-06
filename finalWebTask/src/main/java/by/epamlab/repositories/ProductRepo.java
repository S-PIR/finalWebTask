package by.epamlab.repositories;

import by.epamlab.config.HibernateConf;
import by.epamlab.exception.QuantityOutOfRangeException;
import by.epamlab.model.beans.CategoryType;
import by.epamlab.model.beans.Product;
import by.epamlab.model.beans.cart.Saleable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class ProductRepo implements ProductRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepo.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Product getProductByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<Product> query = session.createQuery("from Product where name=:name");
            query.setParameter("name", name);
            Product product = query.uniqueResult();
            return product;
        }
    }


    @Override
    public Product add(Product product) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
            return product;
        }
    }

    @Override
    public void delete(Product product) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(product);
            transaction.commit();
        }
    }

    @Override
    public Product update(Product product) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(product);
            transaction.commit();
            return product;
        }
    }

    @Override
    public int updateAll(Map<Saleable, Integer> products) throws QuantityOutOfRangeException {
        int i = 0;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            for (Map.Entry<Saleable, Integer> entry : products.entrySet()) {
                if (i > 0 && i % HibernateConf.BATCH_SIZE == 0){
                    session.flush();
                    session.clear();
                }
                Product product = (Product) entry.getKey();
                int leftProductAmount = product.getQuantity() - entry.getValue();
                if (leftProductAmount >= 0){
                    product.setQuantity(leftProductAmount);
                    session.update(product);
                    i++;
                } else {
                   throw new QuantityOutOfRangeException(product.getQuantity(), product.getId());
                }
            }
            transaction.commit();
            return i;
        }
    }

    @Override
    public long count(String category) {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = null;
            if (category == null || category.isEmpty()){
                query = session.createQuery("select count(*) from Product");
            } else {
                int cat = CategoryType.valueOf(category).ordinal();
                query = session.createQuery("select count(*) from Product where category =:cat");
                query.setParameter("cat", cat);
            }
            return query.uniqueResult();
        }
    }

    @Override
    public Product findOne(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Product> query = session.createQuery("from Product where id=:id");
            query.setParameter("id", id);
            Product product = query.uniqueResult();
            return product;
        }
    }

    @Override
    public List<Product> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Product> query = session.createQuery("from Product");
            List<Product> products = query.list();
            return products;
        }
    }

    @Override
    public List<Product> findAllByCategory(String category) {
        int cat = CategoryType.valueOf(category).ordinal();
        String hql = "from Product where category = :cat";
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(hql);
            query.setParameter("cat", cat);
            return query.list();
        }
    }

    @Override
    public List<Product> findAllByPageAndOrder(int limit, int offset, String order) {
        SortDirection sd = SortDirection.valueOf(order.toUpperCase());
        String hql = "from Product" + sd.getQuery();
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(hql);
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            return query.list();
        }
    }

//    @Override
//    public List<Product> findAllByCategoryAndPage(String category, int limit, int offset) {
//        int cat = CategoryType.valueOf(category).ordinal();
//        String hql = "from Product where category = :cat";
//        try (Session session = sessionFactory.openSession()) {
//            Query query = session.createQuery(hql);
//            query.setParameter("cat", cat);
//            query.setFirstResult(offset);
//            query.setMaxResults(limit);
//            return query.list();
//        }
//    }

    @Override
    public List<Product> findAllByCriterion(String criterion) {
        String pattern = "%" + criterion + "%";
        String hql = "from Product where name like :pattern";
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(hql);
            query.setParameter("pattern", pattern);
            return query.list();
        }
    }

    @Override
    public List<Product> findAllByCategoryAndPageAndOrder(String category, int limit, int offset, String order) {
        int cat = CategoryType.valueOf(category).ordinal();
        SortDirection sd = SortDirection.valueOf(order.toUpperCase());
        String hql = "from Product where category = :cat" + sd.getQuery();
        System.out.println(hql);
        try (Session session = sessionFactory.openSession()) {
            Query<Product> query = session.createQuery(hql);
            query.setParameter("cat", cat);
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            return query.list();
        }
    }

    @Override
    public List<Product> findAllByCategoryAndCriterion(String category, String criterion) {
        try (Session session = sessionFactory.openSession()) {
            int cat = CategoryType.valueOf(category).ordinal();
            String pattern = "%" + criterion + "%";
            String hql = "from Product where category = :cat and name like :pattern";
            Query query = session.createQuery(hql);
            query.setParameter("cat", cat);
            query.setParameter("pattern", pattern);
            return query.list();
        }
    }


}
