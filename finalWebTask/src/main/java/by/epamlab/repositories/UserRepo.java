package by.epamlab.repositories;

import by.epamlab.config.HibernateConf;
import by.epamlab.model.beans.Product;
import by.epamlab.model.beans.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserRepo implements UserRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepo.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User getUserByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User where username=:username");
            query.setParameter("username", username);
            User user = query.uniqueResult();
            return user;
        }
    }

    @Override
    public User getUserByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User where email=:email");
            query.setParameter("email", email);
            User user = query.uniqueResult();
            return user;
        }
    }


    @Override
    public User add(final User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return user;
        }
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public User update(final User user) {
        return null;
    }

    @Override
    public User findOne(Integer integer) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
