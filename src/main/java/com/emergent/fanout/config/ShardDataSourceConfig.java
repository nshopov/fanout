package com.emergent.fanout.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ShardDataSourceConfig {

    @Bean(name = "shardAlphaDataSource")
    @ConfigurationProperties("app.datasource.shard-alpha")
    public DataSource shardAlphaDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "shardBetaDataSource")
    @ConfigurationProperties("app.datasource.shard-beta")
    public DataSource shardBetaDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "shardGammaDataSource")
    @ConfigurationProperties("app.datasource.shard-gamma")
    public DataSource shardGammaDataSource() {
        return DataSourceBuilder.create().build();
    }
}
