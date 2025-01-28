package sample.cafekiosk.unit.beverage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AmericanoTest {

    @DisplayName("아메리카노 이름")
    @Test
    void getName() {
        //given
        Americano americano = new Americano();
        //when

        //then
        assertEquals("아메리카노", americano.getName());
        assertThat(americano.getName()).isEqualTo("아메리카노");
    }

    @DisplayName("아메리카노 가격")
    @Test
    void getPrice() {
        //given
        Americano americano = new Americano();
        //when

        //then
        assertThat(americano.getPrice()).isEqualTo(4000);
    }
}