package org.fitness.ecommerce.controllers;

import lombok.RequiredArgsConstructor;
import org.fitness.ecommerce.models.Order;
import org.fitness.ecommerce.requests.OrderRequest;
import org.fitness.ecommerce.responses.GlobalResponse;
import org.fitness.ecommerce.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
