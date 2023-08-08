package com.core.shop.order.service;

import com.core.shop.cart.entity.CartEntity;
import com.core.shop.cart.repository.CartRepository;
import com.core.shop.cartproduct.entity.CartProductEntity;
import com.core.shop.cartproduct.repository.CartProductRepository;
import com.core.shop.member.entity.MemberEntity;
import com.core.shop.member.repository.MemberRepository;
import com.core.shop.order.dto.OrderDetailsResponse;
import com.core.shop.order.dto.OrderResponse;
import com.core.shop.order.entity.OrderEntity;
import com.core.shop.order.entity.OrderStatus;
import com.core.shop.order.repository.OrderRepository;
import com.core.shop.orderproduct.entity.OrderProductEntity;
import com.core.shop.orderproduct.repository.OrderProductRepository;
import com.core.shop.product.entity.ProductEntity;
import com.core.shop.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultOrderService implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartProductRepository cartProductRepository;
    private final ProductRepository productRepository;

    @Override
    public List<OrderResponse> orders(Authentication auth) {

        String email = auth.getName();
        MemberEntity member = memberRepository.findByEmail(email)
                                              .orElseThrow(RuntimeException::new);
        CartEntity cart = cartRepository.findByMember(member);
        List<CartProductEntity> cartProduct = cartProductRepository.findByCart(cart)
                                                                   .orElseThrow(RuntimeException::new);

        OrderEntity order = new OrderEntity();
        order.setMember(member);
        order.setOrderStatus(OrderStatus.WAITING);
        order.setOrderDate(LocalDateTime.now());
        OrderEntity savedOrder = orderRepository.save(order);

        List<OrderProductEntity> orderProductEntity = new ArrayList<>();

        for(CartProductEntity cartProductEntity : cartProduct){

            ProductEntity product = productRepository.findById(cartProductEntity.getPk().getProductId())
                                                     .orElseThrow(RuntimeException::new);

            OrderProductEntity orderProduct = new OrderProductEntity();

            orderProduct.setOrder(savedOrder);
            orderProduct.setProduct(product);
            orderProduct.setOrderPrice(product.getPrice());
            orderProduct.setCount(cartProductEntity.getCount());

            orderProductEntity.add(orderProduct);
        }


        orderProductEntity.forEach(orderProductRepository::save);

        List<OrderResponse> response = new ArrayList<>();


        for(CartProductEntity cartProductEntity : cartProduct){

            ProductEntity product = productRepository.findById(cartProductEntity.getPk().getProductId())
                                                     .orElseThrow(RuntimeException::new);

            OrderResponse orderResponse = new OrderResponse();

            orderResponse.setProductName(product.getName());
            orderResponse.setPrice(product.getPrice());
            orderResponse.setCount(cartProductEntity.getCount());
            orderResponse.setEmail(member.getEmail());
            orderResponse.setAddress1(member.getAddress1());
            orderResponse.setAddress2(member.getAddress2());

            response.add(orderResponse);
        }

        return response;
    }

    @Override
    public List<OrderResponse> orderList(Authentication auth) {

        String email = auth.getName();
        MemberEntity member = memberRepository.findByEmail(email)
                                              .orElseThrow(RuntimeException::new);

        List<OrderEntity> order = orderRepository.findByMember(member)
                                                 .orElseThrow(RuntimeException::new);

//        List<List<OrderProductEntity>> orderProductList = new ArrayList<>();
        List<OrderResponse> response = new ArrayList<>();

        for(OrderEntity orderEntity : order){
            List<OrderProductEntity> orderProductEntities = orderProductRepository.findByOrder(orderEntity)
                                                                                  .orElseThrow(RuntimeException::new);

            for(OrderProductEntity orderProductEntity : orderProductEntities){
                OrderResponse orderResponse = new OrderResponse();
                ProductEntity product = productRepository.findById((orderProductEntity.getProduct().getId()))
                                                         .orElseThrow(RuntimeException::new);
                orderResponse.setProductName(product.getName());
                orderResponse.setOrderProductId(orderProductEntity.getOrderProductId());
                orderResponse.setPrice(orderProductEntity.getOrderPrice());
                orderResponse.setCount(orderProductEntity.getCount());

                response.add(orderResponse);
            }
        }
        return response;
    }

    @Override
    public OrderDetailsResponse orderDetails(Long orderProductId) {

        OrderProductEntity orderProductEntity = orderProductRepository.findById(orderProductId)
                                                                      .orElseThrow(RuntimeException::new);

        return OrderDetailsResponse.builder()
                                   .productName(orderProductEntity.getProduct().getName())
                                   .price(orderProductEntity.getOrderPrice())
                                   .count(orderProductEntity.getCount())
                                   .orderDate(orderProductEntity.getOrder().getOrderDate())
                                   .build();
    }

}
