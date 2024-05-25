package com.graduationproject.backend.backendwebsite.service;

import com.graduationproject.backend.backendwebsite.dto.IContact;
import com.graduationproject.backend.backendwebsite.entity.ContactEntity;
import com.graduationproject.backend.backendwebsite.repository.ContactRepository;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ContactService {

  @Autowired
  ContactRepository contactRepository;

  /**
   * Get all contact.
   *
   * @return list of entity contact.
   */
  public Page<ContactEntity> getAll(Pageable pageable) {
    return contactRepository.findAllPageable(pageable);
  }

  /**
   * Get all contact.
   *
   * @param searchValue searchValue
   * @param pageable    pageable
   * @return list of entity contact.
   */
  public Page<IContact> getAllPageable(String searchValue, Pageable pageable) {
    return contactRepository.getAllContact(searchValue, pageable);
  }

  /**
   * Insert new entity of contact.
   *
   * @param contactEntity contactEntity
   * @return entity contact.
   */
  public ContactEntity insert(ContactEntity contactEntity) {
    return contactRepository.save(contactEntity);
  }

  public ContactEntity update(Long contactId) {
    Optional<ContactEntity> contactEntity = contactRepository.findById(contactId);
    ContactEntity contactEntity1 = new ContactEntity();
    BeanUtils.copyProperties(contactEntity.get(), contactEntity1);
    contactEntity1.setStatus(Boolean.TRUE);
    return contactRepository.save(contactEntity1);
  }
}
