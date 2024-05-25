package com.graduationproject.backend.backendwebsite.forms;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderForm {

  private List<OrderFormSupport> orderList;

  private int pageNo;

  private int pageSize;

  private long totalElements;

  private int totalPages;

  private boolean last;
}
