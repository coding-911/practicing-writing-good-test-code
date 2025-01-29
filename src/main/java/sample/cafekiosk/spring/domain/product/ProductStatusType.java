package sample.cafekiosk.spring.domain.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum ProductStatusType {

    SELLING("판매중"),
    HOLD("보류"),
    STOP("종료");

    private final String inKorean;

    public static List<ProductStatusType> forDisplay() {
        return List.of(SELLING, HOLD);
    }
}
