package com.emergent.fanout.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.emergent.fanout.repositories.shardAlpha",
        entityManagerFactoryRef = "shardAlphaEntityManager",
        transactionManagerRef = "shardAlphaTransactionManager"
)
public class ShardAlphaConfig extends AbstractShardJpaConfig {

    @Bean(name = "shardAlphaEntityManager") // <-- corrected bean name
    public LocalContainerEntityManagerFactoryBean shardAlphaEntityManager(
            @Qualifier("shardAlphaDataSource") DataSource dataSource,
            Environment environment
    ) {
        return buildEntityManagerFactory("com.emergent.fanout.entity", dataSource, environment);
    }


    @Bean
    public JpaTransactionManager shardAlphaTransactionManager(
            @Qualifier("shardAlphaEntityManager") EntityManagerFactory emf) {
        return buildTransactionManager(emf);
    }

}