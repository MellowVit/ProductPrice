package com.inditex.ecommerce.port.spi;

import com.inditex.ecommerce.model.Price;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Represents the interface required by the domain to retrieve information
 */
public interface PricePersistencePort {

    /**
     * Obtains list of final prices, rates and application dates for product of a brand in a given date
     * @param brandId the group brand id
     * @param productId the product id
     * @param applicationDate date of application of price and list price for the product
     * @return list of applicable {@link Price} objects if there are any. Returns empty list otherwise
     */
    Optional<List<Price>> getPriceByBrandProductAndDate(int brandId, int productId, LocalDateTime applicationDate);
}
