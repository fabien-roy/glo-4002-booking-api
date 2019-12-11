package ca.ulaval.glo4002.booking.program.artists.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.booking.profits.domain.ProfitReport;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.profits.domain.Money;

class ArtistTest {

	@Test
	void updateProfit_shouldAddExpenseToProfitReport() {
		ProfitReport profitReport = new ProfitReport();
		Money expectedExpense = new Money(BigDecimal.valueOf(100));
		Artist artist = new Artist(null, null, expectedExpense, null, null, null, new ArrayList<>());

		artist.updateProfit(profitReport);

		assertEquals(expectedExpense, profitReport.getExpense());
	}
}
