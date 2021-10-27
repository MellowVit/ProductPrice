package com.inditex.exception.controller;

import com.inditex.ecommerce.controller.PriceController;
import com.inditex.ecommerce.model.PriceResponse;
import com.inditex.ecommerce.port.api.PriceServicePort;
import com.inditex.exception.mock.MockObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@WebMvcTest(value = PriceController.class)
public class PriceControllerTest {

    private final MediaType contentType = MediaType.APPLICATION_JSON;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PriceServicePort priceServicePort;

    @BeforeEach
    public void setUp() {
        this.mvc = MockMvcBuilders.standaloneSetup(new PriceController(priceServicePort)).build();
    }


    @Test
    void ifInvalidUriThenReturnNotFoundStatus() throws Exception {
        String urlTemplate = "/api/price/wrongUrl/1/wrongUrl/1?applicationDate=2020-06-14T12:00";

        mvc.perform(get(urlTemplate).contentType(contentType))
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void ifInvalidUrlParamThenReturnBadRequestStatus() throws Exception {
        String urlTemplate = "/api/price/brand/a/product/a?applicationDate=2020-06-14T12:00";

        mvc.perform(get(urlTemplate).contentType(contentType))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void ifInvalidApplicationDateFormatThenReturnBadRequestStatus() throws Exception {
        String urlTemplate = "/api/price/brand/1/product/1?applicationDate=2020-06-14";

        mvc.perform(get(urlTemplate).contentType(contentType))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


    @Test
    void ifRequestOkThenReturnStatus200() throws Exception {
        String urlTemplate = "/api/price/brand/1/product/1?applicationDate=2020-06-14T12:00";

        mvc.perform(get(urlTemplate).contentType(contentType))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void ifRequestOkThenReturnStatus200AndPriceListInfo() throws Exception {
        when(priceServicePort.getPriceByBrandProductAndDate(anyInt(), anyInt(), any(LocalDateTime.class))).thenReturn(MockObject.createSimplePriceList());
        String urlTemplate = "/api/price/brand/1/product/1?applicationDate=2020-06-14T12:00:00";

        PriceResponse expectedPrice = MockObject.createSimplePriceList().get(0);

        mvc.perform(get(urlTemplate).contentType(contentType))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].brandId", is(expectedPrice.getBrandId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].productId", is(expectedPrice.getProductId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].priceList", is(expectedPrice.getPriceList())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].price", is(expectedPrice.getPrice())))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * TEST 1: Day 14 at 10:00 for product 35455 of brand 1 (Zara)
     */
    @Test
    void test1() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020,06,14, 10,0,0);
        List<PriceResponse> expectedResponse = List.of(MockObject.createPriceProduct1Rate1());
        PriceResponse expectedPrice = expectedResponse.get(0);

        when(priceServicePort.getPriceByBrandProductAndDate(1, 35455, applicationDate)).thenReturn(expectedResponse);
        String urlTemplate = "/api/price/brand/1/product/35455?applicationDate=2020-06-14T10:00:00";


        mvc.perform(get(urlTemplate).contentType(contentType))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].brandId", is(expectedPrice.getBrandId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].productId", is(expectedPrice.getProductId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].priceList", is(expectedPrice.getPriceList())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].price", is(expectedPrice.getPrice())))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * TEST 2: Day 14 at 16:00 for product 35455 of brand 1 (Zara)
     */
    @Test
    void test2() throws Exception {

        List<PriceResponse> expectedResponse = List.of(MockObject.createPriceProduct1Rate1(), MockObject.createPriceProduct1Rate2());
        PriceResponse expectedPrice1 = expectedResponse.get(0);
        PriceResponse expectedPrice2 = expectedResponse.get(1);

        LocalDateTime applicationDate = LocalDateTime.of(2020,06,14, 16,0,0);
        when(priceServicePort.getPriceByBrandProductAndDate(1, 35455, applicationDate)).thenReturn(expectedResponse);
        String urlTemplate = "/api/price/brand/1/product/35455?applicationDate=2020-06-14T16:00:00";

        mvc.perform(get(urlTemplate).contentType(contentType))
                .andExpect(status().is(HttpStatus.OK.value()))
                //First result
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].brandId", is(expectedPrice1.getBrandId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].productId", is(expectedPrice1.getProductId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].priceList", is(expectedPrice1.getPriceList())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].price", is(expectedPrice1.getPrice())))
                //Second result
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].brandId", is(expectedPrice2.getBrandId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].productId", is(expectedPrice2.getProductId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].priceList", is(expectedPrice2.getPriceList())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].price", is(expectedPrice2.getPrice())))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * TEST 3: Day 14 at 21:00 for product 35455 of brand 1 (Zara)
     */
    @Test
    void test3() throws Exception {

        List<PriceResponse> expectedResponse = List.of(MockObject.createPriceProduct1Rate1());
        PriceResponse expectedPrice1 = expectedResponse.get(0);

        LocalDateTime applicationDate = LocalDateTime.of(2020,06,14, 21,0,0);
        when(priceServicePort.getPriceByBrandProductAndDate(1, 35455, applicationDate)).thenReturn(expectedResponse);
        String urlTemplate = "/api/price/brand/1/product/35455?applicationDate=2020-06-14T21:00:00";

        mvc.perform(get(urlTemplate).contentType(contentType))
                .andExpect(status().is(HttpStatus.OK.value()))
                //First result
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].brandId", is(expectedPrice1.getBrandId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].productId", is(expectedPrice1.getProductId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].priceList", is(expectedPrice1.getPriceList())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].price", is(expectedPrice1.getPrice())))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * TEST 4: Day 15 at 10:00 for product 35455 of brand 1 (Zara)
     */
    @Test
    void test4() throws Exception {

        List<PriceResponse> expectedResponse = List.of(MockObject.createPriceProduct1Rate1(), MockObject.createPriceProduct1Rate3());
        PriceResponse expectedPrice1 = expectedResponse.get(0);
        PriceResponse expectedPrice2 = expectedResponse.get(1);

        LocalDateTime applicationDate = LocalDateTime.of(2020,06,15, 10,0,0);
        when(priceServicePort.getPriceByBrandProductAndDate(1, 35455, applicationDate)).thenReturn(expectedResponse);
        String urlTemplate = "/api/price/brand/1/product/35455?applicationDate=2020-06-15T10:00:00";

        mvc.perform(get(urlTemplate).contentType(contentType))
                .andExpect(status().is(HttpStatus.OK.value()))
                //First result
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].brandId", is(expectedPrice1.getBrandId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].productId", is(expectedPrice1.getProductId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].priceList", is(expectedPrice1.getPriceList())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].price", is(expectedPrice1.getPrice())))
                //Second result
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].brandId", is(expectedPrice2.getBrandId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].productId", is(expectedPrice2.getProductId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].priceList", is(expectedPrice2.getPriceList())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].price", is(expectedPrice2.getPrice())))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * TEST 5: Day 16 at 21:00 for product 35455 of brand 1 (Zara)
     */
    @Test
    void test5() throws Exception {

        List<PriceResponse> expectedResponse = List.of(MockObject.createPriceProduct1Rate1(), MockObject.createPriceProduct1Rate4());
        PriceResponse expectedPrice1 = expectedResponse.get(0);
        PriceResponse expectedPrice2 = expectedResponse.get(1);

        LocalDateTime applicationDate = LocalDateTime.of(2020,06,16, 21,0,0);
        when(priceServicePort.getPriceByBrandProductAndDate(1, 35455, applicationDate)).thenReturn(expectedResponse);
        String urlTemplate = "/api/price/brand/1/product/35455?applicationDate=2020-06-16T21:00:00";

        mvc.perform(get(urlTemplate).contentType(contentType))
                .andExpect(status().is(HttpStatus.OK.value()))
                //First result
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].brandId", is(expectedPrice1.getBrandId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].productId", is(expectedPrice1.getProductId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].priceList", is(expectedPrice1.getPriceList())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].price", is(expectedPrice1.getPrice())))
                //Second result
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].brandId", is(expectedPrice2.getBrandId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].productId", is(expectedPrice2.getProductId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].priceList", is(expectedPrice2.getPriceList())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].price", is(expectedPrice2.getPrice())))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
