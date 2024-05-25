package com.graduationproject.backend.backendwebsite.dto;

import java.util.Date;

public interface ICart {

  String getOrderId();

  String getCartId();

  String getTotalQuantity();

  String getTotalOrderPrice();

  String getOrderStatus();

  String getFirstName();

  String getLastName();

  Date getCreatedDate();

}
