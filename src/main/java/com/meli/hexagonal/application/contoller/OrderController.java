package com.meli.hexagonal.application.contoller;


import com.meli.hexagonal.application.request.AddProductRequest;
import com.meli.hexagonal.application.request.CreateOrderRequest;
import com.meli.hexagonal.application.response.CreateOrderResponse;
import com.meli.hexagonal.domain.ports.service.OrderServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderServicePort orderService;

    @Autowired
    public OrderController(OrderServicePort orderService) {
        this.orderService = orderService;
    }

    @PostMapping()
    CreateOrderResponse createOrder(@RequestBody CreateOrderRequest request) {
        Long id = orderService.createOrder(request.getProduct());
        return new CreateOrderResponse(id);
    }

    @PostMapping("/{id}/products")
    void addProduct(@PathVariable Long id, @RequestBody AddProductRequest request) {
        orderService.addProduct(id, request.getProduct());
    }

    @DeleteMapping("/{id}/products")
    void deleteProduct(@PathVariable Long id, @RequestBody Long productId) {
        orderService.deleteProduct(id, productId);
    }

    @PostMapping("/{id}/complete")
    void completeOrder(@PathVariable Long id){
        orderService.completeOrder(id);
    }
}
