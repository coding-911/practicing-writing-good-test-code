package sample.cafekiosk.spring.api.service.order.dto;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import sample.cafekiosk.spring.api.service.product.dto.ProductResponseDTO;
import sample.cafekiosk.spring.domain.order.Order;
import sample.cafekiosk.spring.domain.order.OrderStatusType;
import sample.cafekiosk.spring.domain.orderproduct.OrderProduct;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class OrderResponseDTO {

    private Long id;
    private OrderStatusType status;
    private int totalPrice;
    private LocalDateTime registeredDateTime;
    private List<ProductResponseDTO> products;

    @Builder
    public OrderResponseDTO(Long id, OrderStatusType status, int totalPrice, LocalDateTime registeredDateTime, List<ProductResponseDTO> products) {
        this.id = id;
        this.status = status;
        this.totalPrice = totalPrice;
        this.registeredDateTime = registeredDateTime;
        this.products = products;
    }

    public static OrderResponseDTO of(Order order) {
        return OrderResponseDTO.builder()
                .id(order.getId())
                .status(order.getStatus())
                .totalPrice(order.getTotalPrice())
                .registeredDateTime(order.getRegisteredDateTime())
                .products(order.getOrderProducts().stream()
                        .map(orderProduct -> ProductResponseDTO.of(orderProduct.getProduct()))
                        .collect(Collectors.toList())
                )
                .build();
    }
}
