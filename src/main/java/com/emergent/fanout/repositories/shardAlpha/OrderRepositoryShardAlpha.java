package com.emergent.fanout.repositories.shardAlpha;

import com.emergent.fanout.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepositoryShardAlpha extends JpaRepository<Order, Long> {
    List<Order> findByAmountGreaterThanAndCustomerCountry(double amount, String country);
}
