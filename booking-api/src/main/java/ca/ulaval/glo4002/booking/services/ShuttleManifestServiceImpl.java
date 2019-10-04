package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.shuttles.ShuttleInventory;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.ShuttleManifest;
import ca.ulaval.glo4002.booking.exceptions.shuttles.ShuttleManifestInvalidDateException;
import ca.ulaval.glo4002.booking.util.FestivalDateUtil;

import javax.annotation.Resource;
import java.time.LocalDate;

public class ShuttleManifestServiceImpl implements ShuttleManifestService {

	@Resource
	private final ShuttleInventoryService shuttleInventoryService;

	public ShuttleManifestServiceImpl(ShuttleInventoryService shuttleInventoryService) {
		this.shuttleInventoryService = shuttleInventoryService;
	}

	@Override
	public ShuttleManifest findByDate(LocalDate date) {
	    validateManifestDate(date);

		ShuttleInventory inventory = shuttleInventoryService.get();

		return new ShuttleManifest(date, inventory.getDepartureShuttles(), inventory.getArrivalShuttles());
	}

	private void validateManifestDate(LocalDate manifestDate){
		if (FestivalDateUtil.isOutsideFestivalDates(manifestDate)){
			throw new ShuttleManifestInvalidDateException();
		}
	}
}
