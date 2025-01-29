package sample.cafekiosk.spring.api.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import sample.cafekiosk.spring.api.service.order.dto.OrderRequestDTO;
import sample.cafekiosk.spring.domain.order.Order;
import sample.cafekiosk.spring.domain.order.OrderRepository;
import sample.cafekiosk.spring.domain.order.OrderStatusType;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public void createOrder(OrderRequestDTO orderRequestDTO) {

        Order.builder()
                .status(OrderStatusType.INIT)
                .totalPrice()
                .registeredDateTime()
                .registeredDateTime();
    }
}
