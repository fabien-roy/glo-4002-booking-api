package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.builders.shuttles.ShuttleCategoryBuilder;
import ca.ulaval.glo4002.booking.constants.ShuttleConstants;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.categories.ShuttleCategory;
import ca.ulaval.glo4002.booking.entities.PassengerEntity;
import ca.ulaval.glo4002.booking.entities.ShuttleEntity;
import ca.ulaval.glo4002.booking.entities.TripEntity;
import ca.ulaval.glo4002.booking.parsers.ShuttleParser;
import ca.ulaval.glo4002.booking.repositories.PassengerRepository;
import ca.ulaval.glo4002.booking.repositories.ShuttleRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShuttleServiceContext {

    private static final ShuttleCategoryBuilder shuttleCategoryBuilder = new ShuttleCategoryBuilder();
    private static final ShuttleCategory A_SHUTTLE_CATEGORY = shuttleCategoryBuilder.buildById(ShuttleConstants.Categories.ET_SPACESHIP_ID);
    private static final ShuttleCategory ANOTHER_SHUTTLE_CATEGORY = shuttleCategoryBuilder.buildById(ShuttleConstants.Categories.MILLENNIUM_FALCON_ID);

    private ShuttleParser parser = new ShuttleParser();
    private ShuttleEntity aShuttleEntity;
    private ShuttleEntity anotherShuttleEntity;
    private ShuttleEntity anotherQualityShuttleEntity;
    private ShuttleEntity aNonExistentShuttleEntity;

    public ShuttleRepository repository;
    public PassengerRepository passengerRepository;
    public Shuttle aShuttle;
    public Shuttle anotherShuttle;
    public Shuttle anotherQualityShuttle;
    public Shuttle aNonExistentShuttle;
    public static final Long A_SHUTTLE_ID = 0L;
    public static final Long ANOTHER_SHUTTLE_ID = 1L;
    public static final Long A_NON_EXISTENT_SHUTTLE_ID = 3L;
    public static final Long ANOTHER_QUALITY_SHUTTLE_ID = 4L;

    public ShuttleServiceContext() {
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

        aNonExistentShuttle = new Shuttle(
                A_NON_EXISTENT_SHUTTLE_ID,
                A_SHUTTLE_CATEGORY.getPrice(),
                A_SHUTTLE_CATEGORY,
                new ArrayList<>()
        );

        anotherQualityShuttle = new Shuttle(
                ANOTHER_QUALITY_SHUTTLE_ID,
                A_SHUTTLE_CATEGORY.getPrice(),
                ANOTHER_SHUTTLE_CATEGORY,
                new ArrayList<>()
        );

        aShuttleEntity = parser.toEntity(aShuttle);
        anotherShuttleEntity = parser.toEntity(anotherShuttle);
        anotherQualityShuttleEntity = parser.toEntity(anotherQualityShuttle);
        aNonExistentShuttleEntity = parser.toEntity(aNonExistentShuttle);
    }

    private void setUpRepository() {
        repository = mock(ShuttleRepository.class);

        when(repository.findAll()).thenReturn(Arrays.asList(aShuttleEntity, anotherShuttleEntity, anotherQualityShuttleEntity));
        when(repository.findById(A_SHUTTLE_ID)).thenReturn(Optional.of(aShuttleEntity));
        when(repository.findById(ANOTHER_SHUTTLE_ID)).thenReturn(Optional.of(anotherShuttleEntity));
        when(repository.findById(A_NON_EXISTENT_SHUTTLE_ID)).thenReturn(Optional.empty());
    }

    public void setUpWithFullTrip() {
        aShuttleEntity.getTrips().add(getTripWithPassengers(aShuttle.getShuttleCategory().getMaxCapacity()));
        aShuttleEntity = parser.toEntity(aShuttle);
    }

    public void setUpWithAlmostFullTrip() {
        aShuttleEntity.getTrips().add(getTripWithPassengers(aShuttle.getShuttleCategory().getMaxCapacity() - 1));
        aShuttleEntity = parser.toEntity(aShuttle);
    }

    public TripEntity getTripWithPassengers(Integer quantity) {
        TripEntity someTrips = new TripEntity();

        for (int i = 0; i < quantity; i++) {
            someTrips.getPassengers().add(new PassengerEntity());
        }

        return someTrips;
    }
}
