package com.graduationproject.backend.backendwebsite.forms;

import com.graduationproject.backend.backendwebsite.dto.UserRoleDTO;
import com.graduationproject.backend.backendwebsite.entity.RoleEntity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {

  private List<UserRoleDTO> userEntityList;

  private List<RoleEntity> roleEntityList;

  private int pageNo;

  private int pageSize;

  private long totalElements;

  private int totalPages;

  private boolean last;
}
