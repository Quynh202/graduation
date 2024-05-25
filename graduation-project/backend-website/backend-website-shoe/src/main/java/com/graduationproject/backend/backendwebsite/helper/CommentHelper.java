package com.graduationproject.backend.backendwebsite.helper;

import com.graduationproject.backend.backendwebsite.entity.CommentEntity;
import com.graduationproject.backend.backendwebsite.entity.UserInformationEntity;
import com.graduationproject.backend.backendwebsite.model.CommentModel;
import com.graduationproject.backend.backendwebsite.service.CommentService;
import com.graduationproject.backend.backendwebsite.service.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;


@Component
public class CommentHelper {

  @Autowired
  CommentService commentService;

  @Autowired
  UserInformationService userInformationService;

  /**
   * Insert model.
   *
   * @param commentModel commentModel
   * @return entity.
   */
  @NonNull
  public CommentEntity insert(CommentModel commentModel) {
    UserInformationEntity userInformationEntity = userInformationService.insert(commentModel);

    return commentService.insert(commentModel, userInformationEntity.getUserInformationId());
  }

}
