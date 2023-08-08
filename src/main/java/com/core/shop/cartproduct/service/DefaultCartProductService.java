package com.core.shop.cartproduct.service;

import com.core.shop.cart.entity.CartEntity;
import com.core.shop.cart.repository.CartRepository;
import com.core.shop.cartproduct.dto.CartProductRequest;
import com.core.shop.cartproduct.dto.CartProductResponse;
import com.core.shop.cartproduct.entity.CartProductEntity;
import com.core.shop.cartproduct.repository.CartProductRepository;
import com.core.shop.member.entity.MemberEntity;
import com.core.shop.member.repository.MemberRepository;
import com.core.shop.product.entity.ProductEntity;
import com.core.shop.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultCartProductService implements CartProductService{

    private final CartProductRepository cartProductRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    @Override
    public void cartProductRegister(CartProductRequest request) {

        MemberEntity member = memberRepository.findByEmail(request.getEmail())
                                              .orElseThrow(RuntimeException::new);

        CartEntity cart = cartRepository.findByMember(member);

        ProductEntity product = productRepository.findById(request.getProductId())
                                                 .orElseThrow(RuntimeException::new);

        CartProductEntity cartProductEntity = new CartProductEntity(cart, product, request.getCount());

        Optional<CartProductEntity> byId = cartProductRepository.findById(cartProductEntity.getPk());

        // 장바구니상품 테이블 조회후 값이 있으면 기존수량에 더해주기
        if(byId.isPresent()){
            cartProductEntity.addCount(request.getCount());
        }

        cartProductRepository.save(cartProductEntity);
    }

    @Override
    public List<CartProductResponse> getCartProduct(Authentication auth) {

        String email = auth.getName();

        MemberEntity member = memberRepository.findByEmail(email)
                                              .orElseThrow(RuntimeException::new);

        CartEntity cart = cartRepository.findByMember(member);

        List<CartProductEntity> cartProductList = cartProductRepository.findByCart(cart)
                                                                       .orElseThrow(RuntimeException::new);

        List<CartProductResponse> cartProductResponseList = new ArrayList<>();

        for (CartProductEntity cartProductEntity : cartProductList){
            ProductEntity product = productRepository.findById(cartProductEntity.getPk().getProductId())
                                                     .orElseThrow(RuntimeException::new);
            CartProductResponse cartProductResponse = new CartProductResponse();
            cartProductResponse.setProductName(product.getName());
            cartProductResponse.setCount(cartProductEntity.getCount());
            cartProductResponse.setPrice(product.getPrice());

            cartProductResponseList.add(cartProductResponse);
        }

        return cartProductResponseList;
    }

    @Override
    public List<CartProductResponse> deleteCartProduct(Long productId, Authentication auth) {

        String email = auth.getName();

        MemberEntity member = memberRepository.findByEmail(email)
                                              .orElseThrow(RuntimeException::new);

        CartEntity cart = cartRepository.findByMember(member);

        CartProductEntity cartProductEntity = cartProductRepository.getCartProduct(cart.getId(), productId)
                                                                   .orElseThrow(RuntimeException::new);

        cartProductRepository.deleteById(cartProductEntity.getPk());


        return getCartProduct(auth);
    }
}
