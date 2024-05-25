package com.graduationproject.backend.backendwebsite.forms;

import com.graduationproject.backend.backendwebsite.dto.IProduct;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductForm {

  private List<IProduct> productModelList;

  private int pageNo;

  private int pageSize;

  private long totalElements;

  private int totalPages;

  private boolean last;
}
