package com.alten.shop.service;

import com.alten.shop.dto.request.ProductReqDto;
import com.alten.shop.dto.response.ProductResDto;

import java.util.List;

public interface ProductService {
    List<ProductResDto> getAllProducts();
    ProductResDto getProductById(Long id);
    ProductResDto createProduct(ProductReqDto requestDto);
    ProductResDto updateProduct(Long id, ProductReqDto requestDto);
    void deleteProduct(Long id);
}
