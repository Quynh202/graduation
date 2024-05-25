package com.graduationproject.backend.backendwebsite.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class UserDTO {

  private Long userId;

  private String username;

  private String password;

  private String email;
}
