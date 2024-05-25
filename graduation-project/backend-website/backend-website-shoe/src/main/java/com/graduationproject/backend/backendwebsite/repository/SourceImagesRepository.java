package com.graduationproject.backend.backendwebsite.repository;

import com.graduationproject.backend.backendwebsite.entity.SourceImagesEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface SourceImagesRepository
    extends JpaRepository<SourceImagesEntity, Long> {


  /**
   * Delete all image of product.
   *
   * @param productId productId
   * @param imagesCode imagesCode
   */
  @Query(value = "FROM SourceImagesEntity source "
          + " WHERE source.productId = ?1 AND source.imageCode IN ?2")
  List<SourceImagesEntity> selectByProductId(Long productId, List<String> imagesCode);


}
