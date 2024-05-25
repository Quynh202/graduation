package com.graduationproject.backend.backendwebsite.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class RoleDTO {

  private Long roleId;

  private String roleName;
}
