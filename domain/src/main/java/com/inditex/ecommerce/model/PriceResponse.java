package com.inditex.ecommerce.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Represents the object to be returned on controller endpoint
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceResponse {

    @Schema(description = "Group brand id", type = "integer")
    private int brandId;

    @Schema(description = "Product code id", type = "integer")
    private int productId;

    @Schema(description = "Applicable price list id", type = "integer")
    private int priceList;

    @Schema(description = "The date when price list will start to be applied to a product", type = "string", format="date-time", example = "2020-06-14T12:00")
    private LocalDateTime startDate;

    @Schema(description = "The date when price list will cease to be applied to a product", type = "string", format="date-time", example = "2020-06-14T12:00")
    private LocalDateTime endDate;

    @Schema(description = "Final sale price", type = "integer", format = "double", example = "35.55")
    private Double price;

}
