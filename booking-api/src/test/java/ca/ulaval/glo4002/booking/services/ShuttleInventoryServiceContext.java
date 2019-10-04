package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.builders.shuttles.ShuttleCategoryBuilder;
import ca.ulaval.glo4002.booking.constants.DateConstants;
import ca.ulaval.glo4002.booking.constants.ShuttleConstants;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.Passenger;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.ShuttleInventory;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.categories.ShuttleCategory;
import ca.ulaval.glo4002.booking.entities.ShuttleEntity;
import ca.ulaval.glo4002.booking.entities.ShuttleInventoryEntity;
import ca.ulaval.glo4002.booking.parsers.ShuttleInventoryParser;
import ca.ulaval.glo4002.booking.parsers.ShuttleParser;
import ca.ulaval.glo4002.booking.repositories.ShuttleInventoryRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShuttleInventoryServiceContext {

    public static final LocalDate AN_ARRIVAL_DATE = DateConstants.START_DATE;
    public static final LocalDate A_DEPARTURE_DATE = DateConstants.END_DATE;
    public static final LocalDate ANOTHER_DEPARTURE_DATE = DateConstants.END_DATE.minusDays(1);
    private static final ShuttleCategoryBuilder shuttleCategoryBuilder = new ShuttleCategoryBuilder();
    private static final ShuttleCategory A_SHUTTLE_CATEGORY = shuttleCategoryBuilder.buildById(ShuttleConstants.Categories.ET_SPACESHIP_ID);
    private static final ShuttleCategory ANOTHER_SHUTTLE_CATEGORY = shuttleCategoryBuilder.buildById(ShuttleConstants.Categories.MILLENNIUM_FALCON_ID);

    private ShuttleInventoryParser parser = new ShuttleInventoryParser();
    private ShuttleParser shuttleParser = new ShuttleParser();
    private ShuttleInventoryEntity aInventoryShuttleEntity;
    private ShuttleEntity aShuttleEntity;

    public ShuttleInventoryRepository repository;
    public Shuttle aShuttle;
    public Shuttle anotherShuttle;
    public Shuttle anotherQualityShuttle;
    public Shuttle oneMoreShuttle;
    public static final Long A_SHUTTLE_ID = 0L;
    public static final Long ANOTHER_SHUTTLE_ID = 1L;
    public static final Long ONE_MORE_SHUTTLE_ID = 3L;
    public static final Long ANOTHER_QUALITY_SHUTTLE_ID = 4L;

    public ShuttleInventoryServiceContext() {
        setUpObjects();
        setUpRepository();
    }

    private void setUpObjects() {
        aShuttle = new Shuttle(
                A_SHUTTLE_ID,
                A_SHUTTLE_CATEGORY.getPrice(),
                A_SHUTTLE_CATEGORY,
                new ArrayList<>()
                );

        anotherShuttle = new Shuttle(
                ANOTHER_SHUTTLE_ID,
                A_SHUTTLE_CATEGORY.getPrice(),
                A_SHUTTLE_CATEGORY,
                new ArrayList<>()
        );

        oneMoreShuttle = new Shuttle(
                ONE_MORE_SHUTTLE_ID,
                A_SHUTTLE_CATEGORY.getPrice(),
                A_SHUTTLE_CATEGORY,
                new ArrayList<>()
        );

        aShuttleEntity = shuttleParser.toEntity(aShuttle);

        setUpInventory();
    }

    private void setUpInventory() {
        ShuttleInventory aInventoryShuttle = new ShuttleInventory();
        aInventoryShuttle.addArrivalShuttle(AN_ARRIVAL_DATE, aShuttle);
        aInventoryShuttle.addArrivalShuttle(AN_ARRIVAL_DATE, anotherShuttle);
        aInventoryShuttle.addArrivalShuttle(AN_ARRIVAL_DATE, anotherShuttle);
        aInventoryShuttle.addDepartureShuttle(A_DEPARTURE_DATE, aShuttle);
        aInventoryShuttle.addDepartureShuttle(A_DEPARTURE_DATE, aShuttle);
        aInventoryShuttle.addDepartureShuttle(ANOTHER_DEPARTURE_DATE, aShuttle);

        aInventoryShuttleEntity = parser.toEntity(aInventoryShuttle);
    }

    private void setUpRepository() {
        repository = mock(ShuttleInventoryRepository.class);

        when(repository.findAll()).thenReturn(Collections.singletonList(aInventoryShuttleEntity));
    }

    public void setUpWithFullTrip() {
        aShuttle.setPassengers(getPassengers(aShuttle.getShuttleCategory().getMaxCapacity()));
        setUpInventory();
    }

    public void setUpWithAlmostFullTrip() {
        aShuttle.setPassengers(getPassengers(aShuttle.getShuttleCategory().getMaxCapacity() - 1));
        setUpInventory();
    }

    public List<Passenger> getPassengers(Integer quantity) {
        List<Passenger> passengers = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            passengers.add(new Passenger());
        }

        return passengers;
    }
}
