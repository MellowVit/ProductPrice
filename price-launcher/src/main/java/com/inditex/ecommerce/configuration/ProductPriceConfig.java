package com.inditex.ecommerce.configuration;

import com.inditex.ecommerce.adapter.PriceAdapter;
import com.inditex.ecommerce.port.api.PriceServicePort;
import com.inditex.ecommerce.port.spi.PricePersistencePort;
import com.inditex.ecommerce.service.PriceServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductPriceConfig {

    @Bean
    public PricePersistencePort pricePersistencePort() {
        return new PriceAdapter();
    }

    @Bean
    public PriceServicePort priceServicePort() {
        return new PriceServiceImpl(pricePersistencePort());
    }
}
