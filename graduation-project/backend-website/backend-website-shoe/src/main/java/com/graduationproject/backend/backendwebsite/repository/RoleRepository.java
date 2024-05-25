package com.graduationproject.backend.backendwebsite.repository;

import com.graduationproject.backend.backendwebsite.dto.RoleDTO;
import com.graduationproject.backend.backendwebsite.entity.RoleEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

  /**
   * Get one role.
   *
   * @param roleName roleName
   * @return role.
   */
  @Query(value = "SELECT * FROM tbl_role role WHERE role.role_name = ?1", nativeQuery = true)
  Optional<RoleEntity> findByRoleName(String roleName);

  /**
   * Get list role.
   *
   * @param userId userId
   * @return role name.
   */
  @Query(value = "SELECT NEW com.graduationproject.backend.backendwebsite.dto.RoleDTO("
      + " role.roleId, role.roleName)"
      + " FROM RoleEntity role"
      + " INNER JOIN UserEntity usr ON usr.roleId = role.roleId"
      + " WHERE usr.userId = ?1 ORDER BY role.roleId")
  List<RoleDTO> findAllByUserId(Long userId);
}
