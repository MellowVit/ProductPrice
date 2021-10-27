package com.inditex.ecommerce.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * Object domain that represents the final sale price and rate applied to a product during a specific date.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Price {

    private Long id;

    @Schema(description = "Group brand id", type = "integer")
    private int brandId;

    @Schema(description = "The date when associated price list will start to be applied", type = "string", format="date-time", example = "2020-06-14T12:00")
    private LocalDateTime startDate;

    @Schema(description = "The date when associated price list will cease to be applied", type = "string", format="date-time", example = "2020-06-14T12:00")
    private LocalDateTime endDate;

    @Schema(description = "Applicable price list id", type = "integer")
    private int priceList;

    @Schema(description = "Product code id", type = "integer")
    private int productId;

    @Schema(description = "Priority of the price list", type = "integer")
    private int priority;

    @Schema(description = "Final sale price", type = "number", format = "double", example = "35.50")
    private Double price;

    @Schema(description = "Currency ISO", type = "string")
    private String currency;


    public PriceResponse priceToPriceResponse() {
        return new PriceResponse(brandId, productId, priceList, startDate, endDate, price);
    }
}
