package sample.cafekiosk.unit;

import lombok.Getter;
import sample.cafekiosk.unit.beverage.Beverage;
import sample.cafekiosk.unit.order.Order;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CafeKiosk {

    private static final LocalTime SHOP_OPEN_TIME = LocalTime.of(10, 0);
    private static final LocalTime SHOP_CLOSE_TIME = LocalTime.of(22, 0);

    private final List<Beverage> beverages = new ArrayList<>();

    public void add(Beverage beverage) {
        beverages.add(beverage);
    }

    public void add(Beverage beverage, int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("음료는 한 잔 이상 주문해야 합니다.");
        }
        if (beverage == null) {
            throw new IllegalArgumentException("음료를 선택하지 않았습니다.");
        }
        for (int i = 0; i < count; i++) {
            beverages.add(beverage);
        }
    }

    public void remove(Beverage beverage) {
        beverages.remove(beverage);
    }

    public void clear() {
        beverages.clear();
    }

    public int calcaulateTotalPrice() {
        return beverages.stream().mapToInt(Beverage::getPrice).sum();
    }

    public Order createOrder(LocalDateTime nowDateTime) {
        LocalTime nowTime = nowDateTime.toLocalTime();
        if (nowTime.isBefore(SHOP_OPEN_TIME)) {
            throw new IllegalArgumentException("오픈 시간 전입니다.");
        }else if (nowTime.isAfter(SHOP_CLOSE_TIME)) {
            throw new IllegalArgumentException("마감 시간 후입니다.");
        }
        return new Order(nowDateTime, beverages);
    }
}
