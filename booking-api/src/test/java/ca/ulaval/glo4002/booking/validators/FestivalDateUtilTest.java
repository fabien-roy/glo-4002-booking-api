package ca.ulaval.glo4002.booking.validators;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import ca.ulaval.glo4002.booking.util.FestivalDateUtil;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.constants.FestivalConstants;

public class FestivalDateUtilTest {
	
	public final LocalDate DATE_BEFORE_FESTIVAL = FestivalConstants.Dates.
			START_DATE.minus(1, DAYS);
	
	public final LocalDate DATE_AFTER_FESTIVAL = FestivalConstants.Dates.
			END_DATE.plus(1, DAYS);
	
	public final LocalDate DATE_DURING_FESTIVAL = FestivalConstants.Dates.
			START_DATE;
	
	@Test
	public void dateIsBeforeFestival() {
		assertTrue(FestivalDateUtil
				.isOutsideFestivalDates(DATE_BEFORE_FESTIVAL));
	}
	
	@Test
	public void dateIsAfterFestival() {
		assertTrue(FestivalDateUtil
				.isOutsideFestivalDates(DATE_AFTER_FESTIVAL));
	}
	
	@Test
	public void dateIsDuringFestival() {
		assertFalse(FestivalDateUtil
				.isOutsideFestivalDates(DATE_DURING_FESTIVAL));
	}
}
