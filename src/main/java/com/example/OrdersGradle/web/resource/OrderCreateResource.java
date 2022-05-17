package com.example.OrdersGradle.web.resource;

import java.math.BigDecimal;
import javax.validation.constraints.*;
import com.google.gson.annotations.Expose;

public class OrderCreateResource {

  @Expose
  @NotNull(message = "Product ID cannot be null!!!")
  @Min(1)
  private Long productId;

  @Expose
  @NotBlank(message = "Name cannot be empty string or null!!!")
  @Size(min = 1, message = "Name length must be more than 1 character!!!")
  private String productName;

  @Expose
  @NotNull(message = "Quantity cannot be null!!!")
  @DecimalMin(value = "0", message = "Тhe quantity cannot be a negative value!!!")
  private BigDecimal quantity;

  public OrderCreateResource() {
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public BigDecimal getQuantity() {
    return quantity;
  }

  public void setQuantity(BigDecimal quantity) {
    this.quantity = quantity;
  }
}
