package ca.ulaval.glo4002.booking.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class FestivalDateValidatorTest {
	
	public final String DATE_BEFORE_FESTIVAL = "2019-09-20";
	public final String DATE_AFTER_FESTIVAL = "2070-01-01";
	public final String DATE_DURING_FESTIVAL = "2050-07-19";
	
	@Test
	public void dateIsBeforeFestival() {
		assertTrue(FestivalDateValidator
				.dateIsOutsideFestivalDates(LocalDate.parse(DATE_BEFORE_FESTIVAL)));
	}
	
	@Test
	public void dateIsAfterFestival() {
		assertTrue(FestivalDateValidator
				.dateIsOutsideFestivalDates(LocalDate.parse(DATE_AFTER_FESTIVAL)));
	}
	
	@Test
	public void dateIsDuringFestival() {
		assertFalse(FestivalDateValidator
				.dateIsOutsideFestivalDates(LocalDate.parse(DATE_DURING_FESTIVAL)));
	}
}
