package ru.geekbrains.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.market.models.OrderItem;

@NoArgsConstructor
@Data
public class OrderItemDto {
    private String productTitle;
    private int quantity;
    private int pricePerProduct;
    private int price;
    private Long productId;

    public OrderItemDto(OrderItem orderItem) {
        this.productTitle = orderItem.getProduct().getTitle();
        this.quantity = orderItem.getQuantity();
        this.pricePerProduct = orderItem.getPricePerProduct();
        this.price = orderItem.getPrice();
        this.productId = orderItem.getProduct().getId();
    }
}
