package com.graduationproject.backend.backendwebsite.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactModel {

  private Long productId;

  private String contactEmail;

  private String contactMessage;

  private String contactName;

  private String contactStatus;

}
