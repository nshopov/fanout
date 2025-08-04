package com.emergent.fanout.controller;

import com.emergent.fanout.entity.Order;
import com.emergent.fanout.service.ShardedOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final ShardedOrderService service;

    public OrderController(ShardedOrderService service){
        this.service = service;
    }

    @GetMapping("/high-value-german")
    public List<Order> getHighValueGermanOrders(){
        return service.fetchHighValueGermanOrders();
    }
}
