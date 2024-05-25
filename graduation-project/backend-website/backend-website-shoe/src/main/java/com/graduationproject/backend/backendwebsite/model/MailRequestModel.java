package com.graduationproject.backend.backendwebsite.model;

import lombok.Data;


@Data
public class MailRequestModel {

  private String mailName;

  private String mailTo;

  private String mailFrom;

  private String mailSubject;
}
