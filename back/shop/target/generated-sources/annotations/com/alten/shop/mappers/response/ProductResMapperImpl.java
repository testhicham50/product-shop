package com.alten.shop.mappers.response;

import com.alten.shop.dto.response.ProductResDto;
import com.alten.shop.entities.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21 (Amazon.com Inc.)"
)
@Component
public class ProductResMapperImpl implements ProductResMapper {

    @Override
    public Product toEntity(ProductResDto dto) {
        if ( dto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.id( dto.getId() );
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
    public ProductResDto toDto(Product entity) {
        if ( entity == null ) {
            return null;
        }

        ProductResDto productResDto = new ProductResDto();

        productResDto.setId( entity.getId() );
        productResDto.setCode( entity.getCode() );
        productResDto.setName( entity.getName() );
        productResDto.setDescription( entity.getDescription() );
        productResDto.setPrice( entity.getPrice() );
        productResDto.setQuantity( entity.getQuantity() );
        productResDto.setInventoryStatus( entity.getInventoryStatus() );
        productResDto.setCategory( entity.getCategory() );
        productResDto.setImage( entity.getImage() );
        productResDto.setRating( entity.getRating() );

        return productResDto;
    }
}
