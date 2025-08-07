package com.emergent.fanout.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractShardJpaConfig {

    protected LocalContainerEntityManagerFactoryBean buildEntityManagerFactory(
            String packagesToScan,
            DataSource dataSource,
            Environment environment
    ) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(packagesToScan);
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Map<String, Object> jpaProperties = new HashMap<>();
        String profile = environment.getProperty("spring.profiles.active", "h2");
        if ("postgres".equalsIgnoreCase(profile)) {
            jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            jpaProperties.put("hibernate.default_schema", "public");
        } else {
            jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        }

        jpaProperties.put("hibernate.hbm2ddl.auto", "update");
        em.setJpaPropertyMap(jpaProperties);
        return em;
    }

    protected JpaTransactionManager buildTransactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
