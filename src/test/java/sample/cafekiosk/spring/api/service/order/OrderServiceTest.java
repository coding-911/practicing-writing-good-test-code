package sample.cafekiosk.spring.api.service.order;

import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import sample.cafekiosk.spring.api.service.order.dto.OrderCreateRequestDTO;

import sample.cafekiosk.spring.api.service.order.dto.OrderResponseDTO;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductType;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;
import static sample.cafekiosk.spring.domain.product.ProductStatusType.*;
import static sample.cafekiosk.spring.domain.product.ProductType.*;

@ActiveProfiles("test")
@SpringBootTest
class OrderServiceTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderService orderService;

    @DisplayName("주문 번호 리스트를 받아 주문을 생성한다.")
    @Test
    void createOrder() {
        //given
        Product product1 = createProduct(HANDMADE, "001", 4000);
        Product product2 = createProduct(BAKERY, "002", 5000);
        Product product3 = createProduct(BOTTLE, "003", 3000);
        productRepository.saveAll(List.of(product1, product2, product3));
        OrderCreateRequestDTO requestDTO = OrderCreateRequestDTO.builder()
                .productNumbers(List.of("001", "002"))
                .build();

        //when
        OrderResponseDTO responseDTO = orderService.createOrder(requestDTO, LocalDateTime.now());

        //then
        assertThat(responseDTO.getId()).isNotNull();
        assertThat(responseDTO.getTotalPrice()).isEqualTo(9000);
        assertThat(responseDTO.getProducts()).hasSize(2)
                .extracting("productNumber", "price")
                .containsExactlyInAnyOrder(
                        tuple("001", 4000),
                        tuple("002", 5000)
                );


    }

    private Product createProduct(ProductType type, String productNumber, int price) {
        Product product = Product.builder()
                .type(type)
                .productNumber(productNumber)
                .price(price)
                .status(SELLING)
                .name("테스트")
                .build();
        return product;
    }
}

