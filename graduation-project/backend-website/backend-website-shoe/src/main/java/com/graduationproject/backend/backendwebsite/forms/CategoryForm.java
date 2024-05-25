package com.graduationproject.backend.backendwebsite.forms;

import com.graduationproject.backend.backendwebsite.model.CategoryModel;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryForm {

  private List<CategoryModel> categoryModelList;

  private int pageNo;

  private int pageSize;

  private long totalElements;

  private int totalPages;

  private boolean last;
}
