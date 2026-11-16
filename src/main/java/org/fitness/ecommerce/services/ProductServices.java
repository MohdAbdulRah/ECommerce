package org.fitness.ecommerce.services;

import lombok.RequiredArgsConstructor;
import org.fitness.ecommerce.models.CategoryType;
import org.fitness.ecommerce.models.Product;
import org.fitness.ecommerce.repository.ProductRepository;
import org.fitness.ecommerce.requests.ProductRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ProductServices {
    private final ProductRepository repository;
    private final FileStorageService service;

    public Product save(ProductRequest request, MultipartFile image){
        String imagePath = service.saveImage(image);
        Product prod = Product.builder()
                .name(request.getName())
                .brand(request.getBrand())
                .description(request.getDescription())
                .price(request.getPrice())
                .stockQuantity(request.getStockQuantity())
                .image(imagePath)
                .avalaible(request.isAvalaible())
                .categoryType(CategoryType.valueOf(request.getCategoryType().toUpperCase(Locale.ROOT)))
                       .build();
        repository.save(prod);

        return prod;
    }

    public List<Product> getProducts() {
        List<Product> products = repository.findAll();
        return products;
    }

    public Product getProductById(String productId) {
        Product prod = repository.findById(productId).orElseThrow(() -> new RuntimeException("Invalid Product Id"));
        return prod;

    }
}
