package com.inditex.exception.mock;

import com.inditex.ecommerce.model.PriceResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MockObject {

    public static PriceResponse createPriceProduct1Rate1() {
        return PriceResponse.builder()
                .brandId(1)
                .startDate(LocalDateTime.of(2020,06,14,00,00,00))
                .endDate(LocalDateTime.of(2020,12,31,23,59,59))
                .priceList(1)
                .productId(35455)
                .price(35.50)
                .build();
    }

    public static PriceResponse createPriceProduct1Rate2() {
        return PriceResponse.builder()
                .brandId(1)
                .startDate(LocalDateTime.of(2020,06,14,15,00,00))
                .endDate(LocalDateTime.of(2020,06,14,18,30,00))
                .priceList(2)
                .productId(35455)
                .price(25.45)
                .build();
    }

    public static PriceResponse createPriceProduct1Rate3() {
        return PriceResponse.builder()
                .brandId(1)
                .startDate(LocalDateTime.of(2020,06,15,00,00,00))
                .endDate(LocalDateTime.of(2020,06,15,11,00,00))
                .priceList(3)
                .productId(35455)
                .price(30.50)
                .build();
    }

    public static PriceResponse createPriceProduct1Rate4() {
        return PriceResponse.builder()
                .brandId(1)
                .startDate(LocalDateTime.of(2020,06,15,16,00,00))
                .endDate(LocalDateTime.of(2020,12,31,23,59,59))
                .priceList(4)
                .productId(35455)
                .price(38.95)
                .build();
    }

    public static List<PriceResponse> createSimplePriceList() {
        List<PriceResponse> prices = new ArrayList<>();
        prices.add(createPriceProduct1Rate1());
        return prices;
    }

    public static List<PriceResponse> createCompletePriceList() {
        List<PriceResponse> prices = new ArrayList<>();
        prices.add(createPriceProduct1Rate1());
        prices.add(createPriceProduct1Rate2());
        prices.add(createPriceProduct1Rate3());
        prices.add(createPriceProduct1Rate4());
        return prices;
    }


}
