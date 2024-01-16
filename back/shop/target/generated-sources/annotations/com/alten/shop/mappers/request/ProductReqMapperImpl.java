package com.alten.shop.mappers.request;

import com.alten.shop.dto.request.ProductReqDto;
import com.alten.shop.entities.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21 (Amazon.com Inc.)"
)
@Component
public class ProductReqMapperImpl implements ProductReqMapper {

    @Override
    public Product toEntity(ProductReqDto dto) {
        if ( dto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.code( dto.getCode() );
        product.name( dto.getName() );
        product.description( dto.getDescription() );
        product.price( dto.getPrice() );
        product.quantity( dto.getQuantity() );
        product.inventoryStatus( dto.getInventoryStatus() );
        product.category( dto.getCategory() );
        product.image( dto.getImage() );
        product.rating( dto.getRating() );

        return product.build();
    }

    @Override
    public ProductReqDto toDto(Product entity) {
        if ( entity == null ) {
            return null;
        }

        ProductReqDto productReqDto = new ProductReqDto();

        productReqDto.setCode( entity.getCode() );
        productReqDto.setName( entity.getName() );
        productReqDto.setDescription( entity.getDescription() );
        productReqDto.setPrice( entity.getPrice() );
        productReqDto.setQuantity( entity.getQuantity() );
        productReqDto.setInventoryStatus( entity.getInventoryStatus() );
        productReqDto.setCategory( entity.getCategory() );
        productReqDto.setImage( entity.getImage() );
        productReqDto.setRating( entity.getRating() );

        return productReqDto;
    }
}
