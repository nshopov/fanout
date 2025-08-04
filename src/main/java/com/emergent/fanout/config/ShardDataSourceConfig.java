package com.emergent.fanout.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShardDataSourceConfig {

    @Bean(name = "shardAlphaDataSource")
    @ConfigurationProperties("app.datasource.shard-alpha")
    public DataSource shardAlphaDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "shardBetaDataSource")
    @ConfigurationProperties("app.datasource.shard-beta")
    public DataSource shardBeataDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "shardGammaDataSource")
    @ConfigurationProperties("app.datasource.shard-gamma")
    public DataSource shardGammaDataSource() {
        return DataSourceBuilder.create().build();
    }

    // configure ShardALpha
    @Configuration
    @EnableJpaRepositories(
            basePackages = "com.emergent.fanout.repositories.shardAlpha",
            entityManagerFactoryRef = "shardAlphaEntytyManager",
            transactionManagerRef = "shardAlphaTransactionManager"
    )
    static class ShardAlphaConfig {
        @Bean
        public LocalContainerEntityManagerFactoryBean shardAlphaEntytyManager(@Qualifier("shardAlphaDataSource") DataSource dataSource) {
            LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
            em.setDataSource(dataSource);
            em.setPackagesToScan("com.emergent.fanout.entity");
            em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

            Map<String, Object> jpaProperties = new HashMap<>();
            jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
            jpaProperties.put("hibernate.hbm2ddl.auto", "update");
            em.setJpaPropertyMap(jpaProperties);
            return em;
        }

        @Bean
        public PlatformTransactionManager shardAlphaTransactionManager(@Qualifier("shardAlphaEntytyManager")EntityManagerFactory emf){
            return new JpaTransactionManager(emf);
        }
    }

    // configure ShardBeta
    @Configuration
    @EnableJpaRepositories(
            basePackages = "com.emergent.fanout.repositories.shardBeta",
            entityManagerFactoryRef = "shardBetaEntityManager",
            transactionManagerRef = "shardBetaTransactionManager"
    )
    static class ShardBetaConfig {
        @Bean
        public LocalContainerEntityManagerFactoryBean shardBetaEntityManager(@Qualifier("shardBetaDataSource") DataSource dataSource) {
            LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
            em.setDataSource(dataSource);
            em.setPackagesToScan("com.emergent.fanout.entity");
            em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

            Map<String, Object> jpaProperties = new HashMap<>();
            jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
            jpaProperties.put("hibernate.hbm2ddl.auto", "update");
            em.setJpaPropertyMap(jpaProperties);

            return em;
        }

        @Bean
        public PlatformTransactionManager shardBetaTransactionManager(@Qualifier("shardBetaEntityManager") EntityManagerFactory emf) {
            return new JpaTransactionManager(emf);
        }
    }

    // configure ShardGamma
    @Configuration
    @EnableJpaRepositories(
            basePackages = "com.emergent.fanout.repositories.shardGamma",
            entityManagerFactoryRef = "shardGammaEntityManager",
            transactionManagerRef = "shardGammaTransactionManager"
    )
    static class ShardGammaConfig{
        @Bean
        public LocalContainerEntityManagerFactoryBean shardGammaEntityManager(@Qualifier("shardGammaDataSource") DataSource dataSource){
            LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
            em.setDataSource(dataSource);
            em.setPackagesToScan("com.emergent.fanout.entity");
            em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

            Map<String, Object> jpaProperties = new HashMap<>();
            jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
            jpaProperties.put("hibernate.hbm2ddl.auto", "update");
            em.setJpaPropertyMap(jpaProperties);

            return em;
        }

        @Bean
        public PlatformTransactionManager shardGammaTransactionManager(@Qualifier("shardGammaEntityManager") EntityManagerFactory emf) {
            return new JpaTransactionManager(emf);
        }
    }
}
