package ca.ulaval.glo4002.booking.dto;

import java.time.LocalDate;
import java.util.List;

public class ShuttleManifestDto {
	
	private LocalDate date; //TODO : String?
	private List<ArrivalDto> arrivals;
	private List<DepartureDto> departures;

}
