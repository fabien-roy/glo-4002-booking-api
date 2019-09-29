package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.builders.VendorBuilder;
import ca.ulaval.glo4002.booking.constants.FestivalConstants;
import ca.ulaval.glo4002.booking.dto.OrderDto;
import ca.ulaval.glo4002.booking.entities.orders.Order;
import ca.ulaval.glo4002.booking.exceptions.orders.OrderDtoInvalidException;

import java.time.LocalDateTime;

class OrderParser {

    public Order parse(OrderDto orderDto) {
        Order order = new Order();
        VendorBuilder vendorBuilder = new VendorBuilder();

        checkOrderDate(orderDto.orderDate);

        order.setOrderDate(orderDto.orderDate);
        order.setVendor(vendorBuilder.buildByCode(orderDto.vendorCode));

        return order;
    }

    private void checkOrderDate(LocalDateTime orderDate){
        if(orderDate.isBefore(FestivalConstants.Dates.ORDER_START_DATE_TIME)
           || orderDate.isAfter(FestivalConstants.Dates.ORDER_END_DATE_TIME)){
            throw new OrderDtoInvalidException();
        }
    }
}
