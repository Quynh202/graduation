package com.graduationproject.backend.backendwebsite.repository;

import com.graduationproject.backend.backendwebsite.dto.UserRoleDTO;
import com.graduationproject.backend.backendwebsite.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  /**
   * Get one user.
   *
   * @param username username
   * @return user.
   */
  @Query(value = " FROM UserEntity usr "
      + " WHERE usr.username = ?1")
  Optional<UserEntity> findByUsername(String username);


  /**
   * Get one user.
   *
   * @param pageable pageable
   * @return user.
   */
  @Query(value = "SELECT DISTINCT(usr.user_id) AS userId, role.role_id AS roleId,"
      + " role.role_name AS roleName, usr.username AS username, usr.user_email AS userEmail,"
      + " usfn.last_name AS lastName, usfn.first_name AS firstName,"
      + " usr.created_date AS createdDate, usr.status AS status"
      + " FROM tbl_user usr "
      + " INNER JOIN tbl_role role ON usr.role_id = role.role_id"
      + " LEFT JOIN tbl_user_information usfn ON usr.user_id = usfn.user_id"
      + " WHERE usr.username LIKE %?1% OR usr.user_email LIKE %?1%"
      + " OR usfn.first_name LIKE %?1% OR usfn.last_name LIKE %?1%", nativeQuery = true,
      countQuery = "SELECT COUNT(*)"
          + " FROM tbl_user usr"
          + " INNER JOIN tbl_role role ON usr.role_id = role.role_id"
          + " LEFT JOIN tbl_user_information usfn ON usr.user_id = usfn.user_id"
          + " WHERE usr.username LIKE %?1% OR usr.user_email LIKE %?1%"
          + " OR usfn.first_name LIKE %?1% OR usfn.last_name LIKE %?1%")
  Page<UserRoleDTO> getAll(String searchValue, Pageable pageable);

  /**
   * Get TRUE or FALSE.
   *
   * @param username username
   * @return TRUE IF exists ELSE FALSE.
   */
  @Query(value = "SELECT CASE WHEN COUNT(usr) > 0 THEN TRUE ELSE FALSE END"
      + " FROM UserEntity usr WHERE LOWER(usr.username) LIKE LOWER(?1)")
  Boolean existsByUsername(String username);

  /**
   * Get TRUE or FALSE.
   *
   * @param email email
   * @return TRUE IF exists ELSE FALSE.
   */
  @Query(value = "SELECT CASE WHEN COUNT(usr) > 0 THEN TRUE ELSE FALSE END"
      + " FROM UserEntity usr "
      + " INNER JOIN UserInformationEntity usrIn ON usr.userId = usrIn.userId"
      + " WHERE LOWER(usrIn.email) LIKE LOWER(?1)")
  Boolean existsByEmail(String email);

}
