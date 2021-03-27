package ru.geekbrains.market.tests.unit;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.geekbrains.market.beans.Cart;
import ru.geekbrains.market.models.OrderItem;
import ru.geekbrains.market.models.Product;

@SpringBootTest(classes = Cart.class)
public class CartTest {
    @Autowired
    private Cart cart;

    public OrderItem generateOderItem(){
        Product demoProduct = new Product();
        demoProduct.setTitle("SmthUseful");
        demoProduct.setPrice(50);
        demoProduct.setId(1L);
        OrderItem orderItem = new OrderItem(demoProduct);
        orderItem.incrementQuantity();
        return orderItem;
    }

    @Test
    public void testAddOrderItem(){
        cart.addOrderItem(generateOderItem());
        cart.recalculate();
        Assertions.assertEquals(100, cart.getTotalPrice());
    }
}
