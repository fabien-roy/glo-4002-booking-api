package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.builders.VendorBuilder;
import ca.ulaval.glo4002.booking.constants.FestivalConstants;
import ca.ulaval.glo4002.booking.dto.OrderDto;
import ca.ulaval.glo4002.booking.domainObjects.orders.Order;
import ca.ulaval.glo4002.booking.entities.OrderEntity;
import ca.ulaval.glo4002.booking.domainObjects.vendors.Vendor;
import ca.ulaval.glo4002.booking.exceptions.orders.OrderDtoInvalidException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderParser implements Parser<Order, OrderDto, OrderEntity> {

    private VendorBuilder vendorBuilder = new VendorBuilder();
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(FestivalConstants.Dates.DATE_TIME_FORMAT);

    @Override
    public Order parseDto(OrderDto dto) {
        Vendor vendor = vendorBuilder.buildByCode(dto.vendorCode);

        validateOrderDate(dto.orderDate);

        return new Order(dto.orderNumber, dto.orderDate, vendor);
    }

    @Override
    public Order parseEntity(OrderEntity entity) {
        LocalDateTime orderDate = LocalDateTime.parse(entity.orderDate, dateTimeFormatter);

        Vendor vendor = vendorBuilder.buildById(entity.vendorId);

        validateOrderDate(orderDate);

        return new Order(entity.id, orderDate, vendor);
    }

    @Override
    public OrderDto toDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.orderNumber = order.getId();
        dto.orderDate = order.getOrderDate();
        dto.vendorCode = order.getVendor().getCode();
        // TODO : dto.passes

        return dto;
    }

    @Override
    public OrderEntity toEntity(Order order) {
        return new OrderEntity(
                order.getId(),
                order.getOrderDate().toString(),
                order.getVendor().getId()
        );
    }

    // TODO : Use FestivalDateChecker from TRANS
    private void validateOrderDate(LocalDateTime orderDate){
        if(orderDate.isBefore(FestivalConstants.Dates.ORDER_START_DATE_TIME)
           || orderDate.isAfter(FestivalConstants.Dates.ORDER_END_DATE_TIME)){
            throw new OrderDtoInvalidException();
        }
    }
}
