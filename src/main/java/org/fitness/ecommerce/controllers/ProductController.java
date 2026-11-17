package org.fitness.ecommerce.controllers;


import lombok.RequiredArgsConstructor;
import org.fitness.ecommerce.models.Product;
import org.fitness.ecommerce.requests.ProductRequest;
import org.fitness.ecommerce.responses.GlobalResponse;
import org.fitness.ecommerce.services.ProductServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {
    private final ProductServices services;

    @PostMapping(value = "save",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<GlobalResponse> save(@RequestPart("product") ProductRequest request, @RequestPart("image") MultipartFile image){
        GlobalResponse response = new GlobalResponse();
        Product prod = services.save(request,image);
        response.setData(prod);
        response.setStatus(1);
        response.setMessage("Succesfully Saved Product");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("get")
    public ResponseEntity<GlobalResponse> get(){
        GlobalResponse response = new GlobalResponse();
        List<Product> prods = services.getProducts();
        response.setData(prods);
        response.setStatus(1);
        response.setMessage("Succesfully Fetched Products");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("getById/{productId}")
    public ResponseEntity<GlobalResponse> getById(@PathVariable String productId){
        GlobalResponse response = new GlobalResponse();
        Product prod = services.getProductById(productId);
        response.setData(prod);
        response.setStatus(1);
        response.setMessage("Succesfully Fetched Product");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("delete/{productId}")
    public ResponseEntity<GlobalResponse> delete(@PathVariable String productId){
        GlobalResponse response = new GlobalResponse();
        services.delete(productId);
        response.setData(null);
        response.setStatus(1);
        response.setMessage("Succesfully Deleted Product");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("update/{productId}")
    public ResponseEntity<GlobalResponse> update(@PathVariable String productId,@RequestPart(value = "product",required = false) ProductRequest request, @RequestPart(value = "image",required = false) MultipartFile image){
        GlobalResponse response = new GlobalResponse();
        Product prod = services.update(productId,request,image);
        response.setData(prod);
        response.setStatus(1);
        response.setMessage("Succesfully Updated Product");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("search")
    public ResponseEntity<GlobalResponse> search(@RequestParam String keyword){
        GlobalResponse response = new GlobalResponse();
        List<Product>prods = services.search(keyword);
        response.setData(prods);
        response.setStatus(1);
        response.setMessage("Succesfully Updated Product");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
