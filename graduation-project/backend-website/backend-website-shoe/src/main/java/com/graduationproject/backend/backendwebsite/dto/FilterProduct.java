package com.graduationproject.backend.backendwebsite.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class FilterProduct {

  private int pageSize;

  private int pageNo;

  private String sortDirection;

  private String sortBy;

  private String searchValue;

  private Long categoryId;

  private BigDecimal priceMin;

  private BigDecimal priceMax;


}
