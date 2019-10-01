package ca.ulaval.glo4002.booking.util;

import ca.ulaval.glo4002.booking.constants.DateConstants;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FestivalDateUtilTest {
	
	public final LocalDate DATE_BEFORE_FESTIVAL = DateConstants.
			START_DATE.minus(1, DAYS);
	
	public final LocalDate DATE_AFTER_FESTIVAL = DateConstants.
			END_DATE.plus(1, DAYS);
	
	public final LocalDate DATE_DURING_FESTIVAL = DateConstants.
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
