package ru.geekbrains.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.geekbrains.market.models.Order;
import ru.geekbrains.market.models.Product;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Product> {
    List<Order> findAllByOwnerUsername(String ownerUsername);
}
