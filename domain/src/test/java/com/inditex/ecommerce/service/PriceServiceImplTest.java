package com.inditex.ecommerce.service;

import com.inditex.ecommerce.mock.MockObject;
import com.inditex.ecommerce.model.Price;
import com.inditex.ecommerce.model.PriceResponse;
import com.inditex.ecommerce.port.spi.PricePersistencePort;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class PriceServiceImplTest {

    @Mock
    private PricePersistencePort pricePersistencePort = mock(PricePersistencePort.class);

    @Test
    void ifThereAreNoPricesThenReturnEmptyList() {
        when(pricePersistencePort.getPriceByBrandProductAndDate(anyInt(), anyInt(), any(LocalDateTime.class))).thenReturn(Optional.empty());
        List<PriceResponse> prices = new PriceServiceImpl(pricePersistencePort).getPriceByBrandProductAndDate(1,1, MockObject.createApplicationDate());
        assertThat(prices, is(Collections.emptyList()));
    }

    @Test
    void ifThereArePricesThenReturnListOfPricesResponse() {
        when(pricePersistencePort.getPriceByBrandProductAndDate(anyInt(), anyInt(), any(LocalDateTime.class))).thenReturn(Optional.of(MockObject.createPriceList()));
        List<PriceResponse> prices = new PriceServiceImpl(pricePersistencePort).getPriceByBrandProductAndDate(1,1, MockObject.createApplicationDate());

        assertThat(prices, hasSize(MockObject.createPriceList().size()));

        Price priceId1 = MockObject.createPriceProduct1Rate1();
        PriceResponse priceResponseId1 = prices.get(0);
        assertThat(priceResponseId1.getProductId(), is(priceId1.getProductId()));
        assertThat(priceResponseId1.getPriceList(), is(priceId1.getPriceList()));
        assertThat(priceResponseId1.getPrice(), is(priceId1.getPrice()));

        Price priceId2 = MockObject.createPriceProduct1Rate2();
        PriceResponse priceResponseId2 = prices.get(1);
        assertThat(priceResponseId2.getProductId(), is(priceId2.getProductId()));
        assertThat(priceResponseId2.getPriceList(), is(priceId2.getPriceList()));
        assertThat(priceResponseId2.getPrice(), is(priceId2.getPrice()));

    }


}
