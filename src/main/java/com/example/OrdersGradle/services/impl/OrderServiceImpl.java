package com.example.OrdersGradle.services.impl;

import java.time.LocalDate;
import com.example.OrdersGradle.model.entities.Order;
import com.example.OrdersGradle.repositories.OrderRepository;
import com.example.OrdersGradle.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

  private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

  @Autowired
  private final OrderRepository orderRepository;

  public OrderServiceImpl(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  @RabbitListener(queues = "message_queue")
  public void seedOrder(Order order) {
    order.setCreatedDate(LocalDate.now());
    order.setLastModifiedDate(LocalDate.now());
    try {
      this.orderRepository.save(order);
      logger.info("Recieved Message From RabbitMQ: " + order);
    } catch (Exception e) {
      throw new DataIntegrityViolationException(e.getMessage());
    }
  }
}
