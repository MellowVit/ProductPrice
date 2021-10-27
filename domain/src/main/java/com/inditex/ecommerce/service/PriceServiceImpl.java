package com.inditex.ecommerce.service;

import com.inditex.ecommerce.model.Price;
import com.inditex.ecommerce.model.PriceResponse;
import com.inditex.ecommerce.port.api.PriceServicePort;
import com.inditex.ecommerce.port.spi.PricePersistencePort;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Represents the implementation of {@link PriceServicePort} interface
 */
public class PriceServiceImpl implements PriceServicePort {

    private final PricePersistencePort pricePersistencePort;

    public PriceServiceImpl(PricePersistencePort pricePersistencePort) {
        this.pricePersistencePort = pricePersistencePort;
    }

    @Override
    public List<PriceResponse> getPriceByBrandProductAndDate(int brandId, int productId, LocalDateTime applicationDate) {
        Optional<List<Price>> prices = pricePersistencePort.getPriceByBrandProductAndDate(brandId, productId, applicationDate);

        if (!prices.isPresent()) {
            return Collections.emptyList();
        }

        return prices.get().stream()
                .map(Price::priceToPriceResponse)
                .collect(Collectors.toList());
    }
}
