package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.builders.shuttles.ShuttleCategoryBuilder;
import ca.ulaval.glo4002.booking.constants.DateConstants;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.entities.ShuttleEntity;
import ca.ulaval.glo4002.booking.entities.ShuttleInventoryEntity;
import ca.ulaval.glo4002.booking.parsers.ShuttleParser;
import ca.ulaval.glo4002.booking.repositories.ShuttleRepository;
import ca.ulaval.glo4002.booking.repositories.ShuttleRepositoryImpl;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class ShuttleServiceContext {
    private static final Long A_SHUTTLE_ID = 0L;
    private static final Long ANOTHER_SHUTTLE_ID = 1L;
    private static final Long A_SHUTTLE_CATEGORY_ID = 0L;
    private static final Long ANOTHER_SHUTTLE_CATEGORY_ID = 1L;
    private static final LocalDate A_SHUTTLE_DATE = DateConstants.START_DATE;
    private static final Long A_SHUTTLE_INVENTORY_ID = 1L;
    private ShuttleRepository shuttleRepository;
    private ShuttleCategoryBuilder shuttleCategoryBuilder = new ShuttleCategoryBuilder();
    private ShuttleParser shuttleParser = new ShuttleParser();

    PassengerService passengerService;
    Shuttle aShuttle;
    Shuttle anotherShuttle;
    Shuttle aShuttleWithoutID;
    ShuttleEntity aShuttleEntity;
    ShuttleInventoryEntity aShuttleInventoryEntity;
    ShuttleServiceImpl subject;
    List<Shuttle> arrivalShuttles = new ArrayList<>();
    List<Shuttle> departureShuttles = new ArrayList<>();
    List<Shuttle> allShuttles = new ArrayList<>();
    List<ShuttleEntity> allShuttlesEntities = new ArrayList<>();

    ShuttleServiceContext(){
        setUpObjects();
        setUpRepository();
        setUpPassengerService();
        this.subject = new ShuttleServiceImpl(shuttleRepository, passengerService);
    }

    void setUpForFindAll(){
        arrivalShuttles.add(aShuttle);
        departureShuttles.add(anotherShuttle);

        allShuttles.addAll(arrivalShuttles);
        allShuttles.addAll(departureShuttles);

        arrivalShuttles.forEach(shuttle -> allShuttlesEntities.add(shuttleParser.toEntity(shuttle)));
        departureShuttles.forEach(shuttle -> allShuttlesEntities.add(shuttleParser.toEntity(shuttle)));
    }

    void setUpForOrder() {

    }

    private void setUpRepository(){
        shuttleRepository = mock(ShuttleRepositoryImpl.class);
        Mockito.doReturn(allShuttlesEntities).when(shuttleRepository).findAll();
        Mockito.doReturn(shuttleParser.toEntity(aShuttle)).when(shuttleRepository).save(any());
    }

    private void setUpPassengerService(){
        passengerService = mock(PassengerServiceImpl.class);
    }

    private void setUpObjects() {
        aShuttle = new Shuttle(
                A_SHUTTLE_ID,
                shuttleCategoryBuilder.buildById(A_SHUTTLE_CATEGORY_ID),
                A_SHUTTLE_DATE,
                new ArrayList<>()
        );

        anotherShuttle = new Shuttle(
                ANOTHER_SHUTTLE_ID,
                shuttleCategoryBuilder.buildById(ANOTHER_SHUTTLE_CATEGORY_ID),
                A_SHUTTLE_DATE,
                new ArrayList<>()
        );

        aShuttleWithoutID = new Shuttle(
                null,
                shuttleCategoryBuilder.buildById(A_SHUTTLE_CATEGORY_ID),
                A_SHUTTLE_DATE,
                new ArrayList<>()
        );

        aShuttleInventoryEntity = new ShuttleInventoryEntity(
                A_SHUTTLE_INVENTORY_ID,
                new ArrayList<>(),
                new ArrayList<>()
        );

        aShuttleEntity = shuttleParser.toEntity(aShuttle);
    }
}
