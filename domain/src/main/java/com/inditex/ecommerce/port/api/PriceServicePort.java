package com.inditex.ecommerce.port.api;

import com.inditex.ecommerce.model.Price;
import com.inditex.ecommerce.model.PriceResponse;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents the entry point to query the domain
 */
public interface PriceServicePort {

    /**
     * Obtains list of final prices, rates and application dates for product of a brand in a given date
     * @param brandId the group brand id
     * @param productId the product id
     * @param applicationDate date of application of price and list price for the product
     * @return list of applicable {@link Price} objects if there are any. Returns empty list otherwise
     */
    List<PriceResponse> getPriceByBrandProductAndDate(int brandId, int productId, LocalDateTime applicationDate);
}
