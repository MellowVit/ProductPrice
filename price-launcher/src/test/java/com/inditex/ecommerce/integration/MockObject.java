package com.inditex.ecommerce.integration;

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
                .productId(35455)
                .price(35.50)
                .currency("EUR")
                .build();
    }

    public static PriceEntity createPriceEntityId2() {
        return PriceEntity.builder()
                .id(2L)
                .brandId(1)
                .startDate(LocalDateTime.of(2020,06,14,15,00,00))
                .endDate(LocalDateTime.of(2020,06,14,18,30,00))
                .priceList(2)
                .priority(1)
                .productId(35455)
                .price(25.45)
                .currency("EUR")
                .build();
    }

    public static PriceEntity createPriceEntityId3() {
        return PriceEntity.builder()
                .id(3L)
                .brandId(1)
                .startDate(LocalDateTime.of(2020,06,15,00,00,00))
                .endDate(LocalDateTime.of(2020,06,15,11,00,00))
                .priceList(3)
                .priority(1)
                .productId(35455)
                .price(30.50)
                .currency("EUR")
                .build();
    }

    public static PriceEntity createPriceEntityId4() {
        return PriceEntity.builder()
                .id(4L)
                .brandId(1)
                .startDate(LocalDateTime.of(2020,06,15,16,00,00))
                .endDate(LocalDateTime.of(2020,12,31,23,59,59))
                .priceList(4)
                .priority(1)
                .productId(35455)
                .price(38.95)
                .currency("EUR")
                .build();
    }

    public static List<PriceEntity> createPriceEntityList() {
        List<PriceEntity> prices = new ArrayList<>();
        prices.add(createPriceEntityId1());
        prices.add(createPriceEntityId2());
        prices.add(createPriceEntityId3());
        prices.add(createPriceEntityId4());
        return prices;
    }

    public static LocalDateTime createApplicationDate() {
        return LocalDateTime.of(2020,06, 14, 23, 55, 55);
    }
}
