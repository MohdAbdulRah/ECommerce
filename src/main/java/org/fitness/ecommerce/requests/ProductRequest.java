package org.fitness.ecommerce.requests;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fitness.ecommerce.models.CategoryType;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String name;
    private String brand;
    private String description;
    private BigDecimal price;
    private String categoryType;
    private int stockQuantity;
    private String image;
    private boolean avalaible;
}
