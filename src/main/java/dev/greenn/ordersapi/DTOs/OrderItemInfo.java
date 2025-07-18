package dev.greenn.ordersapi.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemInfo {
    private Long productId;
    private String productName;
    private int quantity;
    private BigDecimal price;
    private BigDecimal vat;
}
