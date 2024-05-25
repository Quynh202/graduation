package com.graduationproject.backend.backendwebsite.dto;

import javax.xml.crypto.Data;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public interface IOneProduct {

  String getCategoryId();

  String getCategoryName();

  String getProductId();

  String getProductName();

  String getProductPrice();

  String getProductPriceSale();

  String getProductQuantity();

  String getProductDescription();

  String getProductStatus();

  Date getExpireDate();
  Date getCreatedDate();

  String getProductSeo();



  String getImageId();

  String getFileName();

  String getFileType();

  String getFileCode();

  byte[] getFileSize();
}
