package ca.ulaval.glo4002.booking.parsers;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.booking.builders.shuttles.ShuttleCategoryBuilder;
import ca.ulaval.glo4002.booking.constants.TripConstants;
import ca.ulaval.glo4002.booking.domainobjects.trips.ArrivalTrip;
import ca.ulaval.glo4002.booking.domainobjects.trips.DepartureTrip;
import ca.ulaval.glo4002.booking.domainobjects.trips.Trip;
import ca.ulaval.glo4002.booking.dto.PassengerDto;
import ca.ulaval.glo4002.booking.dto.TripDto;
import ca.ulaval.glo4002.booking.entities.TripEntity;
import ca.ulaval.glo4002.booking.exceptions.UnusedMethodException;

public class TripParser implements EntityParser<Trip, TripEntity>, DtoParser<Trip, TripDto> {
	
	ShuttleCategoryBuilder shuttleCategoryBuilder = new ShuttleCategoryBuilder();
	ShuttleParser shuttleParser = new ShuttleParser();
	PassengerParser passengerParser = new PassengerParser();

    // TODO : Add passengers
	@Override
	public Trip parseEntity(TripEntity entity) {
	    if (entity.getTypeId().equals(TripConstants.Types.DEPARTURE_ID)) {
	        return new DepartureTrip(
	                entity.getId(),
                    entity.getDate(),
                    passengerParser.parseEntity(entity.getPassengers()),
                    shuttleParser.parseEntity(entity.getShuttle())
            );
        } else if (entity.getTypeId().equals(TripConstants.Types.ARRIVAL_ID)) {
            return new ArrivalTrip(
                    entity.getId(),
                    entity.getDate(),
                    passengerParser.parseEntity(entity.getPassengers()),
                    shuttleParser.parseEntity(entity.getShuttle())
            );
        } else {
            // TODO : Should we throw?
        }

        return null;
	}

	@Override
	public TripEntity toEntity(Trip trip) {
		return new TripEntity(
		        trip.getId(),
                trip.getDate(),
                trip.getType().getId(),
                passengerParser.toEntity(trip.getPassengers())
        );
	}

	@Override
	public Trip parseDto(TripDto dto) {
		throw new UnusedMethodException();
	}

	@Override
	public TripDto toDto(Trip object) {
		TripDto dto = new TripDto();
		String shuttleName = object.getShuttle().getShuttleCategory().getName();
		String date = object.getDate().toString();
		List<PassengerDto> passengers = new ArrayList<>();
		
		passengers.addAll(passengerParser.toDto(object.getPassengers()));
		
		dto.date = date;
		dto.shuttleName = shuttleName;
		dto.passengers = passengers;
		return dto;
	}
	
	public List<TripDto> toDtos(List<? extends Trip> trips) {
		List<TripDto> tripDtos = new ArrayList<>();
		
		for(Trip trip: trips) {
			TripDto dto = toDto(trip);
			tripDtos.add(dto);
			
		}
		
		return tripDtos;
		
	}
	
	
}
