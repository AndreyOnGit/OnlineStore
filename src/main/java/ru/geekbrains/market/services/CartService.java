package ru.geekbrains.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.market.beans.Cart;
import ru.geekbrains.market.beans.JwtTokenUtil;
import ru.geekbrains.market.dto.CartDto;
import ru.geekbrains.market.exceptions_handling.ResourceNotFoundException;
import ru.geekbrains.market.models.Order;
import ru.geekbrains.market.models.OrderItem;
import ru.geekbrains.market.models.Product;
import ru.geekbrains.market.models.User;
import ru.geekbrains.market.repositories.OrderItemRepository;
import ru.geekbrains.market.repositories.OrderRepository;
import ru.geekbrains.market.repositories.ProductRepository;
import ru.geekbrains.market.repositories.UserRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final Cart cart;
    private final ProductRepository productRepository;
    private final OrderItemRepository cartRepository;
    private final OrderRepository orderRepository;

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public void addProductIntoCartById(Long id) {
        for (OrderItem o : cart.getItems()) {
            if (o.getProduct().getId().equals(id)) {
                o.incrementQuantity();
                cart.recalculate();
                return;
            }
        }
        Product p = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No product's been found with id " + id + " to add to cart."));
        cart.addOrderItem(new OrderItem(p));
        cart.recalculate();
    }

    public CartDto getCart() {
        return new CartDto(cart);
    }

    public void clear() {
        cart.clear();
    }

    public void removeOrderItemByProductId(Long id) {
        cart.removeOrderItemByProductId(id);
    }

    public void checkout(User user){
        Order order = new Order();
        order.setTotalPrice(cart.getTotalPrice());
        order.setUserId(user.getId());
        orderRepository.save(order);
        for (OrderItem oi : cart.getItems()) {
            oi.setOrderId(order.getId());
            cartRepository.save(oi);
        }

    }
}
