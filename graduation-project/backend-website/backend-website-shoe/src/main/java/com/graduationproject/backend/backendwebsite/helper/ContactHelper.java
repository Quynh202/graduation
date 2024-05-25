package com.graduationproject.backend.backendwebsite.helper;

import com.graduationproject.backend.backendwebsite.common.CommonService;
import com.graduationproject.backend.backendwebsite.common.Constant;
import com.graduationproject.backend.backendwebsite.dto.IContact;
import com.graduationproject.backend.backendwebsite.entity.ContactEntity;
import com.graduationproject.backend.backendwebsite.forms.ContactFormPage;
import com.graduationproject.backend.backendwebsite.model.ContactModel;
import com.graduationproject.backend.backendwebsite.service.ContactService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;


@Component
public class ContactHelper {

  @Autowired
  CommonService commonService;

  @Autowired
  ContactService contactService;

  /**
   * Select all contact.
   *
   * @param pageNo        pageNo
   * @param pageSize      pageSize
   * @param sortBy        sortBy
   * @param sortDirection sortDirection
   * @return list contact.
   */
  @NonNull
  public ContactFormPage getAllContact(int pageNo, int pageSize, String sortBy,
                                       String sortDirection) {
    Pageable pageable = commonService.setPageable(pageSize, pageNo, sortBy, sortDirection);

    // Create pageable instance
    Page<ContactEntity> contact = contactService.getAll(pageable);

    // Get content for page object
    List<ContactEntity> contactModelList = contact.getContent();

    return ContactFormPage.builder()
        .contactModelList(contactModelList)
        .pageNo(contact.getNumber())
        .pageSize(contact.getSize())
        .totalElements(contact.getTotalElements())
        .totalPages(contact.getTotalPages())
        .last(contact.isLast())
        .build();
  }

  /**
   * Select all contact.
   *
   * @param pageNo        pageNo
   * @param pageSize      pageSize
   * @param sortBy        sortBy
   * @param sortDirection sortDirection
   * @param searchValue   searchValue
   * @return list contact.
   */
  @NonNull
  public ContactFormPage getAllContactUser(int pageNo, int pageSize, String sortBy,
                                           String sortDirection, String searchValue) {

    Pageable pageable = commonService.setPageable(pageSize, pageNo, sortBy, sortDirection);

    // Create pageable instance
    Page<IContact> contact = contactService.getAllPageable(searchValue, pageable);

    // Get content for page object
    List<IContact> contactList = contact.getContent();

    return ContactFormPage.builder()
        .contactList(contactList)
        .pageNo(contact.getNumber())
        .pageSize(contact.getSize())
        .totalElements(contact.getTotalElements())
        .totalPages(contact.getTotalPages())
        .last(contact.isLast())
        .build();
  }

  /**
   * Insert new entity of contact.
   *
   * @param contactModel contactModel
   * @return entity contact.
   */
  public ContactEntity insert(@NonNull ContactModel contactModel) {
    ContactEntity contactEntity =
        this.toBuildContact(contactModel);
    return contactService.insert(contactEntity);
  }

  /**
   * Build contact entity.
   *
   * @param contactModel contactModel
   * @return entity.
   */
  private ContactEntity toBuildContact(ContactModel contactModel) {
    ContactEntity contactEntity = new ContactEntity();
    contactEntity.setProductId(contactModel.getProductId());
    contactEntity.setContactEmail(contactModel.getContactEmail());
    contactEntity.setContactName(contactModel.getContactName());
    contactEntity.setContactMessage(contactModel.getContactMessage());
    commonService.setCommonCreatedEntity(contactEntity);

    return contactEntity;
  }

  public ContactEntity update(Long contactId) {
    return contactService.update(contactId);
  }
}
