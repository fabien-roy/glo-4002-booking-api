package ca.ulaval.glo4002.booking.entities.oxygen;

import ca.ulaval.glo4002.booking.builders.oxygen.OxygenCategoryBuilder;
import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.constants.DateConstants;
import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domainobjects.qualities.NebulaQuality;
import ca.ulaval.glo4002.booking.domainobjects.qualities.Quality;
import ca.ulaval.glo4002.booking.domainobjects.qualities.SupergiantQuality;
import ca.ulaval.glo4002.booking.domainobjects.qualities.SupernovaQuality;
import ca.ulaval.glo4002.booking.exceptions.dates.InvalidDateException;
import ca.ulaval.glo4002.booking.services.OxygenTankService;
import ca.ulaval.glo4002.booking.services.OxygenTankServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.api.Assertions.*;

public class OxygenTankTest {

    private OxygenTank subject;
    private OxygenCategoryBuilder oxygenCategoryBuilder;
    private OxygenTankService oxygenTankService;
    private NebulaQuality nebulaQuality;
    private SupergiantQuality supergiantQuality;
    private SupernovaQuality supernovaQuality;
    private final LocalDate VALID_DATE = DateConstants.START_DATE.minus(30, DAYS);
    private final LocalDate VALID_DATE_15DAYS_BEFORE_START = DateConstants.START_DATE.minus(15, DAYS);
    private final LocalDate VALID_DATE_5DAYS_BEFORE_START = DateConstants.START_DATE.minus(5, DAYS);
    private final LocalDate INVALID_DATE = DateConstants.START_DATE.plus(1, DAYS);

    @BeforeEach
    void setup() {
        oxygenCategoryBuilder = new OxygenCategoryBuilder();
        subject = new OxygenTank(oxygenCategoryBuilder
                .buildById(OxygenConstants.Categories.A_ID), VALID_DATE, VALID_DATE);
    }

    @Test
    void whenOxygenTankIsCreated_thenCategoryIsAssigned() {
        assertNotNull(subject.getOxygenTankCategory());
    }

    @Test
    void whenOxygenTankIsCreated_thenRequestDateIsAssigned() {
        assertNotNull(subject.getRequestDate());
    }

    @Test
    void whenOxygenTankIsCreated_thenReadyDateIsAssigned() {
        assertNotNull(subject.getReadyDate());
    }
}
