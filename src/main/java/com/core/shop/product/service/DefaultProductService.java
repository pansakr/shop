package com.core.shop.product.service;

import com.core.shop.admin.dto.AdminProductRequest;
import com.core.shop.admin.dto.AdminProductResponse;
import com.core.shop.product.dto.ProductResponse;
import com.core.shop.product.entity.ProductEntity;
import com.core.shop.product.entity.ProductSellStatus;
import com.core.shop.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DefaultProductService implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    @Override
    public AdminProductResponse productRegister(AdminProductRequest request) {

        ProductEntity product = new ProductEntity();

        mapper.map(request, product);
        product.setSellStatus(ProductSellStatus.FOR_SALE);
        ProductEntity saved = productRepository.save(product);

        AdminProductResponse response = mapper.map(saved, AdminProductResponse.class);

        return response;
    }

    @Override
    public AdminProductResponse productModify(Long productId, AdminProductRequest request) {

        ProductEntity product = productRepository.findById(productId)
                                                 .orElseThrow(() -> new RuntimeException());

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDetails(request.getDetails());

        if(request.getSellStatus().equals(2)){
            product.setSellStatus(ProductSellStatus.SOLD_OUT);
        }else{
            product.setSellStatus(ProductSellStatus.FOR_SALE);
        }

        AdminProductResponse response = mapper.map(product, AdminProductResponse.class);

        return response;
    }

    @Override
    public AdminProductResponse productModify(Long productId) {

        ProductEntity product = productRepository.findById(productId)
                                                 .orElseThrow(() -> new RuntimeException());

        AdminProductResponse response = mapper.map(product, AdminProductResponse.class);

        return response;
    }

    @Override
    public void productDelete(Long productId) {

        if(productRepository.findById(productId).isEmpty()){
            throw new RuntimeException();
        }else{
            productRepository.deleteById(productId);
        }
    }

    @Override
    public ProductResponse importOneProduct(Long productId) {

        ProductEntity product = productRepository.findById(productId)
                                                 .orElseThrow(() -> new RuntimeException());

       ProductResponse response = mapper.map(product, ProductResponse.class);

        return response;
    }

    @Override
    public AdminProductResponse productStock(Long productId) {

        Integer stock = productRepository.findStock(productId)
                                         .orElseThrow(() -> new RuntimeException());

        AdminProductResponse response = new AdminProductResponse();
        response.setStockNumber(stock);

        return response;
    }

    @Override
    public AdminProductResponse productStockModify(Long productId, AdminProductRequest request) {

        ProductEntity product = productRepository.findById(productId)
                                                 .orElseThrow(() -> new RuntimeException());

        //ProductEntity product = new ProductEntity();
        product.setStockNumber(request.getStockNumber());

        AdminProductResponse response = mapper.map(product, AdminProductResponse.class);

        return response;
    }


}
