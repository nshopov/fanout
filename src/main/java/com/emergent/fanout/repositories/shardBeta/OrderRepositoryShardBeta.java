package com.emergent.fanout.repositories.shardBeta;

import com.emergent.fanout.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepositoryShardBeta extends JpaRepository<Order, Long> {
    List<Order> findByAmountGreaterThanAndCustomerCountry(Double amount, String country);
}
