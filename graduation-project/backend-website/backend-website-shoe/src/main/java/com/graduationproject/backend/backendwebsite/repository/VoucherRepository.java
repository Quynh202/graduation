package com.graduationproject.backend.backendwebsite.repository;

import com.graduationproject.backend.backendwebsite.entity.VoucherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VoucherRepository extends JpaRepository<VoucherEntity, Long> {

}


