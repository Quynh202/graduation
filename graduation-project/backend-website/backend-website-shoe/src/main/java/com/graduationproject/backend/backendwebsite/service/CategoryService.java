package com.graduationproject.backend.backendwebsite.service;

import com.graduationproject.backend.backendwebsite.dto.ChartJS;
import com.graduationproject.backend.backendwebsite.dto.ICategory;
import com.graduationproject.backend.backendwebsite.entity.CategoryEntity;
import com.graduationproject.backend.backendwebsite.entity.CategoryEntityKey;
import com.graduationproject.backend.backendwebsite.repository.CategoryRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;


@Service
public class CategoryService {

  @Autowired
  CategoryRepository categoryRepository;

  /**
   * Get all category.
   *
   * @return list of entity category.
   */
  public List<CategoryEntity> getAll() {
    return categoryRepository.findAll();
  }

  /**
   * Get all category.
   *
   * @return list of entity category.
   */
  public List<ICategory> getAllByTrademark() {
    return categoryRepository.findAllCategory();
  }

  /**
   * Get all category.
   *
   * @return list of entity category.
   */
  public List<ICategory> getAllByTrademark(int pageNo, int pageSize, String searchValue,
                                           String sortBy) {
    return categoryRepository.findAllCategory(pageNo, pageSize, searchValue, sortBy);
  }

  /**
   * Get all category.
   *
   * @param sortBy sortBy
   * @param searchValue searchValue
   * @param pageable pageable
   * @return list of entity category.
   */
  public Page<ICategory> getAllByTrademark(String sortBy, String searchValue, Pageable pageable) {
    return categoryRepository.findAllCategory(sortBy, searchValue, pageable);
  }

  /**
   * Get all category.
   *
   * @return list of entity category.
   */
  public Optional<CategoryEntity> getCategoryByPrimaryKey(Long categoryId) {
    CategoryEntityKey categoryEntityKey = new CategoryEntityKey();
    categoryEntityKey.setCategoryId(categoryId);

    return categoryRepository.findById(categoryEntityKey);
  }

  /**
   * Insert new entity of category.
   *
   * @param categoryEntity categoryEntity
   * @return entity category.
   */
  public CategoryEntity insert(CategoryEntity categoryEntity) {
    return categoryRepository.save(categoryEntity);
  }

  /**
   * Update category by primary key.
   *
   * @param categoryEntity categoryEntity
   * @param categoryId     categoryId
   * @return entity category.
   */
  public CategoryEntity update(CategoryEntity categoryEntity, Long categoryId) {
    CategoryEntityKey categoryEntityKey = new CategoryEntityKey();
    categoryEntityKey.setPk(categoryId);

    Optional<CategoryEntity> categoryInDatabase = categoryRepository.findById(categoryEntityKey);
    if (categoryInDatabase.isPresent()) {
      this.toBuildCategory(categoryEntity, categoryInDatabase.get());
      return categoryRepository.save(categoryInDatabase.get());
    }
    return categoryRepository.save(categoryEntity);
  }

  /**
   * Delete category by primary key.
   *
   * @param categoryId  categoryId
   */
  public void delete(@NonNull Long categoryId) {
    CategoryEntityKey categoryEntityKey = new CategoryEntityKey();
    categoryEntityKey.setPk(categoryId);
    categoryRepository.deleteById(categoryEntityKey);
  }

  /**
   * Get all category.
   *
   * @return category
   */
  public List<ChartJS> getAllChart() {
    return categoryRepository.findAllChart();
  }

  /**
   * To build category.
   *
   * @param currentEntity  entityInInput
   * @param databaseEntity entityInDatabase
   */
  private void toBuildCategory(CategoryEntity currentEntity, CategoryEntity databaseEntity) {
    databaseEntity.setCategoryName(currentEntity.getCategoryName());
    databaseEntity.setCategoryDescription(currentEntity.getCategoryDescription());
    databaseEntity.setSeo(currentEntity.getSeo());
    databaseEntity.setUpdatedDate(new Date());
    databaseEntity.setUpdatedBy(1);
    databaseEntity.setStatus(Boolean.TRUE);
  }
}
