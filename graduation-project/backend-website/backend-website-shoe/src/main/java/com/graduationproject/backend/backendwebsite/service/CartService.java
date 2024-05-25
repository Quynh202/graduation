package com.graduationproject.backend.backendwebsite.service;

import com.graduationproject.backend.backendwebsite.entity.CartEntity;
import com.graduationproject.backend.backendwebsite.entity.CartEntityKey;
import com.graduationproject.backend.backendwebsite.repository.CartRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CartService {

  @Autowired
  CartRepository cartRepository;

  public Long getMaxCartId() {
    Optional<Long> cartId = cartRepository.findMaxOrderId();
    return cartId.isEmpty() ? 1L : cartId.get();
  }

  /**
   * Insert cart into database.
   *
   * @param cartEntityList cartEntityList
   * @return entity
   */
  public List<CartEntity> insert(List<CartEntity> cartEntityList) {
    return cartRepository.saveAll(cartEntityList);
  }

  /**
   * Get all.
   *
   * @return entity
   */
  public List<CartEntity> getAll() {
    return cartRepository.findAll();
  }

  public List<CartEntity> getByCartId(Long cartId) {
    return cartRepository.findByCartId(cartId);
  }

  public void deleteAllByCartId(Long cartId, Long productId, String productSizeName) {
    CartEntityKey cartEntityKey = new CartEntityKey();
    cartEntityKey.setPk(cartId, productId, productSizeName);
    cartRepository.deleteById(cartEntityKey);
  }
}
