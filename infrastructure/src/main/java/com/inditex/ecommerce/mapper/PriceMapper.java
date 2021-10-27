package com.inditex.ecommerce.mapper;

import com.inditex.ecommerce.entity.PriceEntity;
import com.inditex.ecommerce.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

/**
 * Represents the mapper interface with operations to convert domain objects from-to entity objects
 */
@Mapper(componentModel = "spring")
public interface PriceMapper {

    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);
    Price priceToPriceEntity(PriceEntity prices);
    PriceEntity priceEntityToPrice(Price finalPrice);
    List<Price> priceEntityListToPriceList(List<PriceEntity> priceList);
    List<PriceEntity> priceListToPriceEntityList(List<Price> finalPriceList);
}
