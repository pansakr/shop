package com.core.shop.product.service;

import com.core.shop.admin.dto.AdminProductRequest;
import com.core.shop.admin.dto.AdminProductResponse;
import com.core.shop.product.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    // 상품 등록
    AdminProductResponse productRegister(AdminProductRequest request);

    // 상품 수정
    AdminProductResponse productModify(Long productId, AdminProductRequest request);

    // 상품 수정 페이지 이동
    AdminProductResponse productModify(Long productId);

    // 상품 삭제
    void productDelete(Long productId);

    // 상품 상세 조회
    ProductResponse importOneProduct(Long productId);

    // 상품 재고 조회
    AdminProductResponse productStock(Long productId);

    AdminProductResponse productStockModify(Long productId, AdminProductRequest request);
}
