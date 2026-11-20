package org.fitness.ecommerce.services;

import lombok.RequiredArgsConstructor;
import org.fitness.ecommerce.models.Order;
import org.fitness.ecommerce.repository.OrderItemRepository;
import org.fitness.ecommerce.repository.OrderRepository;
import org.fitness.ecommerce.requests.OrderRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;


    public Order saveOrder(OrderRequest request) {

    }
}
