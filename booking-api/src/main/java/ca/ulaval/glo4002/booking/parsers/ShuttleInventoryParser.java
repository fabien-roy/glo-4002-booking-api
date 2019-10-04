package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.domainobjects.shuttles.ShuttleInventory;
import ca.ulaval.glo4002.booking.entities.ShuttleEntity;
import ca.ulaval.glo4002.booking.entities.ShuttleInventoryEntity;

import java.util.ArrayList;
import java.util.List;

public class ShuttleInventoryParser implements EntityParser<ShuttleInventory, ShuttleInventoryEntity> {

    ShuttleParser shuttleParser = new ShuttleParser();

    // TODO : TRANS : ShuttleInventoryParser.parseEntity tests
    @Override
    public ShuttleInventory parseEntity(ShuttleInventoryEntity entity) {
        ShuttleInventory inventory = new ShuttleInventory();

        entity.getArrivalShuttles().forEach(shuttle -> inventory.addArrivalShuttle(shuttle.getDate(), shuttleParser.parseEntity(shuttle)));
        entity.getDepartureShuttles().forEach(shuttle -> inventory.addDepartureShuttle(shuttle.getDate(), shuttleParser.parseEntity(shuttle)));

        return inventory;
    }

    // TODO : TRANS : ShuttleInventoryParser.toEntity tests
    @Override
    public ShuttleInventoryEntity toEntity(ShuttleInventory inventory) {
        List<ShuttleEntity> arrivalShuttleEntities = new ArrayList<>();
        List<ShuttleEntity> departureShuttleEntities = new ArrayList<>();

        inventory.getArrivalShuttles().forEach((categoryId, shuttlesByDate) ->
                shuttlesByDate.forEach((date, shuttles) ->
                        shuttles.forEach(shuttle ->
                                arrivalShuttleEntities.add(shuttleParser.toEntity(shuttle)
                                )
                        )
                )
        );

        inventory.getDepartureShuttles().forEach((categoryId, shuttlesByDate) ->
                shuttlesByDate.forEach((date, shuttles) ->
                        shuttles.forEach(shuttle ->
                                departureShuttleEntities.add(shuttleParser.toEntity(shuttle)
                                )
                        )
                )
        );

        return new ShuttleInventoryEntity(inventory.getId(), arrivalShuttleEntities, departureShuttleEntities);
    }
}
