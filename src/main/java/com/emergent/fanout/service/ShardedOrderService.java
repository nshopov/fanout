package com.emergent.fanout.service;

import com.emergent.fanout.dto.OrderRequest;
import com.emergent.fanout.entity.Order;
import com.emergent.fanout.repositories.shardAlpha.OrderRepositoryShardAlpha;
import com.emergent.fanout.repositories.shardBeta.OrderRepositoryShardBeta;
import com.emergent.fanout.repositories.shardGamma.OrderRepositoryShardGamma;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
public class ShardedOrderService {
    private final OrderRepositoryShardAlpha repoAlpha;
    private final OrderRepositoryShardBeta repoBeta;
    private final OrderRepositoryShardGamma repoGamma;

    public ShardedOrderService(OrderRepositoryShardAlpha repoAlpha,
                               OrderRepositoryShardBeta repoBeta,
                               OrderRepositoryShardGamma repoGamma) {
        this.repoAlpha = repoAlpha;
        this.repoBeta = repoBeta;
        this.repoGamma = repoGamma;
    }

    public void createOrder(OrderRequest orderRequest) {
        Order order = new Order(orderRequest.getId(),
                                orderRequest.getCustomerCountry(),
                                orderRequest.getAmount());

        int shardIdex = Math.toIntExact(orderRequest.getId() % 3);

        switch (shardIdex) {
            case 0 -> repoAlpha.save(order);
            case 1 -> repoBeta.save(order);
            case 2 -> repoGamma.save(order);
        }
    }

    public List<Order> fetchHighValueGermanOrders() {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        try{
            List<Future<List<Order>>> results = executor.invokeAll(List.of (
                    () -> repoAlpha.findByAmountGreaterThanAndCustomerCountry(1000.0, "DE"),
                    () -> repoBeta.findByAmountGreaterThanAndCustomerCountry(1000.0, "DE"),
                    () -> repoGamma.findByAmountGreaterThanAndCustomerCountry(1000.0, "DE")
            ));

            return results.stream()
                    .flatMap(f -> {
                        try {
                            return f.get().stream();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }).collect(Collectors.toList());

        } catch (InterruptedException ie) {
            throw new RuntimeException("Faild to query shards", ie);
        } finally {
            executor.shutdown();
        }

    }
}
