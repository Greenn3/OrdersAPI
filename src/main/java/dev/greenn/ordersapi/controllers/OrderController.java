package dev.greenn.ordersapi.controllers;

import dev.greenn.ordersapi.DTOs.CreateOrderRequest;
import dev.greenn.ordersapi.DTOs.OrderResponse;
import dev.greenn.ordersapi.domain.Order;
import dev.greenn.ordersapi.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping
    ResponseEntity<Order> createOrder(@Valid @RequestBody CreateOrderRequest order){
        Order cretedOrder = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(cretedOrder);
    }

    @GetMapping("/{id}")
    ResponseEntity<OrderResponse> getOrderInfo(@PathVariable Long id){
        OrderResponse response = orderService.getOrderInfo(id);
        return ResponseEntity.ok(response);
    }
}
