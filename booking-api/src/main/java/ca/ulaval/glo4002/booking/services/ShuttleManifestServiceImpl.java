package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.ShuttleInventory;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.ShuttleManifest;
import ca.ulaval.glo4002.booking.exceptions.shuttles.ShuttleManifestInvalidDateException;
import ca.ulaval.glo4002.booking.util.FestivalDateUtil;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ShuttleManifestServiceImpl implements ShuttleManifestService {

	@Resource
	private final ShuttleInventoryService shuttleInventoryService;

	public ShuttleManifestServiceImpl(ShuttleInventoryService shuttleInventoryService) {
		this.shuttleInventoryService = shuttleInventoryService;
	}

	@Override
	public ShuttleManifest findAll() {
		ShuttleInventory inventory = shuttleInventoryService.get();

		return new ShuttleManifest(inventory.getDepartureShuttles(), inventory.getArrivalShuttles());
	}

	@Override
	public ShuttleManifest findByDate(LocalDate date) {
	    validateManifestDate(date);

		ShuttleInventory inventory = shuttleInventoryService.get();

		List<Shuttle> departureShuttles = inventory.getDepartureShuttles().stream().filter(shuttle -> shuttle.getDate().equals(date)).collect(Collectors.toList());
		List<Shuttle> arrivalShuttles = inventory.getArrivalShuttles().stream().filter(shuttle -> shuttle.getDate().equals(date)).collect(Collectors.toList());

		return new ShuttleManifest(departureShuttles, arrivalShuttles);
	}

	private void validateManifestDate(LocalDate manifestDate){
		if (FestivalDateUtil.isOutsideFestivalDates(manifestDate)){
			throw new ShuttleManifestInvalidDateException();
		}
	}
}
