package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.builders.shuttles.ShuttleCategoryBuilder;
import ca.ulaval.glo4002.booking.constants.ShuttleConstants;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.categories.ShuttleCategory;
import ca.ulaval.glo4002.booking.domainobjects.trips.Trip;
import ca.ulaval.glo4002.booking.entities.ShuttleEntity;
import ca.ulaval.glo4002.booking.parsers.ShuttleParser;
import ca.ulaval.glo4002.booking.repositories.PassengerRepository;
import ca.ulaval.glo4002.booking.repositories.ShuttleRepository;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShuttleServiceContext {

    private ShuttleParser parser = new ShuttleParser();
    private ShuttleEntity aShuttleEntity;
    private ShuttleEntity anotherShuttleEntity;
    private ShuttleEntity aNonExistentShuttleEntity;
    private final static ShuttleCategoryBuilder shuttleCategoryBuilder = new ShuttleCategoryBuilder();
    public ShuttleRepository repository;
    public PassengerRepository passengerRepository;
    public Shuttle aShuttle;
    public Shuttle anotherShuttle;
    public Shuttle aNonExistentShuttle;
    public ShuttleCategory A_SHUTTLE_CATEGORY = shuttleCategoryBuilder.buildById(ShuttleConstants.Categories.ET_SPACESHIP_ID);
    public final static Long A_SHUTTLE_ID = 1L;
    public final static Long ANOTHER_SHUTTLE_ID = 2L;
    public final static Long A_NON_EXISTENT_SHUTTLE_ID = 0L;
    public List<Trip> A_TRIP_LIST = new ArrayList<Trip>();
    public List<Trip> ANOTHER_TRIP_LIST = new ArrayList<Trip>();


    public ShuttleServiceContext() {
        setUpObjects();
        setUpRepository();
    }

    private void setUpObjects() {
        aShuttle = new Shuttle(
                A_SHUTTLE_ID,
                A_SHUTTLE_CATEGORY.getPrice(),
                A_SHUTTLE_CATEGORY,
                A_TRIP_LIST
                );

        anotherShuttle = new Shuttle(
                ANOTHER_SHUTTLE_ID,
                A_SHUTTLE_CATEGORY.getPrice(),
                A_SHUTTLE_CATEGORY,
                ANOTHER_TRIP_LIST
        );

        aNonExistentShuttle = new Shuttle(
                A_NON_EXISTENT_SHUTTLE_ID,
                A_SHUTTLE_CATEGORY.getPrice(),
                A_SHUTTLE_CATEGORY,
                new ArrayList<>()
        );

        aShuttleEntity = parser.toEntity(aShuttle);
        anotherShuttleEntity = parser.toEntity(anotherShuttle);
        aNonExistentShuttleEntity = parser.toEntity(aNonExistentShuttle);
    }

    private void setUpRepository() {
        repository = mock(ShuttleRepository.class);

        when(repository.findAll()).thenReturn(Arrays.asList(aShuttleEntity, anotherShuttleEntity));
        when(repository.findById(A_SHUTTLE_ID)).thenReturn(Optional.of(aShuttleEntity));
        when(repository.findById(ANOTHER_SHUTTLE_ID)).thenReturn(Optional.of(anotherShuttleEntity));
        when(repository.findById(A_NON_EXISTENT_SHUTTLE_ID)).thenReturn(Optional.empty());
    }
}
