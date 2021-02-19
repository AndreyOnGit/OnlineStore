package ru.geekbrains.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.dto.OrderDto;
import ru.geekbrains.market.exceptions_handling.ResourceNotFoundException;
import ru.geekbrains.market.models.Delivery;
import ru.geekbrains.market.models.User;
import ru.geekbrains.market.services.OrderService;
import ru.geekbrains.market.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @GetMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrderFromCart(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        orderService.createFromUserCart(user);
    }

    @GetMapping
    public List<OrderDto> getCurrentUserOrders(Principal principal) {
        return orderService.findAllOrdersByOwnerName(principal.getName()).stream().map(OrderDto::new).collect(Collectors.toList());
    }

    @PostMapping("/change_address")
    public void attachAddress(@RequestBody Delivery delivery) {
        orderService.changeAddress(delivery.getAddress(), delivery.getOrderId());
    }

}
