package dev.greenn.ordersapi.DTOs;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateOrderRequest {
    @NotNull
    private Long clientId;

    @NotEmpty(message = "Order must contain at least one item")
    private List< @Valid OrderItemRequest> orderItemRequestList;
}
