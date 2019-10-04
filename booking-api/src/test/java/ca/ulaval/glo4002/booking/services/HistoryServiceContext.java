package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.builders.oxygen.OxygenCategoryBuilder;
import ca.ulaval.glo4002.booking.constants.DateConstants;
import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTank;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HistoryServiceContext {

    public OxygenTankService oxygenTankService;

    private final static Long A_OXYGEN_TANK_ID = 0L;
    private final static Long ANOTHER_OXYGEN_TANK_ID = 1L;
    private static final LocalDate A_REQUEST_DATE = DateConstants.START_DATE.minusDays(30);
    private static final LocalDate ANOTHER_REQUEST_DATE = DateConstants.START_DATE.minusDays(20);
    private static final LocalDate A_READY_DATE = A_REQUEST_DATE.plusDays(10);
    private static final LocalDate ANOTHER_READY_DATE = ANOTHER_REQUEST_DATE.plusDays(15);

    private OxygenCategoryBuilder oxygenCategoryBuilder = new OxygenCategoryBuilder();
    public OxygenTank anOxygenTank;
    public OxygenTank anotherOxygenTank;

    public HistoryServiceContext() {
        setUpOxygenTanks();
        setUpRepository();
    }

    private void setUpOxygenTanks() {
        anOxygenTank = new OxygenTank(
                A_OXYGEN_TANK_ID,
                oxygenCategoryBuilder.buildById(OxygenConstants.Categories.E_ID),
                A_REQUEST_DATE,
                A_READY_DATE
        );

        anotherOxygenTank = new OxygenTank(
                ANOTHER_OXYGEN_TANK_ID,
                oxygenCategoryBuilder.buildById(OxygenConstants.Categories.B_ID),
                ANOTHER_REQUEST_DATE,
                ANOTHER_READY_DATE
        );
    }

    private void setUpRepository() {
        oxygenTankService = mock(OxygenTankService.class);

        when(oxygenTankService.findAll()).thenReturn(new ArrayList<>(Arrays.asList(anOxygenTank, anotherOxygenTank)));
    }

    public void setUpRepositoryForNoTank() {
        when(oxygenTankService.findAll()).thenReturn(new ArrayList<>());
    }
}
