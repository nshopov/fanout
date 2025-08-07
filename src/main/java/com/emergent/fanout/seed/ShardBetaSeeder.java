package com.emergent.fanout.seed;

import com.emergent.fanout.entity.Order;
import com.emergent.fanout.repositories.shardBeta.OrderRepositoryShardBeta;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class ShardBetaSeeder {
    private final OrderRepositoryShardBeta repository;

    public ShardBetaSeeder(OrderRepositoryShardBeta repo){
        this.repository = repo;
    }

    @PostConstruct
    public void seed(){
        System.out.println("🔄 Seeding shard_beta...");
        repository.deleteAll();

        this.repository.save(new Order(5L, "DE", 1800.0));
        this.repository.save(new Order(6L, "UK", 230.0));
        this.repository.save(new Order(7L, "DE", 2800.0));
        System.out.println("Seeding shard_beta done.");
    }
}
