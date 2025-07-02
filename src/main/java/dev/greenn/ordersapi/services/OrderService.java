package dev.greenn.ordersapi.services;

import dev.greenn.ordersapi.DTOs.CreateOrderRequest;
import dev.greenn.ordersapi.DTOs.OrderItemInfo;
import dev.greenn.ordersapi.DTOs.OrderItemRequest;
import dev.greenn.ordersapi.DTOs.OrderResponse;
import dev.greenn.ordersapi.domain.Client;
import dev.greenn.ordersapi.domain.Order;
import dev.greenn.ordersapi.domain.OrderItem;
import dev.greenn.ordersapi.domain.Product;
import dev.greenn.ordersapi.repositories.ClientRepository;
import dev.greenn.ordersapi.repositories.OrderRepository;
import dev.greenn.ordersapi.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ClientRepository clientRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
    }
@Transactional
    public Order createOrder(CreateOrderRequest request){

        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Order order = new Order();
        order.setClient(client);
    BigDecimal net = BigDecimal.ZERO;
    BigDecimal gross = BigDecimal.ZERO;
        for(OrderItemRequest itemRequest : request.getOrderItemRequestList()){
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);

            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));


            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());
            order.getOrderItems().add(orderItem);
        }
  for(OrderItem orderItem : order.getOrderItems()){
     BigDecimal netPrice = orderItem.getProduct().getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()));
     net = net.add(netPrice);
     BigDecimal vatValue = netPrice.multiply(orderItem.getProduct().getVat());
     BigDecimal grossPrice =  netPrice.add(vatValue);
     gross = gross.add(grossPrice);

  }
order.setValueGross(gross);
  order.setValueNet(net);



        return orderRepository.save(order);
    }

    public OrderResponse getOrderInfo(Long id){
        OrderResponse response = new OrderResponse();
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        Client client = order.getClient();
        response.setClientId(client.getId());
        response.setClientName(client.getName());
        response.setClientSurname(client.getSurname());
        response.setClientPhone(client.getPhone());
        response.setGross(order.getValueGross());
        response.setNet(order.getValueNet());

        for(OrderItem item : order.getOrderItems()){
            OrderItemInfo orderItemInfo = new OrderItemInfo();
            orderItemInfo.setQuantity(item.getQuantity());
            orderItemInfo.setProductId(item.getProduct().getId());
            orderItemInfo.setProductName(item.getProduct().getName());
            orderItemInfo.setPrice(item.getProduct().getPrice());
            orderItemInfo.setVat(item.getProduct().getVat());
            response.getItemsInfo().add(orderItemInfo);
        }
        return response;


    }
}
