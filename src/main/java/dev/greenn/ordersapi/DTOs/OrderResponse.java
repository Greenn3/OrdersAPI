package dev.greenn.ordersapi.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class OrderResponse {

    private Long clientId;
    private String clientName;
    private String clientSurname;
    private String clientPhone;

    private List<OrderItemInfo> itemsInfo = new ArrayList<>();

    private BigDecimal net;
    private BigDecimal gross;
}
