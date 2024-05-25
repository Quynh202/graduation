package com.graduationproject.backend.backendwebsite.repository;

import com.graduationproject.backend.backendwebsite.entity.UserInformationEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserInformationRepository extends JpaRepository<UserInformationEntity, Long> {
  Optional<UserInformationEntity> findByUserId(Long userId);
}
