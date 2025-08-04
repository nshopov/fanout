package com.emergent.fanout.seed;

import com.emergent.fanout.entity.Order;
import com.emergent.fanout.repositories.shardGamma.OrderRepositoryShardGamma;
import jakarta.annotation.PostConstruct;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Component;

@Component
public class ShardGammaSeeder {
    private final OrderRepositoryShardGamma repository;

    public ShardGammaSeeder(OrderRepositoryShardGamma repo) {
        this.repository = repo;
    }

    @PostConstruct
    public void seed() {
        this.repository.save(new Order(8L, "DE", 1300.0));
        this.repository.save(new Order(9L, "IT", 300.0));
        this.repository.save(new Order(10L, "DE", 1900.0));
    }
}
