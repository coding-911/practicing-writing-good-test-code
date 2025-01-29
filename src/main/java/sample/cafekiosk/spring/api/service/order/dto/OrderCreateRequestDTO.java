package sample.cafekiosk.spring.api.service.order.dto;

import lombok.Builder;
import lombok.Getter;
import sample.cafekiosk.spring.domain.order.OrderStatusType;
import sample.cafekiosk.spring.domain.orderproduct.OrderProduct;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderCreateRequestDTO {

    private List<String> productNumbers;

    @Builder
    public OrderCreateRequestDTO(List<String> productNumbers) {
        this.productNumbers = productNumbers;
    }
}
