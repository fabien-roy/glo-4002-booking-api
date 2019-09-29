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

import static org.junit.jupiter.api.Assertions.*;

class OrderDtoTest {

    private OrderDto orderDto;
    private static ObjectMapper mapper;
    private static final String AN_INVALID_DATE_TIME_FORMAT = "{\"orderDate\": \"An Invalid Date Time Format\"}";
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
    void whenDeserializingInvalidOrderDateFormat_thenThrowsIOException(){
        assertThrows(IOException.class, () ->
                orderDto = mapper.readerFor(OrderDto.class).readValue(AN_INVALID_DATE_TIME_FORMAT)
        );
    }

    @Test
    void whenDeserializingOrderDate_thenCreatesLocalDateTimeObject() throws IOException {
        orderDto = mapper.readerFor(OrderDto.class).readValue(A_VALID_ORDER_DATE);

        assertEquals(LocalDateTime.of(2050, 5,21,15,23,20,142000000),
                orderDto.orderDate);
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