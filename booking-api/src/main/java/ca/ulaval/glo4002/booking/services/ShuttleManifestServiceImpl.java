package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainObjects.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.repositories.ShuttleRepository;

public class ShuttleManifestServiceImpl implements ShuttleManifestService {
	
	private final ShuttleRepository shuttleRepository;
	
	
	public ShuttleManifestServiceImpl(ShuttleRepository shuttleRepository) {
		this.shuttleRepository = shuttleRepository;
	}

	@Override
	public Shuttle findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Shuttle> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shuttle save(Shuttle object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Shuttle> saveAll(Iterable<Shuttle> objects) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
