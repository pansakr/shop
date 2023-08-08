package com.core.shop.admin.controller;

import com.core.shop.admin.dto.AdminProductResponse;
import com.core.shop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class AdminProductController {

    private final ProductService productService;

    @GetMapping("/admin/products/{productId}")
    public String productModifyForm(@PathVariable Long productId, Model model){

        AdminProductResponse response = productService.productModify(productId);
        model.addAttribute("product",response);

        return "product/ProductModifyForm";
    }


}
