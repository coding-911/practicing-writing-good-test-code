package sample.cafekiosk.unit;

import org.hibernate.query.sqm.mutation.internal.cte.CteInsertStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.cafekiosk.unit.beverage.Americano;
import sample.cafekiosk.unit.beverage.Latte;
import sample.cafekiosk.unit.order.Order;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class CafeKioskRunnerTest {

    @DisplayName("아메리카노 추가")
    @Test
    void addAmericano() {
        //given
        CafeKiosk cafeKiosk = new CafeKiosk();

        //when
        cafeKiosk.add(new Americano());

        //then
        assertThat(cafeKiosk.getBeverages()).hasSize(1);
        assertThat(cafeKiosk.getBeverages().get(0).getName()).isEqualTo("아메리카노");
    }

    @DisplayName("아메리카노 여러 잔 추가")
    @Test
    void addAmericanos() {
        //given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        //when
        cafeKiosk.add(americano, 2);

        //then
        assertThat(cafeKiosk.getBeverages()).hasSize(2);
        assertThat(cafeKiosk.getBeverages().get(0)).isEqualTo(americano);
        assertThat(cafeKiosk.getBeverages().get(1)).isEqualTo(americano);
    }

    @DisplayName("아메리카노 0 잔 추가")
    @Test
    void addAmericanosCountZero() {
        //given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        //when
        cafeKiosk.add(americano, 0);

        //then
        assertThatThrownBy(() -> cafeKiosk.add(americano, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음료는 한 잔 이상 주문해야 합니다.");
    }

    @DisplayName("여러 음료 중 특정 아메리카노를 제거")
    @Test
    void remove() {
        //given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        cafeKiosk.add(americano);
        cafeKiosk.add(new Latte());
        cafeKiosk.add(new Latte());
        cafeKiosk.add(new Americano());
        cafeKiosk.getBeverages().stream().forEach(south -> System.out.println(south.getName()));

        //when
        cafeKiosk.remove(americano);
        cafeKiosk.getBeverages().stream().forEach(south -> System.out.println(south.getName()));

        //then
        assertThat(cafeKiosk.getBeverages()).hasSize(3);
    }

    @DisplayName("키오스크 초기화")
    @Test
    void clear() {
        //given
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Latte());
        cafeKiosk.add(new Latte());
        cafeKiosk.add(new Americano());

        //when
        cafeKiosk.clear();

        //then
        assertThat(cafeKiosk.getBeverages()).isEmpty();
    }

    @DisplayName("총 가격 계산")
    @Test
    void calcaulateTotalPrice() {
        //given
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Latte());
        cafeKiosk.add(new Latte());
        cafeKiosk.add(new Americano());

        //when
        int totalPrice = cafeKiosk.calcaulateTotalPrice();

        //then
        assertThat(totalPrice).isEqualTo(13000);
    }

    @DisplayName("주문 생성")
    @Test
    void createOrder() {
        //given
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Latte());
        cafeKiosk.add(new Latte());
        cafeKiosk.add(new Americano());

        //when
        Order order = cafeKiosk.createOrder(LocalDateTime.now());

        //then
        assertThat(order.getBeverages()).hasSize(3);
    }

    @DisplayName("운영 시간 전 주문 생성 시 예외 처리")
    @Test
    void createOrderBeforeOperatingTime() {
        //given
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Latte());
        cafeKiosk.add(new Latte());
        cafeKiosk.add(new Americano());

        //then
        assertThatThrownBy(() -> cafeKiosk.createOrder(LocalDateTime.of(2025, 1, 28, 9, 59)))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("오픈 시간 전입니다.");
    }

    @DisplayName("운영 시간 후 주문 생성 시 예외 처리")
    @Test
    void createOrderAfterOperatingTime() {
        //given
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Latte());
        cafeKiosk.add(new Latte());
        cafeKiosk.add(new Americano());

        //then
        assertThatThrownBy(() -> cafeKiosk.createOrder(LocalDateTime.of(2025, 1, 28, 22, 1)))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("마감 시간 후입니다.");
    }

}