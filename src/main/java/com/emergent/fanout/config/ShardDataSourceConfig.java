package com.emergent.fanout.config;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ShardDataSourceConfig {

    private static final Logger log = LoggerFactory.getLogger(ShardDataSourceConfig.class);


    @Bean(name = "shardAlphaDataSource")
    @ConfigurationProperties("app.datasource.shard-alpha")
    public DataSource shardAlphaDataSource() {

        HikariDataSource ds = DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
        log.info("shardAlpha jdbcUrl={}, username={}", ds.getJdbcUrl(), ds.getUsername());
        return ds;
    }

    @Bean(name = "shardBetaDataSource")
    @ConfigurationProperties("app.datasource.shard-beta")
    public DataSource shardBetaDataSource() {
        HikariDataSource ds = DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();

        log.info("shardAlpha jdbcUrl={}, username={}", ds.getJdbcUrl(), ds.getUsername());
        return ds;
    }

    @Bean(name = "shardGammaDataSource")
    @ConfigurationProperties("app.datasource.shard-gamma")
    public DataSource shardGammaDataSource() {
        HikariDataSource ds = DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();

        log.info("shardAlpha jdbcUrl={}, username={}", ds.getJdbcUrl(), ds.getUsername());
        return ds;
    }
}
