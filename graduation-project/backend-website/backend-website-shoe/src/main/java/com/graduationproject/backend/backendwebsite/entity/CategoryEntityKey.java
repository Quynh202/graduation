package com.graduationproject.backend.backendwebsite.entity;

import java.io.Serializable;
import javax.persistence.Column;
import lombok.Data;


@Data
public class CategoryEntityKey implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "category_id", nullable = false)
  private Long categoryId;



  /**
   * Set PK.
   *
   * @param categoryId  categoryId
   * @return key.
   */
  public CategoryEntityKey setPk(Long categoryId) {
    setCategoryId(categoryId);
    return this;
  }
}
