package com.emergent.fanout.seed;

import com.emergent.fanout.entity.Order;
import com.emergent.fanout.repositories.shardAlpha.OrderRepositoryShardAlpha;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class ShardAlphaDataSeeder {

    private final OrderRepositoryShardAlpha repository;

    public  ShardAlphaDataSeeder(OrderRepositoryShardAlpha repo) {
        this.repository = repo;
    }

    @PostConstruct
    public void seed(){
        repository.save(new Order(1L, "DE", 1500.0));
        repository.save(new Order(2L, "FR", 900.0));
        repository.save(new Order(3L, "GB", 665.0));
        repository.save(new Order(4L, "DE", 2500.0));
    }
}
