package com.graduationproject.backend.backendwebsite.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentModel {

  private Long productId;

  private String commentDetail;

  private String commentName;

  private String commentEmail;

}
