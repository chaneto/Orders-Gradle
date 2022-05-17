package com.example.OrdersGradle.web;

import com.example.OrdersGradle.web.resource.OrderCreateResource;
import com.example.OrdersGradle.web.resource.OrderResource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("orders")
public class OrderController {

  private final RabbitTemplate template;

  public OrderController(RabbitTemplate template) {
    this.template = template;
  }

  @PostMapping
  @ApiOperation(value = "Adding a order to the database", response = OrderResource.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
    @ApiResponse(code = 404, message = "Not found"),
    @ApiResponse(code = 500, message = "Internal Server Error")})
  public ResponseEntity<?> addOrders(@RequestBody OrderCreateResource orderCreateResource) {
    template.convertAndSend("message_exchange", "message_routingKEY", orderCreateResource);
    return new ResponseEntity<>(orderCreateResource, HttpStatus.CREATED);
  }
}
