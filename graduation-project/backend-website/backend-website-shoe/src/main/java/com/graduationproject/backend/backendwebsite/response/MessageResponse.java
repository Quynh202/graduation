package com.graduationproject.backend.backendwebsite.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageResponse {

  private String messageTo;

  private String messageFrom;

  private String messageText;

  private String messageTime;
}
