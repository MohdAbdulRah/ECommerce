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

    public void delete(String productId) {
        Product prod = repository.findById(productId).orElseThrow(() -> new RuntimeException("Invalid Product Id"));
        repository.delete(prod);
    }

    public Product update(String productId, ProductRequest request, MultipartFile image) {
        if(request == null && (image == null || image.isEmpty())){
            throw new RuntimeException("Nothing Updation for Product Given");
        }

        Product prod = repository.findById(productId).orElseThrow(() -> new RuntimeException("Invalid Product Id"));
        if(request != null){
             prod.setName(request.getName());
             prod.setBrand(request.getBrand());
             prod.setAvalaible(request.isAvalaible());
             prod.setDescription(request.getDescription());
             prod.setPrice(request.getPrice());
             prod.setCategoryType(CategoryType.valueOf(request.getCategoryType().toUpperCase()));
             prod.setStockQuantity(request.getStockQuantity());
        }
        if(image != null){
            prod.setImage(service.saveImage(image));
        }
        repository.save(prod);

        return prod;

    }

    public List<Product> search(String keyword) {
        List<Product> prods = repository.searchKeyWord(keyword);
        return prods;
    }
}
