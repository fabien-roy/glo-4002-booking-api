package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.builders.VendorBuilder;
import ca.ulaval.glo4002.booking.constants.FestivalConstants;
import ca.ulaval.glo4002.booking.dto.OrderDto;
import ca.ulaval.glo4002.booking.entities.orders.Order;
import ca.ulaval.glo4002.booking.entities.vendors.Vendor;
import ca.ulaval.glo4002.booking.exceptions.orders.OrderDtoInvalidException;

import java.time.LocalDateTime;

public class OrderParser implements Parser<Order, OrderDto> {

    @Override
    public Order parse(OrderDto dto) {
        VendorBuilder vendorBuilder = new VendorBuilder();

        Vendor vendor = vendorBuilder.buildByCode(dto.vendorCode);

        validateOrderDate(dto.orderDate);

        return new Order(dto.orderNumber, dto.orderDate, vendor);
    }

    // TODO : Use FestivalDateChecker from TRANS
    private void validateOrderDate(LocalDateTime orderDate){
        if(orderDate.isBefore(FestivalConstants.Dates.ORDER_START_DATE_TIME)
           || orderDate.isAfter(FestivalConstants.Dates.ORDER_END_DATE_TIME)){
            throw new OrderDtoInvalidException();
        }
    }
}
