package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.builders.VendorBuilder;
import ca.ulaval.glo4002.booking.constants.PassConstants;
import ca.ulaval.glo4002.booking.domainobjects.orders.Order;
import ca.ulaval.glo4002.booking.domainobjects.orders.OrderItem;
import ca.ulaval.glo4002.booking.domainobjects.passes.Pass;
import ca.ulaval.glo4002.booking.domainobjects.vendors.Vendor;
import ca.ulaval.glo4002.booking.dto.OrderDto;
import ca.ulaval.glo4002.booking.dto.PassesDto;
import ca.ulaval.glo4002.booking.entities.OrderEntity;
import ca.ulaval.glo4002.booking.entities.OrderItemEntity;
import ca.ulaval.glo4002.booking.exceptions.dates.InvalidDateTimeException;
import ca.ulaval.glo4002.booking.exceptions.orders.OrderDtoInvalidException;
import ca.ulaval.glo4002.booking.util.FestivalDateUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderParser implements EntityParser<Order, OrderEntity>, DtoParser<Order, OrderDto> {

    private VendorBuilder vendorBuilder = new VendorBuilder();
    private OrderItemParser orderItemParser = new OrderItemParser();
    private PassParser passParser = new PassParser();

    @Override
    public Order parseDto(OrderDto dto) {
        Vendor vendor = vendorBuilder.buildByCode(dto.vendorCode);

        LocalDateTime orderDate = parseOrderDate(dto.orderDate);
        validateOrderDate(orderDate);

        List<Pass> passes = passParser.parseDto(dto.passes);

        return new Order(dto.orderNumber, orderDate, vendor, passes);
    }

    @Override
    public Order parseEntity(OrderEntity entity) {
        Vendor vendor = vendorBuilder.buildById(entity.getVendorId());
        List<OrderItem> orderItems = orderItemParser.parseEntity(entity.getOrderItems());

        return new Order(entity.getId(), entity.getOrderDate(), vendor, orderItems);
    }

    @Override
    public OrderDto toDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.orderNumber = order.getId();
        dto.orderDate = FestivalDateUtil.toZonedDateTimeString(order.getOrderDate());
        dto.vendorCode = order.getVendor().getCode();

        List<Pass> passes = new ArrayList<>();
        order.getOrderItems().forEach(orderItem -> {
            if (orderItem instanceof Pass) {
                passes.add((Pass) orderItem);
            }
        });

        PassesDto passesDto = new PassesDto();
        passesDto.passNumber = passes.get(0).getId();
        passesDto.passCategory = passes.get(0).getCategory().getName();
        passesDto.passOption = passes.get(0).getOption().getName();

        if (passes.get(0).getOption().getId().equals(PassConstants.Options.SINGLE_ID)) {
            passesDto.eventDates = new ArrayList<>();
            passes.forEach(pass -> {
                if (pass.getEventDate() != null) {
                    passesDto.eventDates.add(pass.getEventDate().toString());
                }
            });
        }

        dto.passes = passesDto;

        return dto;
    }

    @Override
    public OrderEntity toEntity(Order order) {
        List<OrderItemEntity> orderItems = orderItemParser.toEntity(order.getOrderItems());

        OrderEntity orderEntity = new OrderEntity(
                order.getId(),
                order.getOrderDate(),
                order.getVendor().getId(),
                orderItems
        );

        orderItems.forEach(orderItem -> orderItem.setOrder(orderEntity));

        return orderEntity;
    }

    private LocalDateTime parseOrderDate(String orderDate) {
        try {
            return FestivalDateUtil.toLocalDateTime(orderDate);
        } catch(InvalidDateTimeException exception) {
            throw new OrderDtoInvalidException();
        }
    }

    private void validateOrderDate(LocalDateTime orderDate){
        if (FestivalDateUtil.isOutsideOrderDates(orderDate)){
            throw new OrderDtoInvalidException();
        }
    }
}
