package dev.greenn.ordersapi.DTOs;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class OrderItemRequest {

    private Long productId;


    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;
}
