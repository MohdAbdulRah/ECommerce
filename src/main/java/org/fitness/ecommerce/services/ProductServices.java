package org.fitness.ecommerce.services;

import lombok.RequiredArgsConstructor;
import org.fitness.ecommerce.models.CategoryType;
import org.fitness.ecommerce.models.Product;
import org.fitness.ecommerce.repository.ProductRepository;
import org.fitness.ecommerce.requests.ProductRequest;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ProductServices {
    private final ProductRepository repository;

    public Product save(ProductRequest request){
        Product prod = Product.builder()
                .name(request.getName())
                .brand(request.getBrand())
                .description(request.getDescription())
                .price(request.getPrice())
                .stockQuantity(request.getStockQuantity())
                .image(request.getImage())
                .avalaible(request.isAvalaible())
                .categoryType(CategoryType.valueOf(request.getCategoryType().toUpperCase(Locale.ROOT)))
                       .build();
        repository.save(prod);

        return prod;
    }
}
