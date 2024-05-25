package com.graduationproject.backend.backendwebsite.common;

import lombok.Getter;


@Getter
public enum User {

  /*
   * UserCode = 10 => User
   * UserCode = 11 => Customer Not User
   * */
  USER("10", "user"),

  CUSTOMER("11", "customer not user");

  /* Code */
  private final String code;

  /* Value */
  private final String value;

  /**
   * Set constructor.
   *
   * @param code  code
   * @param value value
   */
  User(String code, String value) {
    this.code = code;
    this.value = value;
  }
}
