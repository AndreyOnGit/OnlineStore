package ru.geekbrains.market.beans;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.market.models.OrderItem;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Data
public class Cart {
    private List<OrderItem> items;
    private int totalPrice;

    @PostConstruct
    public void init() {
        this.items = new LinkedList<>();
    }

    public void addOrderItem(OrderItem orderItem) {
        items.add(orderItem);
    }

    public void removeOrderItemByProductId(Long id) {
        items.removeIf(o -> o.getProduct().getId() == id);
    }

    public void clear() {
        items.clear();
        recalculate();
    }

    public void recalculate() {
        totalPrice = 0;
        for (OrderItem o : items) {
            totalPrice += o.getPrice();
        }
    }

}
