package sample.cafekiosk.spring.domain.order;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.cafekiosk.spring.domain.Timestamp;
import sample.cafekiosk.spring.domain.orderproduct.OrderProduct;
import sample.cafekiosk.spring.domain.product.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
@Entity
public class Order extends Timestamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatusType status;

    private int totalPrice;

    private LocalDateTime registeredDateTime;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts = new ArrayList<>();
    /*
    * new ArrayList<>() 코드 작성 이유
    * 객체가 생성될 때 자동으로 빈 리스트(ArrayList<>)가 할당됨
    * null을 신경 쓰지 않고 바로 add() 가능
    * NPE 걱정 없이 바로 사용 가능!
    * */

    @Builder
    public Order(OrderStatusType status, int totalPrice, LocalDateTime registeredDateTime, List<OrderProduct> orderProducts) {
        this.status = status;
        this.totalPrice = totalPrice;
        this.registeredDateTime = registeredDateTime;
        this.orderProducts = orderProducts;
    }

    public Order(List<Product> products, LocalDateTime now) {
        this.status = OrderStatusType.INIT;
        this.totalPrice = calculateTotalPrice(products);
        this.registeredDateTime = now;
        this.orderProducts = products.stream()
                .map(product -> new OrderProduct(this, product))
                .collect(Collectors.toList());
    }

    public static Order create(List<Product> products, LocalDateTime now) {
        return new Order(products, now);
    }

    private static int calculateTotalPrice(List<Product> products){
        return products.stream().mapToInt(Product::getPrice).sum();
    }
}
