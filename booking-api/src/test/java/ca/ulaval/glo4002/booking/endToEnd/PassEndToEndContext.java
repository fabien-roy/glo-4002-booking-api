package ca.ulaval.glo4002.booking.endToEnd;

import ca.ulaval.glo4002.booking.constants.FestivalConstants;
import ca.ulaval.glo4002.booking.constants.PassConstants;
import ca.ulaval.glo4002.booking.domainObjects.passes.Pass;
import ca.ulaval.glo4002.booking.entities.PassEntity;

import java.time.LocalDate;

public class PassEndToEndContext {

    private static final LocalDate A_VALID_DATE = FestivalConstants.Dates.START_DATE.plusDays(1L);
    PassEntity aPass = new PassEntity(PassConstants.Categories.SUPERNOVA_ID, PassConstants.Options.SINGLE_ID, A_VALID_DATE);
    PassEntity anotherPass = new PassEntity(PassConstants.Categories.SUPERGIANT_ID, PassConstants.Options.PACKAGE_ID);

    PassEndToEndContext(){
    }

}
