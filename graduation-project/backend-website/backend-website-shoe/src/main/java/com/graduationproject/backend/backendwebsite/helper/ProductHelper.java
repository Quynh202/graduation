package com.graduationproject.backend.backendwebsite.helper;

import com.graduationproject.backend.backendwebsite.common.CommonService;
import com.graduationproject.backend.backendwebsite.common.Constant;
import com.graduationproject.backend.backendwebsite.common.Image;
import com.graduationproject.backend.backendwebsite.dto.FilterProduct;
import com.graduationproject.backend.backendwebsite.dto.IComment;
import com.graduationproject.backend.backendwebsite.dto.IOneProduct;
import com.graduationproject.backend.backendwebsite.dto.IProduct;
import com.graduationproject.backend.backendwebsite.entity.ProductEntity;
import com.graduationproject.backend.backendwebsite.forms.ProductForm;
import com.graduationproject.backend.backendwebsite.model.MapProductModel;
import com.graduationproject.backend.backendwebsite.model.ProductModel;
import com.graduationproject.backend.backendwebsite.model.SourceImageModel;
import com.graduationproject.backend.backendwebsite.service.CommentService;
import com.graduationproject.backend.backendwebsite.service.ProductService;
import com.graduationproject.backend.backendwebsite.service.SourceImageService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
public class ProductHelper {

    @Autowired
    CommonService commonService;

    @Autowired
    ProductService productService;

    @Autowired
    SourceImageService sourceImageService;

    @Autowired
    CommentService commentService;

    /**
     * Select all product.
     *
     * @return list product.
     */
    @NonNull
    public List<IProduct> getAllProduct() {
        return productService.getAll();
    }

    /**
     * Select all product.
     *
     * @param categoryId categoryId
     * @param limitItem  limitItem
     * @return list product.
     */
    @NonNull
    public List<IProduct> getAllProductByCategoryId(Long categoryId, Integer limitItem) {
        return productService.getAll(categoryId, limitItem);
    }

    /**
     * Select all product.
     *
     * @param pageNo        pageNo
     * @param pageSize      pageSize
     * @param sortBy        sortBy
     * @param sortDirection sortDirection
     * @param categoryId    categoryId
     * @return list product.
     */
    @NonNull
    public ProductForm getAllProductByCategoryId(int pageNo, int pageSize, String sortBy,
                                                 String sortDirection,
                                                 Long categoryId) {
        Pageable pageable = commonService.setPageable(pageSize, pageNo, sortBy, sortDirection);

        // Create pageable instance
        Page<IProduct> productList = productService.getAllByCategoryId(pageable, categoryId);

        // Get content for page object
        List<IProduct> listOfProductModel = productList.getContent();

        return ProductForm.builder()
                .productModelList(listOfProductModel)
                .pageNo(productList.getNumber())
                .pageSize(productList.getSize())
                .totalElements(productList.getTotalElements())
                .totalPages(productList.getTotalPages())
                .last(productList.isLast())
                .build();
    }

    /**
     * Select all product.
     *
     * @param limitItem limitItem
     * @return list product.
     */
    @NonNull
    public List<IProduct> getAllProductSaleByCategoryId(Integer limitItem) {
        return productService.getAllProductSale(limitItem);
    }

    /**
     * Select all product.
     *
     * @param limitItem limitItem
     * @return list product.
     */
    @NonNull
    public List<IProduct> getAllProductNew(Integer limitItem) {
        return productService.getAllProductNew(limitItem);
    }

    /**
     * Select all product.
     *
     * @param filterProduct filterProduct
     * @return list product.
     */
    @NonNull
    public ProductForm getAllProduct(FilterProduct filterProduct) {
        Pageable pageable = commonService.setPageable(filterProduct.getPageSize(),
                filterProduct.getPageNo(), filterProduct.getSortBy(), filterProduct.getSortDirection());

        // Create pageable instance
        Page<IProduct> productList = productService.getAll(filterProduct, pageable);

        // Get content for page object
        List<IProduct> listOfProductModel = productList.getContent();

        return ProductForm.builder()
                .productModelList(listOfProductModel)
                .pageNo(productList.getNumber())
                .pageSize(productList.getSize())
                .totalElements(productList.getTotalElements())
                .totalPages(productList.getTotalPages())
                .last(productList.isLast())
                .build();
    }

    /**
     * Select all product.
     *
     * @param pageNo        pageNo
     * @param pageSize      pageSize
     * @param sortBy        sortBy
     * @param sortDirection sortDirection
     * @param searchValue   searchValue
     * @return list product.
     */
    public ProductForm getAllProduct(int pageNo, int pageSize, String sortBy, String sortDirection,
                                     String searchValue) {
        Pageable pageable = commonService.setPageable(pageSize, pageNo, sortBy, sortDirection);

        // Create pageable instance
        Page<IProduct> productList = productService.getAll(pageable, searchValue);

        // Get content for page object
        List<IProduct> listOfProductModel = productList.getContent();

        return ProductForm.builder()
                .productModelList(listOfProductModel)
                .pageNo(productList.getNumber())
                .pageSize(productList.getSize())
                .totalElements(productList.getTotalElements())
                .totalPages(productList.getTotalPages())
                .last(productList.isLast())
                .build();
    }

