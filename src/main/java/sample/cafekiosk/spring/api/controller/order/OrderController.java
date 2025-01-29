package sample.cafekiosk.spring.api.controller.order;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.cafekiosk.spring.api.service.order.OrderService;
import sample.cafekiosk.spring.api.service.order.dto.OrderCreateRequestDTO;
import sample.cafekiosk.spring.api.service.order.dto.OrderResponseDTO;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
public class OrderController {
    private final OrderService orderService;

    @RequestMapping("/api/v1/order/create")
    public OrderResponseDTO createOrder(@RequestBody OrderCreateRequestDTO orderRequestDTO){
        return orderService.createOrder(orderRequestDTO, LocalDateTime.now());
    }
}
