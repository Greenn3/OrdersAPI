package dev.greenn.ordersapi.repositories;

import dev.greenn.ordersapi.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
