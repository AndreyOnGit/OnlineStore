package ru.geekbrains.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.dto.CartDto;
import ru.geekbrains.market.models.User;
import ru.geekbrains.market.services.CartService;
import ru.geekbrains.market.services.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final UserService userService;

    @GetMapping
    public CartDto showCart() {
        return cartService.getCart();
    }

    @GetMapping("/add/{id}")
    public void putProductIntoCart(@PathVariable Long id) {
        cartService.addProductIntoCartById(id);
    }

    @DeleteMapping("/{id}")
    public void removeProductFromCart(@PathVariable Long id) {
        cartService.removeOrderItemByProductId(id);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cartService.clear();
    }

    @GetMapping("/checkout")
    public void checkout(Principal principal) {
        User user = userService.findByUsername(principal.getName()).get();
        cartService.checkout(user);}
}
