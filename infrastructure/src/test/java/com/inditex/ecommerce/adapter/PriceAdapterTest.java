package com.inditex.ecommerce.adapter;

import com.inditex.ecommerce.entity.PriceEntity;
import com.inditex.ecommerce.mock.MockObject;
import com.inditex.ecommerce.model.Price;
import com.inditex.ecommerce.repository.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PriceAdapterTest {

    @InjectMocks
    private PriceAdapter priceAdapter;

    @Mock
    private PriceRepository priceRepository = mock(PriceRepository.class);


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void ifThereIsNoDataThenReturnEmptyList() {
        when(priceRepository.getPriceByBrandProductAndDate(anyInt(), anyInt(), any(LocalDateTime.class))).thenReturn(Optional.empty());
        Optional<List<Price>> adapterPrices = priceAdapter.getPriceByBrandProductAndDate(1,1,MockObject.createApplicationDate());
        assertThat(adapterPrices, is(Optional.empty()));
    }


    @Test
    void ifThereIsDataThenReturnListOfPrices() {
        when(priceRepository.getPriceByBrandProductAndDate(anyInt(), anyInt(), any(LocalDateTime.class)))
                .thenReturn(Optional.of(MockObject.createPriceEntityList()));
        Optional<List<Price>> adapterPrices = priceAdapter.getPriceByBrandProductAndDate(1,1,MockObject.createApplicationDate());

        assertThat(adapterPrices.get(), hasSize(MockObject.createPriceEntityList().size()));

        Price priceId1 = adapterPrices.get().get(0);
        PriceEntity priceEntityId1 = MockObject.createPriceEntityId1();
        assertThat(priceId1.getId(), is(priceEntityId1.getId()));
        assertThat(priceId1.getPriceList(), is(priceEntityId1.getPriceList()));
        assertThat(priceId1.getPrice(), is(priceEntityId1.getPrice()));

        Price priceId2 = adapterPrices.get().get(1);
        PriceEntity priceEntityId2 = MockObject.createPriceEntityId2();
        assertThat(priceId2.getId(), is(priceEntityId2.getId()));
        assertThat(priceId2.getPriceList(), is(priceEntityId2.getPriceList()));
        assertThat(priceId2.getPrice(), is(priceEntityId2.getPrice()));
    }
}
