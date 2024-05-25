package com.graduationproject.backend.backendwebsite.dto;

import java.math.BigDecimal;

public interface EachMonthOrderDto {

  String getMonth();

  BigDecimal getTotalPrice();
}
