package org.fitness.ecommerce.controllers;


import lombok.RequiredArgsConstructor;
import org.fitness.ecommerce.models.Product;
import org.fitness.ecommerce.requests.ProductRequest;
import org.fitness.ecommerce.responses.GlobalResponse;
import org.fitness.ecommerce.services.ProductServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServices services;

    @PostMapping("save")
    public ResponseEntity<GlobalResponse> save(@RequestBody ProductRequest request){
        GlobalResponse response = new GlobalResponse();
        Product prod = services.save(request);
        response.setData(prod);
        response.setStatus(1);
        response.setMessage("Succesfully Saved Product");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
