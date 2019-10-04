package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.builders.shuttles.ShuttleCategoryBuilder;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.Passenger;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.entities.PassengerEntity;
import ca.ulaval.glo4002.booking.entities.ShuttleEntity;

import java.util.ArrayList;
import java.util.List;

public class ShuttleParser implements EntityParser<Shuttle, ShuttleEntity> {
	
	private ShuttleCategoryBuilder shuttleCategoryBuilder = new ShuttleCategoryBuilder();
	private PassengerParser passengerParser = new PassengerParser();

	// TODO : TRANS : ShuttleParser.parseEntity tests
	@Override
	public Shuttle parseEntity(ShuttleEntity entity) {
		List<Passenger> passengers = new ArrayList<>(passengerParser.parseEntity(entity.getPassengers()));

		return new Shuttle(
				entity.getId(),
				entity.getPrice(),
				shuttleCategoryBuilder.buildById(entity.getCategoryId()),
				passengers
		);
	}

	// TODO : TRANS : ShuttleParser.toEntity tests
	@Override
	public ShuttleEntity toEntity(Shuttle shuttle) {
		List<PassengerEntity> passengers = new ArrayList<>(passengerParser.toEntity(shuttle.getPassengers()));

		return new ShuttleEntity(
				shuttle.getId(),
				shuttle.getShuttleCategory().getId(),
				shuttle.getPrice(),
				passengers
		);
	}
}
