package sample.cafekiosk.spring.api.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.cafekiosk.spring.api.service.order.dto.OrderCreateRequestDTO;
import sample.cafekiosk.spring.api.service.order.dto.OrderResponseDTO;
import sample.cafekiosk.spring.domain.order.Order;
import sample.cafekiosk.spring.domain.order.OrderRepository;
import sample.cafekiosk.spring.domain.order.OrderStatusType;
import sample.cafekiosk.spring.domain.orderproduct.OrderProduct;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderResponseDTO createOrder(OrderCreateRequestDTO requestDTO, LocalDateTime now) {
        List<Product> products = productRepository.findAllByProductNumberIn(requestDTO.getProductNumbers());
        Order order = Order.create(products, now);
        Order savedOrder = orderRepository.save(order);
        return OrderResponseDTO.of(savedOrder);

    }
}
