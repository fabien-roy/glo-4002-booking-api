package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.builders.VendorBuilder;
import ca.ulaval.glo4002.booking.constants.FestivalConstants;
import ca.ulaval.glo4002.booking.dto.OrderDto;
import ca.ulaval.glo4002.booking.domainObjects.orders.Order;
import ca.ulaval.glo4002.booking.entities.OrderEntity;
import ca.ulaval.glo4002.booking.domainObjects.vendors.Vendor;
import ca.ulaval.glo4002.booking.exceptions.orders.OrderDtoInvalidException;

import java.time.LocalDateTime;

public class OrderParser implements Parser<Order, OrderDto, OrderEntity> {

    @Override
    public Order parseDto(OrderDto dto) {
        VendorBuilder vendorBuilder = new VendorBuilder();

        Vendor vendor = vendorBuilder.buildByCode(dto.vendorCode);

        validateOrderDate(dto.orderDate);

        return new Order(dto.orderNumber, dto.orderDate, vendor);
    }

    @Override
    public Order parseEntity(OrderEntity entity) {
        // TODO
        return null;
    }

    @Override
    public OrderDto toDto(Order object) {
        // TODO : toDto
        return null;
    }

    @Override
    public OrderEntity toEntity(Order object) {
        // TODO : toEntity
        return null;
    }

    // TODO : Use FestivalDateChecker from TRANS
    private void validateOrderDate(LocalDateTime orderDate){
        if(orderDate.isBefore(FestivalConstants.Dates.ORDER_START_DATE_TIME)
           || orderDate.isAfter(FestivalConstants.Dates.ORDER_END_DATE_TIME)){
            throw new OrderDtoInvalidException();
        }
    }
}
