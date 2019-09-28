package ca.ulaval.glo4002.booking.entities.trips;

import java.time.LocalDate;
import java.util.List;

import ca.ulaval.glo4002.booking.entities.shuttles.categories.ShuttleCategory;

public abstract class Trip {

	protected LocalDate date;
	protected ShuttleCategory shuttleName;
	protected List<Long> passengers;
}
