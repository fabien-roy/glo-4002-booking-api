package ca.ulaval.glo4002.booking.validators;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.constants.FestivalConstants;

public class FestivalDateValidatorTest {
	
	public final LocalDate DATE_BEFORE_FESTIVAL = FestivalConstants.Dates.
			START_DATE.minus(1, DAYS);
	
	public final LocalDate DATE_AFTER_FESTIVAL = FestivalConstants.Dates.
			END_DATE.plus(1, DAYS);
	
	public final LocalDate DATE_DURING_FESTIVAL = FestivalConstants.Dates.
			START_DATE;
	
	@Test
	public void dateIsBeforeFestival() {
		assertTrue(FestivalDateValidator
				.dateIsOutsideFestivalDates(DATE_BEFORE_FESTIVAL));
	}
	
	@Test
	public void dateIsAfterFestival() {
		assertTrue(FestivalDateValidator
				.dateIsOutsideFestivalDates(DATE_AFTER_FESTIVAL));
	}
	
	@Test
	public void dateIsDuringFestival() {
		assertFalse(FestivalDateValidator
				.dateIsOutsideFestivalDates(DATE_DURING_FESTIVAL));
	}
}
