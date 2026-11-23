package org.fitness.ecommerce.controllers;

import lombok.RequiredArgsConstructor;
import org.fitness.ecommerce.models.Order;
import org.fitness.ecommerce.requests.OrderRequest;
import org.fitness.ecommerce.responses.GlobalResponse;
import org.fitness.ecommerce.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("save")
    public ResponseEntity<GlobalResponse> save(@RequestBody OrderRequest request){
        GlobalResponse response = new GlobalResponse();
        Order order = orderService.saveOrder(request);
        response.setData(order);
        response.setMessage("Successfully Saved");
        response.setStatus(1);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("getByEmail")
    public ResponseEntity<GlobalResponse> getByEmail(@RequestParam String email){
        GlobalResponse response = new GlobalResponse();
        List<Order> order = orderService.getOrderByEmail(email);
        response.setData(order);
        response.setMessage("Successfully Fetched Orders");
        response.setStatus(1);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
