package by.epamlab.config;


import by.epamlab.model.beans.Authority;
import by.epamlab.model.beans.Product;
import by.epamlab.model.beans.User;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"by.epamlab"})
public class HibernateConf {

    public static final int BATCH_SIZE = 30;

    @Autowired
    private ApplicationContext context;

    @Bean
    public LocalSessionFactoryBean getSessionFactory(){
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

        Properties props = new Properties();
        props.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        props.put(Environment.URL, "jdbc:mysql://localhost:3306/online_shop?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false");
        props.put(Environment.USER, "root");
        props.put(Environment.PASS, "");
        props.put(Environment.USE_NEW_ID_GENERATOR_MAPPINGS, false);
        props.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        props.put(Environment.SHOW_SQL, true);
        props.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        props.put(Environment.STATEMENT_BATCH_SIZE, BATCH_SIZE);
        //props.put(Environment.HBM2DDL_AUTO, "create-drop");

        factoryBean.setHibernateProperties(props);
        factoryBean.setAnnotatedClasses(User.class, Product.class, Authority.class);

        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager(){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }


}
