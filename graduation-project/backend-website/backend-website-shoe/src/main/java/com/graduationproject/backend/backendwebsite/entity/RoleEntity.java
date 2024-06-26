package com.graduationproject.backend.backendwebsite.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "tbl_role")
public class RoleEntity extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  public RoleEntity() {

    super();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "role_id", nullable = false)
  private Long roleId;

  @Column(name = "role_name", nullable = false)
  private String roleName;

  @Column(name = "role_description", nullable = false)
  private String roleDescription;

}
