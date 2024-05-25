package com.graduationproject.backend.backendwebsite.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {

  private String username;

  private String password;

}
