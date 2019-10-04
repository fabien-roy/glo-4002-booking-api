package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.domainobjects.shuttles.Passenger;
import ca.ulaval.glo4002.booking.dto.PassengerDto;
import ca.ulaval.glo4002.booking.entities.PassengerEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PassengerParser implements ToDtoParser<List<Passenger>, List<PassengerDto>>, EntityParser<List<Passenger>, List<PassengerEntity>>{

	@Override
	public List<PassengerDto> toDto(List<Passenger> passengers) {
		List<PassengerDto> passengerDtos = new ArrayList<>();
		List<Long> passengerIds = new ArrayList<>();
	
		
		passengerIds.addAll(passengers.stream().map(Passenger::getId).collect(Collectors.toList()));
		
		for (Long passengerId : passengerIds) {
			PassengerDto passengerDto = new PassengerDto();
			passengerDto.id = passengerId;
			passengerDtos.add(passengerDto);
		}
		
		return passengerDtos;
	}

	@Override
	public List<Passenger> parseEntity(List<PassengerEntity> entities) {
		List<Passenger> passengers = new ArrayList<>();
		
		for (PassengerEntity passengerEntity : entities) {
			Passenger passenger = new Passenger(passengerEntity.getId());
			passengers.add(passenger);
		}
		
		return passengers;
	}

	@Override
	public List<PassengerEntity> toEntity(List<Passenger> passengers) {
		List<PassengerEntity> entities = new ArrayList<>();
		
		for (Passenger passenger : passengers) {
			PassengerEntity entity = new PassengerEntity(passenger.getId());
			entities.add(entity);
		}
		return entities;
	}
	
}
