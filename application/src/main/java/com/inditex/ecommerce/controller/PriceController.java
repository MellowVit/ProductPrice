package com.inditex.ecommerce.controller;

import com.inditex.ecommerce.model.PriceResponse;
import com.inditex.ecommerce.port.api.PriceServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "api/price", produces = MediaType.APPLICATION_JSON_VALUE)
public class PriceController {

    private final PriceServicePort priceServicePort;

    @Autowired
    public PriceController(PriceServicePort priceServicePort) {
        this.priceServicePort = priceServicePort;
    }

    @Operation(summary = "Get final price and list prices of a product of a brand by an application date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product info obtained ok",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PriceResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid applicationDate. Format must be [yyyy-MM-ddThh:mm],  or [yyyy-MM-ddThh:mm:ss]",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "General error. An error has occurred while trying to process your request",
                    content = @Content)})
    @GetMapping("brand/{brandId}/product/{productId}")
    public ResponseEntity<List<PriceResponse>> getPrice(@Parameter(description = "Brand id") @PathVariable int brandId,
                                                        @Parameter(description = "Product id") @PathVariable int productId,
                                                        @Parameter(description = "Application date. Format: yyyy-MM-ddThh:mm", example = "2020-06-14T12:00")
                                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                            @RequestParam LocalDateTime applicationDate) {
        List<PriceResponse> prices =  priceServicePort.getPriceByBrandProductAndDate(brandId, productId, applicationDate);
        return ResponseEntity.ok(prices);
    }
}
