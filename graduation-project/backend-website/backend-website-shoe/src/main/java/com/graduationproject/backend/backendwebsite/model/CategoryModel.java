package com.graduationproject.backend.backendwebsite.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryModel {

  private Long categoryId;


  private String categoryDescription;

  private String categoryName;

  private String categorySeo;

  private String createdDate;

  private String updatedDate;

  private String categoryStatus;
}
