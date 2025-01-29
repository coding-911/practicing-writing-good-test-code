package sample.cafekiosk.spring.domain.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;
import static sample.cafekiosk.spring.domain.product.ProductStatusType.*;
import static sample.cafekiosk.spring.domain.product.ProductType.*;

@ActiveProfiles("test")
@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @DisplayName("원하는 판매 상태의 상품 목록을 조회한다.")
    @Test
    void findAllByStatusIn() {
        //given
        Product product1 = Product.builder()
                .productNumber("001")
                .type(HANDMADE)
                .status(SELLING)
                .name("아메리카노")
                .price(4000)
                .build();
        Product product2 = Product.builder()
                .productNumber("002")
                .type(HANDMADE)
                .status(STOP)
                .name("라떼")
                .price(4500)
                .build();
        Product product3 = Product.builder()
                .productNumber("003")
                .type(BOTTLE)
                .status(SELLING)
                .name("모구모구")
                .price(3000)
                .build();
        Product product4 = Product.builder()
                .productNumber("004")
                .type(BAKERY)
                .status(HOLD)
                .name("크로플")
                .price(5000)
                .build();
        productRepository.saveAll(List.of(product1, product2, product3, product4));

        //when
        List<Product> products = productRepository.findAllByStatusIn(List.of(SELLING, HOLD));

        //then
        assertThat(products).hasSize(3)
                .extracting("productNumber", "name", "status")
                .containsExactlyInAnyOrder(
                        tuple("001", "아메리카노", SELLING),
                        tuple("003", "모구모구", SELLING),
                        tuple("004", "크로플", HOLD)
                );


    }
}