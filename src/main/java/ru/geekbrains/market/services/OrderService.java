package ru.geekbrains.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.market.beans.Cart;
import ru.geekbrains.market.exceptions_handling.ResourceNotFoundException;
import ru.geekbrains.market.models.Order;
import ru.geekbrains.market.models.User;
import ru.geekbrains.market.repositories.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final Cart cart;

    public Order createFromUserCart(User user) {
        Order order = new Order(cart, user);
        order = orderRepository.save(order);
        cart.clear();
        return order;
    }

    public List<Order> findAllOrdersByOwnerName(String username) {
        return orderRepository.findAllByOwnerUsername(username);
    }

    public void changeAddress(String address, Long orderId) {
        if (!address.isBlank() || !address.isEmpty() || address != null) {
            Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("No order's been found with id " + orderId + " to add new delivery address."));
            order.setDeliveryAddress(address);
            orderRepository.save(order);
        }
    }
}
