package com.alten.shop.controllers;

import com.alten.shop.dto.request.ProductReqDto;
import com.alten.shop.dto.response.ProductResDto;
import com.alten.shop.service.Impl.ProductServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productService;


    @GetMapping
    public ResponseEntity<List<ProductResDto>> getAllProducts() {
        List<ProductResDto> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResDto> getProductById(@PathVariable Long id) {
        ProductResDto product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductResDto> createProduct(@RequestBody @Valid ProductReqDto requestDTO) {
        ProductResDto createdProduct = productService.createProduct(requestDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResDto> updateProduct(
            @PathVariable Long id,
            @RequestBody @Valid Map<String, Object> fields) throws IllegalAccessException {
        ProductResDto updatedProduct = productService.updateProduct(id, fields);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

