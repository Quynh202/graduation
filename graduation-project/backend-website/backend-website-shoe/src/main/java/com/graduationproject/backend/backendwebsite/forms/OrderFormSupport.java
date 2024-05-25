package com.graduationproject.backend.backendwebsite.forms;

import java.util.Date;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class OrderFormSupport {

  private String fullName;

  private Long orderId;

  private String customerAddress;

  private String orderCode;

  private Date createdDate;

  private String orderStatus;

  private String quantity;

  private String totalPrice;

  private String customerNote;

  private Long userId;
}
