package com.inditex.ecommerce.mock;

import com.inditex.ecommerce.entity.PriceEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MockObject {


    public static PriceEntity createPriceEntityId1() {
        return PriceEntity.builder()
                .id(1L)
                .brandId(1)
                .startDate(LocalDateTime.of(2020,06,14,00,00,00))
                .endDate(LocalDateTime.of(2020,12,31,23,59,59))
                .priceList(1)
                .priority(0)
                .productId(1)
                .price(35.50)
                .currency("EUR")
                .build();
    }

    public static PriceEntity createPriceEntityId2() {
        return PriceEntity.builder()
                .id(2L)
                .brandId(1)
                .startDate(LocalDateTime.of(2020,06,14,00,00,00))
                .endDate(LocalDateTime.of(2020,06,14,15,00,00))
                .priceList(2)
                .priority(0)
                .productId(1)
                .price(25.45)
                .currency("EUR")
                .build();
    }

    public static List<PriceEntity> createPriceEntityList() {
        List<PriceEntity> prices = new ArrayList<>();
        prices.add(createPriceEntityId1());
        prices.add(createPriceEntityId2());
        return prices;
    }

    public static LocalDateTime createApplicationDate() {
        return LocalDateTime.of(2020,06, 14, 23, 55, 55);
    }
}
