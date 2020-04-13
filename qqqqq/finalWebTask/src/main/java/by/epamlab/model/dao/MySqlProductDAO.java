package by.epamlab.model.dao;

import by.epamlab.config.HibernateConf;
import by.epamlab.model.beans.CategoryType;
import by.epamlab.model.beans.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public class MySqlProductDAO implements ProductDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Product> getProducts(String category) {
        try (Session session = sessionFactory.openSession()) {
            if (category == null){
                return session.createQuery("from Product").list();
            } else {
                int cat = CategoryType.valueOf(category).ordinal();
                String hql = "from Product where category = :cat";
                Query query = session.createQuery(hql);
                query.setParameter("cat", cat);
                return query.list();
            }
        }
    }

}
