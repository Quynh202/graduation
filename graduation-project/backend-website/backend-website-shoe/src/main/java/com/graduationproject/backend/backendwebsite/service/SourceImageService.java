package com.graduationproject.backend.backendwebsite.service;

import com.graduationproject.backend.backendwebsite.common.CommonService;
import com.graduationproject.backend.backendwebsite.common.Constant;
import com.graduationproject.backend.backendwebsite.common.Image;
import com.graduationproject.backend.backendwebsite.entity.SourceImagesEntity;
import com.graduationproject.backend.backendwebsite.model.ProductModel;
import com.graduationproject.backend.backendwebsite.repository.SourceImagesRepository;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class SourceImageService {

  private static final String TARGET_SOURCE = "E:\\Graduation\\api-image";

  @Autowired
  CommonService commonService;

  @Autowired
  SourceImagesRepository sourceImagesRepository;

  /**
   * Insert or update image.
   *
   * @param productId    productId
   * @param files        files
   * @param productModel productModel
   * @param type         type
   * @throws Error with image.
   */
  public void insertOrUpdateImages(@NonNull Long productId, @NonNull List<MultipartFile> files,
                                   ProductModel productModel, String type) throws IOException {
    // Insert file image
    if (Constant.INSERT.equals(type)) {
      this.insertFileImage(files, productId, Constant.ZERO);
    }

    // Update file image
    if (Constant.UPDATE.equals(type)) {
      if (files.size() == productModel.getSourceImageModelList().size()) {
        this.updateFileImages(files, productId, productModel);
      }

      if (files.size() < productModel.getSourceImageModelList().size()) {
        // Delete image when file image in database > file image in input
        for (int i = files.size(); i < productModel.getSourceImageModelList().size(); i++) {
          Long imageId = Long.parseLong(productModel.getSourceImageModelList().get(i).getImageId());

          // Delete by primary key
          sourceImagesRepository.deleteById(imageId);
        }

        this.updateFileImages(files, productId, productModel);
      }

      if (files.size() > productModel.getSourceImageModelList().size()) {
        // Update file image when file image into database.
        this.updateFileImages(files, productId, productModel);

        // Insert new file image.
        this.insertFileImage(files, productId,
            productModel.getSourceImageModelList().size());
      }
    }

  }



  /**
   * Delete all source image of product by productId.
   *
   * @param productId  productId
   * @param imagesCode imagesCode
   */
  public List<SourceImagesEntity> selectByProductId(Long productId, List<String> imagesCode) {
    return sourceImagesRepository.selectByProductId(productId, imagesCode);
  }

  /**
   * Delete all source image of product by productId.
   *
   * @param productId  productId
   * @param imagesCode imagesCode
   */
  public void deleteByProductId(Long productId, List<String> imagesCode) {
    List<Long> imageIdList = this.selectByProductId(productId, imagesCode).stream()
            .map(SourceImagesEntity::getImageId)
            .toList();
    imageIdList.forEach(imageId -> sourceImagesRepository.deleteById(imageId));
  }




  /**
   * To build source image use update.
   *
   * @param file          file
   * @param fileName      fileName
   * @param imageDatabase imageDatabase
   * @return entity
   */
  private SourceImagesEntity toBuildSourceImage(MultipartFile file, String fileName,
                                                SourceImagesEntity imageDatabase)
      throws IOException {
    SourceImagesEntity entity = new SourceImagesEntity();
    entity.setImageId(imageDatabase.getImageId());
    entity.setProductId(imageDatabase.getProductId());
    entity.setData(file.getBytes());
    entity.setFileType(file.getContentType());
    entity.setFileName(fileName);
    entity.setImageCode(imageDatabase.getImageCode());
    entity.setUserInformationId(imageDatabase.getUserInformationId());
    commonService.setCommonUpdateEntity(entity);

    return entity;
  }

  /**
   * To build source image use create new.
   *
   * @param file      file
   * @param fileName  fileName
   * @param imageCode imageCode
   * @param productId productId
   * @return entity
   */
  private SourceImagesEntity toBuildSourceImage(MultipartFile file, String fileName,
                                                String imageCode, Long productId)
      throws IOException {
    SourceImagesEntity entity = new SourceImagesEntity();
    entity.setProductId(productId);
    entity.setData(file.getBytes());
    entity.setFileType(file.getContentType());
    entity.setFileName(fileName);
    entity.setImageCode(imageCode);
    entity.setUserInformationId(null);
    commonService.setCommonCreatedEntity(entity);

    return entity;
  }




  /**
   * Insert file.
   *
   * @param files      files
   * @param productId  productId
   * @param startIndex startIndex
   * @throws Error with image.
   */
  private void insertFileImage(List<MultipartFile> files, Long productId, int startIndex)
      throws IOException {
    for (int i = startIndex; i < files.size(); i++) {
      MultipartFile file = files.get(i);
      String fileName =
          StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
      try {
        // Check file name
        if (fileName.contains("..")) {
          throw new IOException("Filename contains invalid path sequence" + fileName);
        }

        // Insert data into database if type is insert.
        SourceImagesEntity entity =
            this.toBuildSourceImage(file, fileName, (i == 0) ? Image.IMAGE_MAIN_PRODUCT.getCode() :
                Image.IMAGE_SECONDARY_PRODUCT.getCode(), productId);

        try {
          String pathPng = TARGET_SOURCE + Constant.SLASH + fileName;
          file.transferTo(new File(pathPng));
        } catch (FileSystemNotFoundException exception) {
          exception.printStackTrace();
        }
        // Save data into database.
        sourceImagesRepository.save(entity);
      } catch (Exception e) {
        throw new IOException("Could not save File: " + fileName);
      }
    }
  }

  /**
   * Upload file.
   *
   * @param files        files
   * @param productId    productId
   * @param productModel productModel
   * @throws Error with image.
   */
  private void updateFileImages(List<MultipartFile> files, Long productId,
                                ProductModel productModel) throws IOException {
    for (int i = 0; i < files.size(); i++) {
      MultipartFile file = files.get(i);
      String fileName =
          StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
      try {
        // Check file name
        if (fileName.contains("..")) {
          throw new IOException("Filename contains invalid path sequence" + fileName);
        }

        // Update data into database if type is update
        Long imageId = Long.parseLong(productModel.getSourceImageModelList().get(i).getImageId());

        SourceImagesEntity entity;
        Optional<SourceImagesEntity> imageInDatabase =
            sourceImagesRepository.findById(imageId);
        if (imageInDatabase.isPresent()) {
          entity = this.toBuildSourceImage(file, fileName, imageInDatabase.get());
        } else {
          entity = this.toBuildSourceImage(file,
              fileName,
              (i == 0)
                  ? Image.IMAGE_MAIN_PRODUCT.getCode()
                  : Image.IMAGE_SECONDARY_PRODUCT.getCode(), productId);
        }
        // Save data into database.
        try {
          String pathPng = TARGET_SOURCE + Constant.SLASH + fileName;
          file.transferTo(new File(pathPng));
        } catch (FileSystemNotFoundException exception) {
          exception.printStackTrace();
        }
        sourceImagesRepository.save(entity);
      } catch (Exception e) {
        throw new IOException("Could not save File: " + fileName);
      }
    }
  }

}
