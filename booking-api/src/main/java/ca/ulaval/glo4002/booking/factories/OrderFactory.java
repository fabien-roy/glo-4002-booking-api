package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.domain.NumberGenerator;
import ca.ulaval.glo4002.booking.domain.orders.Order;
import ca.ulaval.glo4002.booking.domain.orders.OrderNumber;
import ca.ulaval.glo4002.booking.domain.passes.PassList;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.exceptions.orders.InvalidOrderDateFormatException;
import ca.ulaval.glo4002.booking.exceptions.orders.InvalidOrderFormatException;
import ca.ulaval.glo4002.booking.exceptions.orders.OutOfBoundsOrderDateException;
import ca.ulaval.glo4002.booking.parsers.PassListFactory;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class OrderFactory {

    public static final LocalDateTime START_DATE_TIME = LocalDateTime.of(2050, 1, 1, 0, 0);
    public static final LocalDateTime END_DATE_TIME = LocalDateTime.of(2050, 7, 17, 0, 0);
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    private NumberGenerator numberGenerator;
    private PassListFactory passListFactory;

    public OrderFactory(NumberGenerator numberGenerator, PassListFactory passListFactory) {
        this.numberGenerator = numberGenerator;
        this.passListFactory = passListFactory;
    }

    public Order buildWithDto(OrderWithPassesAsEventDatesDto orderDto) {
        if (orderDto.getPasses() == null) {
            throw new InvalidOrderFormatException();
        }


        OrderNumber orderNumber = new OrderNumber(numberGenerator.generate(), orderDto.getVendorCode());
        LocalDateTime orderDate = buildOrderDate(orderDto.getOrderDate());
        PassList passList = passListFactory.parseDto(orderDto.getPasses());

        return new Order(orderNumber, orderDate, passList);
    }

    private LocalDateTime buildOrderDate(String sentOrderDate) {
        LocalDateTime orderDate = parseLocalDateTime(sentOrderDate);

        validateOrderDate(orderDate);

        return orderDate;
    }

    private LocalDateTime parseLocalDateTime(String orderDate) {
        try {
            return ZonedDateTime.parse(orderDate, DATE_TIME_FORMATTER).toLocalDateTime();
        } catch (Exception exception) {
            throw new InvalidOrderDateFormatException();
        }
    }

    private void validateOrderDate(LocalDateTime orderDate) {
        if (orderDate.isBefore(START_DATE_TIME) || orderDate.isAfter(END_DATE_TIME)) {
            throw new OutOfBoundsOrderDateException();
        }
    }
}
