package com.alten.shop.mappers.request;

import com.alten.shop.dto.request.ProductReqDto;
import com.alten.shop.entities.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductReqMapper {

    Product toEntity(ProductReqDto dto);
    ProductReqDto toDto(Product entity);

}

