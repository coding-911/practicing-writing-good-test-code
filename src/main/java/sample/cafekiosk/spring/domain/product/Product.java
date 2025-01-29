package sample.cafekiosk.spring.domain.product;

import jakarta.persistence.*;
import lombok.*;
import sample.cafekiosk.spring.domain.Timestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Product extends Timestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productNumber;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    @Enumerated(EnumType.STRING)
    private ProductStatusType status;

    private String name;
    private int price;

    @Builder
    private Product(String productNumber, ProductType type, ProductStatusType status, String name, int price) {
        this.productNumber = productNumber;
        this.type = type;
        this.status = status;
        this.name = name;
        this.price = price;
    }
}
