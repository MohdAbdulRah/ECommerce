package org.fitness.ecommerce.services;

import lombok.RequiredArgsConstructor;
import org.fitness.ecommerce.models.Order;
import org.fitness.ecommerce.models.OrderItem;
import org.fitness.ecommerce.models.Product;
import org.fitness.ecommerce.repository.OrderItemRepository;
import org.fitness.ecommerce.repository.OrderRepository;
import org.fitness.ecommerce.repository.ProductRepository;
import org.fitness.ecommerce.requests.OrderItemRequest;
import org.fitness.ecommerce.requests.OrderRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;


    public Order saveOrder(OrderRequest request) {
        Order order = Order.builder()
                .name(request.getName())
                .email(request.getEmail())
                .build();


        for(OrderItemRequest oq : request.itemsList){
            Product pro = productRepository.findById(oq.getProductId()).orElseThrow(() -> new RuntimeException("Product Not Found"));
            if(pro.getStockQuantity() < oq.getQuantity()) throw new RuntimeException("Less Number of Avalaible Stocks");
            OrderItem item = OrderItem.builder()
                    .quantity(oq.getQuantity())
                    .price(pro.getPrice().multiply(BigDecimal.valueOf(oq.getQuantity())))
                    .product(pro)
                    .order(order)
                    .build();
            order.itemsList.add(item);
            pro.setStockQuantity(pro.getStockQuantity() - oq.getQuantity());
            productRepository.save(pro);

        }

        orderRepository.save(order);

        return order;
    }

    public List<Order> getOrderByEmail(String email) {
        List<Order> orders = orderRepository.findAllByEmail(email);
        return orders;
    }
}
