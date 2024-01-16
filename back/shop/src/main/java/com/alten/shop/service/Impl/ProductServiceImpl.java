package com.alten.shop.service.Impl;

import com.alten.shop.dto.request.ProductReqDto;
import com.alten.shop.dto.response.ProductResDto;
import com.alten.shop.entities.Product;
import com.alten.shop.mappers.request.ProductReqMapper;
import com.alten.shop.mappers.response.ProductResMapper;
import com.alten.shop.repository.ProductRepository;
import com.alten.shop.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductReqMapper productReqMapper;
    private final ProductResMapper productResMapper;

    /**
     * Retrieve all products.
     * @return List of ProductResDto representing all products.
     */
    @Override
    public List<ProductResDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productResMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieve a product by ID.
     * @param id The ID of the product to retrieve.
     * @return ProductResDto representing the retrieved product.
     * @throws EntityNotFoundException if the product with the specified ID is not found.
     */
    @Override
    public ProductResDto getProductById(Long id) {
        Product product = getProductOrThrowNotFound(id);
        return productResMapper.toDto(product);
    }

    /**
     * Create a new product.
     * @param requestDto ProductReqDto containing information for the new product.
     * @return ProductResDto representing the created product.
     */
    @Override
    public ProductResDto createProduct(ProductReqDto requestDto) {
        Product product = productReqMapper.toEntity(requestDto);
        Product savedProduct = productRepository.save(product);
        return productResMapper.toDto(savedProduct);
    }

    /**
     * Update an existing product.
     * @param id The ID of the product to update.
     * @param requestDto ProductReqDto containing updated information.
     * @return ProductResDto representing the updated product.
     * @throws EntityNotFoundException if the product with the specified ID is not found.
     */
    @Override
    public ProductResDto updateProduct(Long id, ProductReqDto requestDto) {
        Product existingProduct = getProductOrThrowNotFound(id);

        // Update the fields of the existing product with new values from the request DTO
        existingProduct.setCode(requestDto.getCode());
        existingProduct.setName(requestDto.getName());
        existingProduct.setDescription(requestDto.getDescription());
        existingProduct.setPrice(requestDto.getPrice());
        existingProduct.setQuantity(requestDto.getQuantity());
        existingProduct.setInventoryStatus(requestDto.getInventoryStatus());
        existingProduct.setCategory(requestDto.getCategory());
        existingProduct.setImage(requestDto.getImage());

        // Save the updated product
        Product updatedProduct = productRepository.save(existingProduct);
        return productResMapper.toDto(updatedProduct);
    }

    /**
     * Delete a product by ID.
     * @param id The ID of the product to delete.
     * @throws EntityNotFoundException if the product with the specified ID is not found.
     */
    @Override
    public void deleteProduct(Long id) {
        Product product = getProductOrThrowNotFound(id);
        productRepository.delete(product);
    }

    /**
     * Helper method to retrieve a product by ID or throw an exception if not found.
     * @param id The ID of the product to retrieve.
     * @return Product representing the retrieved product.
     * @throws EntityNotFoundException if the product with the specified ID is not found.
     */
    private Product getProductOrThrowNotFound(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
    }
}

