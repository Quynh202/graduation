package com.graduationproject.backend.backendwebsite.dto;


public interface IProduct {


  String getCategoryId();

  String getCategoryName();

  String getProductId();

  String getProductName();

  String getProductPrice();

  String getProductPriceSale();

  String getProductQuantity();

  String getProductDescription();

  String getProductSeo();

  String getImageId();

  String getFileName();

  String getFileType();

  String getFileCode();

  byte[] getFileSize();
}
