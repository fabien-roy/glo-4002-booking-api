package ca.ulaval.glo4002.booking.dto;

import ca.ulaval.glo4002.booking.interfaces.configuration.CustomJsonProvider;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class OrderDtoTest {

    private OrderDto orderDto;
    private static ObjectMapper mapper;
    private static final String A_VALID_ORDER_DATE = "{\"orderDate\": \"2050-05-21T15:23:20.142Z\"}";
    private static final String A_VALID_VENDOR_CODE = "{\"vendorCode\": \"TEST\"}";
    private static final String A_VALID_PASSDTO_OBJECT = "{\"passes\": {" +
            "\"passCategory\": \"supernova\"," +
            "\"passOption\": \"package\"," +
            "\"eventDates\": []}}";

    @BeforeAll
    static void setUpAll(){
        mapper = CustomJsonProvider.getMapper();
    }

    @BeforeEach
    void setUpEach(){
        orderDto = new OrderDto();
    }


    @Test
    void whenDeserializingOrderDate_thenCreatesOrderDateString() throws IOException {
        orderDto = mapper.readerFor(OrderDto.class).readValue(A_VALID_ORDER_DATE);

        assertEquals("2050-05-21T15:23:20.142Z", orderDto.orderDate);
    }

    @Test
    void whenDeserializingVendorCode_thenCreatesString() throws IOException{
        orderDto = mapper.readerFor(OrderDto.class).readValue(A_VALID_VENDOR_CODE);

        assertEquals("TEST", orderDto.vendorCode);
    }

    @Test
    void whenDeserializingPasses_thenCreatesPassDto() throws IOException{
        orderDto = mapper.readerFor(OrderDto.class).readValue(A_VALID_PASSDTO_OBJECT);

        assertNotNull(orderDto.passes);
        assertEquals("supernova", orderDto.passes.passCategory);
    }
}