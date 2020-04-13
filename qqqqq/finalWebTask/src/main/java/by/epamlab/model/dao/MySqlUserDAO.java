package by.epamlab.model.dao;

import by.epamlab.config.HibernateConf;
import by.epamlab.model.beans.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MySqlUserDAO implements UserDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public User findUserByUsername(String username) {
        return  sessionFactory.getCurrentSession().get(User.class, username);
    }


//    @Override
//    public User findUserByUsername(String username) {
//        return HibernateConf.getSessionFactory().getCurrentSession().get(User.class,username);
//    }
}
