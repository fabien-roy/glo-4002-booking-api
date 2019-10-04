package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.builders.shuttles.ShuttleCategoryBuilder;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.Passenger;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.dto.ShuttleDto;
import ca.ulaval.glo4002.booking.entities.PassengerEntity;
import ca.ulaval.glo4002.booking.entities.ShuttleEntity;

import java.util.ArrayList;
import java.util.List;

public class ShuttleParser implements EntityParser<Shuttle, ShuttleEntity>, ToDtoParser<Shuttle, ShuttleDto> {
	
	private ShuttleCategoryBuilder shuttleCategoryBuilder = new ShuttleCategoryBuilder();

	@Override
	public Shuttle parseEntity(ShuttleEntity shuttle) {
		List<Passenger> passengers = new ArrayList<>();
		shuttle.getPassengers().forEach(passenger -> passengers.add(new Passenger(passenger.getId())));

		return new Shuttle(
				shuttle.getId(),
				shuttleCategoryBuilder.buildById(shuttle.getCategoryId()),
				shuttle.getDate(),
				passengers
		);
	}

	@Override
	public ShuttleEntity toEntity(Shuttle shuttle) {
		List<PassengerEntity> passengers = new ArrayList<>();
		shuttle.getPassengers().forEach(passenger -> passengers.add(new PassengerEntity(passenger.getId())));

		return new ShuttleEntity(
				shuttle.getId(),
				shuttle.getShuttleCategory().getId(),
				shuttle.getDate(),
				passengers
		);
	}

	@Override
	public ShuttleDto toDto(Shuttle shuttle) {
		List<Long> passengers = new ArrayList<>();
		shuttle.getPassengers().forEach(passenger -> passengers.add(passenger.getId()));

	    ShuttleDto dto = new ShuttleDto();
	    dto.date = shuttle.getDate().toString();
	    dto.passengers = passengers;
	    dto.shuttleName = shuttle.getShuttleCategory().getName();

		return dto;
	}
}
