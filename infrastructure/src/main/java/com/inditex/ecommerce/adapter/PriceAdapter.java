package com.inditex.ecommerce.adapter;

import com.inditex.ecommerce.entity.PriceEntity;
import com.inditex.ecommerce.mapper.PriceMapper;
import com.inditex.ecommerce.model.Price;
import com.inditex.ecommerce.port.spi.PricePersistencePort;
import com.inditex.ecommerce.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Represents the output adapter to communicate with domain layer
 */
@Service
public class PriceAdapter implements PricePersistencePort {

    @Autowired
    private PriceRepository priceRepository;


    @Override
    public Optional<List<Price>> getPriceByBrandProductAndDate(int brandId, int productId, LocalDateTime applicationDate) {
        Optional<List<PriceEntity>> entityPrices = priceRepository.getPriceByBrandProductAndDate(brandId, productId, applicationDate);

        if (!entityPrices.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(PriceMapper.INSTANCE.priceEntityListToPriceList(entityPrices.get()));
    }
}
