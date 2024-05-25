package com.graduationproject.backend.backendwebsite.forms;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpForm {

  private String firstName;

  private String lastName;

  private String username;

  private String password;

  private String email;

  private List<Long> roleIdList = new ArrayList<>();
}
