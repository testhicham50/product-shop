package com.alten.shop.service.Impl;

import com.alten.shop.dto.request.ProductReqDto;
import com.alten.shop.dto.response.ProductResDto;
import com.alten.shop.entities.Product;
import com.alten.shop.exceptions.EntityAlreadyExistException;
import com.alten.shop.exceptions.FieldNotFoundException;
import com.alten.shop.mappers.request.ProductReqMapper;
import com.alten.shop.mappers.response.ProductResMapper;
import com.alten.shop.repository.ProductRepository;
import com.alten.shop.service.ProductService;
import com.alten.shop.utils.ProductUpdateUtil;
import com.alten.shop.validators.ObjectValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductReqMapper productReqMapper;
    private final ProductResMapper productResMapper;
    private final ObjectValidator<ProductReqDto> productValidator;
   // private final ModelMapper modelMapper;

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
        productValidator.validate(requestDto);

        productRepository.findByCode(requestDto.getCode()).ifPresent(product -> {
            throw new EntityAlreadyExistException("Product already exists");
        });

        Product product = productReqMapper.toEntity(requestDto);
        Product savedProduct = productRepository.save(product);
        return productResMapper.toDto(savedProduct);
    }

    /**
     * Updates a Product entity with the specified non-null fields from the provided map.
     *
     * @param id     The identifier of the product to be updated.
     * @param fields A map containing field names and their corresponding values for update.
     * @return A ProductResDto representing the updated product.
     * @throws EntityNotFoundException If the product with the specified ID is not found.
     * @throws FieldNotFoundException  If any of the provided fields are not valid for the Product entity.
     * @throws IllegalAccessException   If there is an issue accessing the fields during the update process.
     */
    public ProductResDto updateProduct(Long id, Map<String, Object> fields) throws IllegalAccessException {
        // Retrieve the existing product or throw an exception if not found
        Product existingProduct = getProductOrThrowNotFound(id);

        // Validate that the provided fields are valid for the Product entity
        ProductUpdateUtil.validateFields(fields);

        // Update non-null fields using custom method
        ProductUpdateUtil.updateNonNullFields(existingProduct, fields);

        // Validate the mapped ProductReqDto
        productValidator.validate(productReqMapper.toDto(existingProduct));

        // Save the updated product to the database and map it to a ProductResDto
        return productResMapper.toDto(productRepository.save(existingProduct));
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

