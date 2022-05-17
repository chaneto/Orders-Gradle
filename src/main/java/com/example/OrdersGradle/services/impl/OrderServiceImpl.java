package com.example.OrdersGradle.services.impl;

import java.time.LocalDate;
import com.example.OrdersGradle.model.entities.Order;
import com.example.OrdersGradle.repositories.OrderRepository;
import com.example.OrdersGradle.services.OrderService;
import com.example.OrdersGradle.web.assembler.OrderAssembler;
import com.example.OrdersGradle.web.resource.OrderCreateResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

  private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

  private final OrderRepository orderRepository;
  private final OrderAssembler orderAssembler;

  public OrderServiceImpl(OrderRepository orderRepository, OrderAssembler orderAssembler) {
    this.orderRepository = orderRepository;
    this.orderAssembler = orderAssembler;
  }

  @Override
  @RabbitListener(queues = "message_queue")
  public void seedOrder(OrderCreateResource orderCreateResource) {
    Order order = this.orderAssembler.assembleOrder(orderCreateResource);
    order.setCreatedDate(LocalDate.now());
    order.setLastModifiedDate(LocalDate.now());
    try {
      this.orderRepository.save(order);
      logger.info("Recieved Message From RabbitMQ: " + orderCreateResource);
    } catch (Exception e) {
      throw new DataIntegrityViolationException(e.getMessage());
    }
  }
}
