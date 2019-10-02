package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.builders.VendorBuilder;
import ca.ulaval.glo4002.booking.domainobjects.orders.Order;
import ca.ulaval.glo4002.booking.domainobjects.passes.Pass;
import ca.ulaval.glo4002.booking.domainobjects.vendors.Vendor;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsPassesDto;
import ca.ulaval.glo4002.booking.entities.OrderEntity;
import ca.ulaval.glo4002.booking.entities.PassEntity;
import ca.ulaval.glo4002.booking.exceptions.dates.InvalidDateTimeException;
import ca.ulaval.glo4002.booking.exceptions.orders.OrderInvalidDateException;
import ca.ulaval.glo4002.booking.exceptions.orders.OrderInvalidFormatException;
import ca.ulaval.glo4002.booking.util.FestivalDateUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderParser implements EntityParser<Order, OrderEntity>, ParseDtoParser<Order, OrderWithPassesAsEventDatesDto>, ToDtoParser<Order, OrderWithPassesAsPassesDto> {

    private VendorBuilder vendorBuilder = new VendorBuilder();
    private PassParser passParser = new PassParser();

    @Override
    public Order parseDto(OrderWithPassesAsEventDatesDto dto) {
        Vendor vendor = vendorBuilder.buildByCode(dto.vendorCode);

        LocalDateTime orderDate = parseOrderDate(dto.orderDate);
        validateOrderDate(orderDate);

        List<Pass> passes = passParser.parseDto(dto.passes);

        try {
            return new Order(dto.orderNumber, orderDate, vendor, passes);
        } catch (Exception exception) {
            throw new OrderInvalidFormatException();
        }
    }

    @Override
    public Order parseEntity(OrderEntity order) {
        Vendor vendor = vendorBuilder.buildById(order.getVendorId());
        List<Pass> passes = new ArrayList<>();
        order.getPasses().forEach(pass -> passes.add(passParser.parseEntity(pass)));

        return new Order(order.getId(), order.getOrderDate(), vendor, passes);
    }

    @Override
    public OrderWithPassesAsPassesDto toDto(Order order) {
        OrderWithPassesAsPassesDto dto = new OrderWithPassesAsPassesDto();
        dto.orderNumber = order.getId();
        dto.orderDate = FestivalDateUtil.toZonedDateTimeString(order.getOrderDate());
        dto.vendorCode = order.getVendor().getCode();
        dto.passes = passParser.toDto(order.getPasses());

        return dto;
    }

    @Override
    public OrderEntity toEntity(Order order) {
        List<PassEntity> passeEntities = new ArrayList<>();
        order.getPasses().forEach(pass -> passeEntities.add(passParser.toEntity(pass)));

        OrderEntity orderEntity = new OrderEntity(
                order.getId(),
                order.getOrderDate(),
                order.getVendor().getId(),
                passeEntities,
                order.getPrice()
        );

        passeEntities.forEach(pass -> pass.setOrder(orderEntity));

        return orderEntity;
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
            throw new OrderInvalidDateException();
        }
    }
}
