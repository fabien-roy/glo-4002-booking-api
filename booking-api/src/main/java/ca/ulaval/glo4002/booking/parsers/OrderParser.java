package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.builders.VendorBuilder;
import ca.ulaval.glo4002.booking.domainobjects.orders.Order;
import ca.ulaval.glo4002.booking.domainobjects.orders.OrderItem;
import ca.ulaval.glo4002.booking.domainobjects.passes.Pass;
import ca.ulaval.glo4002.booking.domainobjects.vendors.Vendor;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsPassesDto;
import ca.ulaval.glo4002.booking.entities.OrderEntity;
import ca.ulaval.glo4002.booking.entities.OrderItemEntity;
import ca.ulaval.glo4002.booking.exceptions.dates.InvalidDateTimeException;
import ca.ulaval.glo4002.booking.exceptions.orders.OrderInvalidFormatException;
import ca.ulaval.glo4002.booking.util.FestivalDateUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class OrderParser implements EntityParser<Order, OrderEntity>, ParseDtoParser<Order, OrderWithPassesAsEventDatesDto>, ToDtoParser<Order, OrderWithPassesAsPassesDto> {

    private VendorBuilder vendorBuilder = new VendorBuilder();
    private OrderItemParser orderItemParser = new OrderItemParser();
    private PassParser passParser = new PassParser();

    @Override
    public Order parseDto(OrderWithPassesAsEventDatesDto dto) {
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
    public OrderWithPassesAsPassesDto toDto(Order order) {
        OrderWithPassesAsPassesDto dto = new OrderWithPassesAsPassesDto();
        dto.orderNumber = order.getId();
        dto.orderDate = FestivalDateUtil.toZonedDateTimeString(order.getOrderDate());
        dto.vendorCode = order.getVendor().getCode();
        dto.passes = passParser.toDto(getPasses(order.getOrderItems()));

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

    private List<Pass> getPasses(List<? extends OrderItem> orderItems) {
        return (List<Pass>) orderItems.stream().filter(orderItem -> orderItem instanceof Pass).collect(Collectors.toList());
    }

    private LocalDateTime parseOrderDate(String orderDate) {
        try {
            return FestivalDateUtil.toLocalDateTime(orderDate);
        } catch(InvalidDateTimeException exception) {
            throw new OrderInvalidFormatException();
        }
    }

    private void validateOrderDate(LocalDateTime orderDate){
        if (FestivalDateUtil.isOutsideOrderDates(orderDate)){
            throw new OrderInvalidFormatException();
        }
    }
}
