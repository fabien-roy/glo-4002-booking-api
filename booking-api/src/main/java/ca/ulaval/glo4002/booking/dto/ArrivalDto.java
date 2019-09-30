package ca.ulaval.glo4002.booking.dto;

import java.time.LocalDate;
import java.util.List;

public class ArrivalDto {
	
	LocalDate date; //TODO : String?
	String shuttleName;
	List<PassengerDto> passengers;

}
