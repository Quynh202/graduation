package com.graduationproject.backend.backendwebsite.service;

import com.graduationproject.backend.backendwebsite.common.CommonService;
import com.graduationproject.backend.backendwebsite.common.Constant;
import com.graduationproject.backend.backendwebsite.common.User;
import com.graduationproject.backend.backendwebsite.entity.UserInformationEntity;
import com.graduationproject.backend.backendwebsite.model.CommentModel;
import com.graduationproject.backend.backendwebsite.repository.UserInformationRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserInformationService {

  @Autowired
  CommonService commonService;

  @Autowired
  UserInformationRepository userInformationRepository;

  /**
   * Insert user with role.
   *
   * @param userInformationEntity userInformationEntity
   * @return TRUE if exist else FALSE.
   */
  public UserInformationEntity insert(UserInformationEntity userInformationEntity) {
    return userInformationRepository.save(userInformationEntity);
  }

  /**
   * Insert user with role.
   *
   * @param commentModel commentModel
   * @return entity.
   */
  public UserInformationEntity insert(CommentModel commentModel) {
    return userInformationRepository.save(this.toBuildUserInformationComment(commentModel));
  }

  public Optional<UserInformationEntity> getUserInformation(Long userId) {
    return userInformationRepository.findByUserId(userId);
  }

  /**
   * To build user information entity.
   *
   * @param commentModel commentModel
   * @return entity.
   */
  private UserInformationEntity toBuildUserInformationComment(CommentModel commentModel) {
    UserInformationEntity entity = new UserInformationEntity();
    entity.setAddress(Constant.EMPTY_SPACE);
    entity.setEmail(commentModel.getCommentEmail());
    entity.setLastName(commentModel.getCommentName());
    entity.setPhone(Constant.EMPTY_SPACE);
    entity.setUserCode(User.CUSTOMER.getCode());

    commonService.setCommonCreatedEntity(entity);
    return entity;
  }

  public UserInformationEntity update(UserInformationEntity userInfo) {
    return userInformationRepository.save(userInfo);
  }
}
