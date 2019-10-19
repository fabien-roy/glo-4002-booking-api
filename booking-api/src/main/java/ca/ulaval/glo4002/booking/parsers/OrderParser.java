package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.domain.Id;
import ca.ulaval.glo4002.booking.domain.Order;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsPassesDto;

public class OrderParser {

    // TODO
    public OrderWithPassesAsPassesDto toDto(Order order) {
        return new OrderWithPassesAsPassesDto();
    }

    // TODO
    public Order parseDto(OrderWithPassesAsEventDatesDto orderDto) {
        return null;
    }

    // TODO
    public Id parseIdFromOrderNumber(String orderNumber) {
        return null;
    }
}
