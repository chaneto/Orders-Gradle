package com.example.OrdersGradle.web.assembler;

import com.example.OrdersGradle.model.entities.Order;
import com.example.OrdersGradle.web.resource.OrderCreateResource;
import com.example.OrdersGradle.web.resource.OrderResource;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderAssembler {

  public Order assembleOrder(OrderCreateResource orderCreateResource) {
    Order order = new Order();
    order.setProductId(orderCreateResource.getProductId());
    order.setQuantity(orderCreateResource.getQuantity());
    order.setProductName(orderCreateResource.getProductName());
    return order;
  }

  public OrderResource assebmleOrderResource(Order order){
    OrderResource orderResource = new OrderResource();
    orderResource.setId(order.getId());
    orderResource.setProductId(order.getProductId());
    orderResource.setProductName(order.getProductName());
    orderResource.setQuantity(order.getQuantity());
    orderResource.setCreatedDate(order.getCreatedDate());
    return orderResource;
  }
}
