package dev.greenn.ordersapi.services;

import dev.greenn.ordersapi.domain.Client;
import dev.greenn.ordersapi.domain.Product;
import dev.greenn.ordersapi.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

public Product addProduct(Product product){
        return productRepository.save(product);
}
}
