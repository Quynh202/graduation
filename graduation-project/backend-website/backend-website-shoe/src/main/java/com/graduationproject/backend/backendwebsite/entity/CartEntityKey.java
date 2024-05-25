package com.graduationproject.backend.backendwebsite.entity;

import java.io.Serializable;
import javax.persistence.Column;
import lombok.Data;


@Data
public class CartEntityKey implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "cart_id", nullable = false)
  private Long cartId;

  @Column(name = "product_id", nullable = false)
  private Long productId;



  /**
   * Set PK.
   *
   * @param cartId    cartId
   * @param productId productId
   * @return key.
   */
  public CartEntityKey setPk(Long cartId, Long productId, String productSizeName) {
    setCartId(cartId);
    setProductId(productId);

    return this;
  }
}
