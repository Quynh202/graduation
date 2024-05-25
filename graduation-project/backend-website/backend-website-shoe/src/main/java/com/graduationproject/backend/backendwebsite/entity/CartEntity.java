package com.graduationproject.backend.backendwebsite.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@IdClass(CartEntityKey.class)
@Table(name = "tbl_cart")
public class CartEntity extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  public CartEntity() {

    super();
  }

  @Id
  @Column(name = "cart_id", nullable = false)
  private Long cartId;

  @Id
  @Column(name = "product_id", nullable = false)
  private Long productId;

  @Column(name = "product_name", nullable = false)
  private String productName;



  @Column(name = "product_quantity", length = 15, nullable = false)
  private Integer productQuantity;

  @Column(name = "product_current_price", length = 15, nullable = false)
  private BigDecimal productCurrentPrice;
}