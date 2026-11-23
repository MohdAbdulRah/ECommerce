package org.fitness.ecommerce.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public String Id;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    public Product product;

    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)
    @JsonIgnore
    public Order order;

    public int quantity;

    public BigDecimal price;

    public LocalDateTime createdAt;

    public LocalDateTime updatedAt;


    @PrePersist
    public void onCreate(){
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate(){
        updatedAt = LocalDateTime.now();
    }
}
