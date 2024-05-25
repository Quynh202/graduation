package com.graduationproject.backend.backendwebsite.forms;

import com.graduationproject.backend.backendwebsite.dto.IContact;
import com.graduationproject.backend.backendwebsite.entity.ContactEntity;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactFormPage {

  private List<ContactEntity> contactModelList;

  private List<IContact> contactList;

  private int pageNo;

  private int pageSize;

  private long totalElements;

  private int totalPages;

  private boolean last;

}
