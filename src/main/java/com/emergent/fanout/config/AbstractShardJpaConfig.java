package com.emergent.fanout.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractShardJpaConfig {

    protected LocalContainerEntityManagerFactoryBean buildEntityManagerFactory(
            String basePackage,
            DataSource dataSource,
            Environment environment
    ) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(basePackage);
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.hbm2ddl.auto", environment.getProperty("spring.jpa.hibernate.ddl-auto", "update"));
        props.put("hibernate.dialect", environment.getProperty("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect"));
        em.setJpaPropertyMap(props);
        return em;
    }

    protected JpaTransactionManager buildTransactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
