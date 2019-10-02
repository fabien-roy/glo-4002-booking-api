package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.domainobjects.orders.OrderItem;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domainobjects.passes.Pass;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.entities.OrderItemEntity;
import ca.ulaval.glo4002.booking.entities.OxygenTankEntity;
import ca.ulaval.glo4002.booking.entities.PassEntity;
import ca.ulaval.glo4002.booking.entities.ShuttleEntity;

import java.util.ArrayList;
import java.util.List;

public class OrderItemParser implements EntityParser<List<? extends OrderItem>, List<? extends OrderItemEntity>> {

    private PassParser passParser = new PassParser();
    private OxygenTankParser oxygenTankParser = new OxygenTankParser();
    private ShuttleParser shuttleParser = new ShuttleParser();

    // TODO : Test
    @Override
    public List<OrderItem> parseEntity(List<? extends OrderItemEntity> orderItemEntities) {
        List<OrderItem> orderItems = new ArrayList<>();

        orderItemEntities.forEach(orderItem -> {
            if (orderItem instanceof PassEntity) {
                orderItems.add(passParser.parseEntity((PassEntity) orderItem));
            } else if (orderItem instanceof OxygenTankEntity) {
                orderItems.add(oxygenTankParser.parseEntity((OxygenTankEntity) orderItem));
            } else if (orderItem instanceof ShuttleEntity) {
                orderItems.add(shuttleParser.parseEntity((ShuttleEntity) orderItem));
            }
        });

        return orderItems;
    }

    // TODO : Test
    @Override
    public List<OrderItemEntity> toEntity(List<? extends OrderItem> orderItems) {
        List<OrderItemEntity> orderItemEntities = new ArrayList<>();

        orderItems.forEach(orderItem -> {
            if (orderItem instanceof Pass) {
                orderItemEntities.add(passParser.toEntity((Pass) orderItem));
            } else if (orderItem instanceof OxygenTank) {
                orderItemEntities.add(oxygenTankParser.toEntity((OxygenTank) orderItem));
            } else if (orderItem instanceof Shuttle) {
                orderItemEntities.add(shuttleParser.toEntity((Shuttle) orderItem));
            }
        });

        return orderItemEntities;
    }
}
