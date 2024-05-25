package com.graduationproject.backend.backendwebsite.model;


import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductModel {

  private Long categoryId;

  private String categoryName;

  private Long productId;

  private String productName;

  private String productDescription;

  private String productPrice;

  private String productPriceSale;

  private Integer quantity;

  private Date expireDate;

  private Date createdDate;

  private String status;

  private List<SourceImageModel> sourceImageModelList;



}
