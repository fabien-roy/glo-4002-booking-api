package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.builders.VendorBuilder;
import ca.ulaval.glo4002.booking.constants.FestivalConstants;
import ca.ulaval.glo4002.booking.dto.OrderDto;
import ca.ulaval.glo4002.booking.entities.orders.Order;
import ca.ulaval.glo4002.booking.exceptions.orders.OrderDtoInvalidException;

import java.time.LocalDateTime;


class OrderParser {

    private Order order;

    Order parse(OrderDto orderDto) {
        order = new Order();
        VendorBuilder vendorBuilder = new VendorBuilder();

        checkOrderDate(orderDto.getOrderDate());
        order.setOrderDate(orderDto.getOrderDate());
        order.setVendor(vendorBuilder.buildByVendorCode(orderDto.getVendorCode()));

        return order;
    }

    Order getOrder() {
        return this.order;
    }

    private void checkOrderDate(LocalDateTime orderDate){
        if(orderDate.isBefore(FestivalConstants.Dates.FESTIVAL_START_ORDER) ||
                orderDate.isAfter(FestivalConstants.Dates.FESTIVAL_END_ORDER)){
            throw new OrderDtoInvalidException();
        }
    }
}
