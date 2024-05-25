package com.graduationproject.backend.backendwebsite.dto;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;


public interface UserRoleDTO {

  Long getUserId();

  Long getRoleId();

  String getRoleName();

  String getUsername();

  String getUserPassword();

  String getUserEmail();

  String getFirstName();

  String getLastName();

  @DateTimeFormat(fallbackPatterns = "yyyy/MM/dd HH:mm:ss")
  Date getCreatedDate();

  Boolean getStatus();
}
