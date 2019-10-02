package ca.ulaval.glo4002.booking.domainobjects.trips;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.builders.shuttles.ShuttleCategoryBuilder;
import ca.ulaval.glo4002.booking.constants.ShuttleConstants;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.domainobjects.trips.types.ArrivalTripType;

public class ArrivalTripTest {
	
	private ShuttleCategoryBuilder shuttleCategoryBuilder = new ShuttleCategoryBuilder();
    private Shuttle shuttle = new Shuttle(ShuttleConstants.Categories.ET_SPACESHIP_ID,
    		ShuttleConstants.Categories.ET_SPACESHIP_PRICE,
    		shuttleCategoryBuilder.buildByName(ShuttleConstants.Categories.ET_SPACESHIP_NAME),
    		new ArrayList<>());

    @Test
    public void construction_shouldSetTripTypeToArrival() {
        ArrivalTrip subject = new ArrivalTrip(LocalDate.now(), new ArrayList<>(), shuttle);

        assertTrue(subject.getType() instanceof ArrivalTripType);
    }
}
