package com.graduationproject.backend.backendwebsite.model;

import com.graduationproject.backend.backendwebsite.dto.IComment;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MapProductModel {

  ProductModel productModel;

  List<IComment> commentList;
}
