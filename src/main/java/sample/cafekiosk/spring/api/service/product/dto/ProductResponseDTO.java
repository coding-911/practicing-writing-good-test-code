package sample.cafekiosk.spring.api.service.product.dto;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductStatusType;
import sample.cafekiosk.spring.domain.product.ProductType;

@Getter
public class ProductResponseDTO {

    private Long id;
    private String productNumber;
    private ProductType type;
    private ProductStatusType status;
    private String name;
    private int price;

    @Builder
    private ProductResponseDTO(Long id, String productNumber, ProductType type, ProductStatusType status, String name, int price) {
        this.id = id;
        this.productNumber = productNumber;
        this.type = type;
        this.status = status;
        this.name = name;
        this.price = price;
    }

    public static ProductResponseDTO of(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .productNumber(product.getProductNumber())
                .type(product.getType())
                .status(product.getStatus())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}