    /**
     * Select all category.
     *
     * @return list category.
     */
    public ProductModel getById(Long categoryId, Long productId) {
        List<IOneProduct> productList = productService.getProductByKey(categoryId, productId);

        return this.toBuildProductModel(productList);
    }

    /**
     * Select all category.
     *
     * @return list category.
     */
    public MapProductModel getInformationProductByKey(Long categoryId, Long productId) {
        MapProductModel mapProductModel = new MapProductModel();
        ProductModel productModel =
                this.toBuildProductModel(productService.getProductByKey(categoryId, productId));
        List<IComment> commentList = commentService.getAll(productId);
        mapProductModel.setProductModel(productModel);
        mapProductModel.setCommentList(commentList);

        return mapProductModel;
    }

    /**
     * Insert data.
     *
     * @return list category.
     */
    @NonNull
    public ProductEntity insert(ProductModel productModel, List<MultipartFile> files)
            throws IOException {
        // insert data of table product into database
        ProductEntity productEntity = productService.insert(productModel);

        Long productId = productService.getProductLast();
        // insert data of table product size into database


        // Insert image into database
        sourceImageService.insertOrUpdateImages(productId, files, new ProductModel(),
                Constant.INSERT);

        return productEntity;
    }

    /**
     * Update product by primary key.
     *
     * @param productModel productModel
     * @return entity product.
     */
    public ProductEntity update(ProductModel productModel, List<MultipartFile> files)
            throws IOException {
        // Get product entity from database.
        Optional<ProductEntity> productInDatabase = productService.getProductById(
                productModel.getCategoryId(), productModel.getProductId());

        // Check product entity have exists.
        if (productInDatabase.isPresent()) {
            // Save product
            ProductEntity productEntity = productService.update(productModel);


            // Save file image of product
            sourceImageService
                    .insertOrUpdateImages(productEntity.getProductId(), files, productModel, Constant.UPDATE);
            return productEntity;
        }

        // After product entity not exists => create entity to insert.
        ProductEntity productEntity = productService.insert(productModel);

        // insert product model
        sourceImageService
                .insertOrUpdateImages(productEntity.getProductId(), files, productModel, Constant.INSERT);

        return productEntity;
    }

    /**
     * Delete all product.
     *
     * @param categoryId categoryId
     * @param productId  productId
     * @return TRUE or FALSE
     */
    public Boolean delete(Long categoryId, Long productId) {
        // Delete source image
        List<String> productImagesCode =
                List.of(Image.IMAGE_MAIN_PRODUCT.getCode(), Image.IMAGE_SECONDARY_PRODUCT.getCode(),
                        Image.IMAGE_COLOR.getCode());
        sourceImageService.deleteByProductId(productId, productImagesCode);

        // Delete product
        Optional<ProductEntity> productEntity = productService.getProductById(categoryId, productId);
        if (productEntity.isPresent()) {
            productService.delete(productId, categoryId);
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    /**
     * Convert product entity to product model.
     *
     * @param productList productList
     * @return product model.
     */
    private ProductModel toBuildProductModel(List<IOneProduct> productList) {
        if (productList.size() > 0) {
            // Convert image entity to model.
            List<SourceImageModel> sourceImageModelList = new ArrayList<>();
            for (IOneProduct image : productList) {
                SourceImageModel model = SourceImageModel.builder()
                        .imageId(image.getImageId())
                        .fileSize(image.getFileSize())
                        .fileCode(image.getFileCode())
                        .fileName(image.getFileName())
                        .fileType(image.getFileType())
                        .build();
                sourceImageModelList.add(model);
            }
            // Convert product entity to model
            return ProductModel.builder()
                    .categoryId(Long.parseLong(productList.get(0).getCategoryId()))
                    .categoryName(productList.get(0).getCategoryName())
                    .productId(Long.parseLong(productList.get(0).getProductId()))
                    .productName(productList.get(0).getProductName())
                    .productDescription(productList.get(0).getProductDescription())
                    .productPrice(productList.get(0).getProductPrice())
                    .productPriceSale(productList.get(0).getProductPriceSale())
                    .quantity(Integer.parseInt(productList.get(0).getProductQuantity()))
                    .status(productList.get(0).getProductStatus())
                    .createdDate(productList.get(0).getCreatedDate())
                    .expireDate(productList.get(0).getExpireDate())
                    .sourceImageModelList(sourceImageModelList.stream().distinct().toList())
                    .build();
        }

        return null;

    }
}