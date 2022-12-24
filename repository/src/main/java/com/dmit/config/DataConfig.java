package com.dmit.config;

import com.dmit.entity.car.Car;
import com.dmit.entity.car.CarBrand;
import com.dmit.entity.car.CarModel;
import com.dmit.entity.car.Image;
import com.dmit.entity.user.User;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.dmit.dao")
@PropertySource(value = {
        "classpath:/database.properties",
        "classpath:/hibernate.properties"
})
public class DataConfig {

    @Bean
    public Properties hibernateProperties(
            @Value("${hibernate.dialect}") String dialect,
            @Value("${hibernate.show_sql:false}") String showSql,
            @Value("${hibernate.format_sql:false}") String formatSql,
            @Value("${hibernate.hbm2ddl.auto:validate}") String hbm2ddl,
            @Value("${hibernate.enable_lazy_load_no_trans:false}") String lazyNoTrans) {
        Properties properties = new Properties();
        properties.put(Environment.DIALECT, dialect);
        properties.put(Environment.SHOW_SQL, showSql);
        properties.put(Environment.FORMAT_SQL, formatSql);
        properties.put(Environment.HBM2DDL_AUTO, hbm2ddl);
        properties.put(Environment.ENABLE_LAZY_LOAD_NO_TRANS, lazyNoTrans);
        return properties;
    }

    @Bean
    public DataSource dataSource(
            @Value("${database.url}") String url,
            @Value("${database.user}") String user,
            @Value("${database.password}") String password,
            @Value("${database.driver_class}") String driver,
            @Value("${connection.remove_abandoned:true}") boolean removeAbandoned,
            @Value("${connection.initial_size:50}") int initialSize,
            @Value("${connection.max_total:100}") int maxTotal) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setRemoveAbandonedOnBorrow(removeAbandoned);
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxTotal(maxTotal);
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource, Properties hibernateProperties) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setAnnotatedClasses(
                Car.class,
                CarBrand.class,
                CarModel.class,
                Image.class,
                User.class
        );
        sessionFactory.setHibernateProperties(hibernateProperties);
        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}
