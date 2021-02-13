package ru.geekbrains.market.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Delivery {
    private Long orderId;
    private String address;

    @Override
    public String toString() {
        return "Delivery{" +
                "orderId=" + orderId +
                ", address='" + address + '\'' +
                '}';
    }
}
