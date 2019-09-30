package ca.ulaval.glo4002.booking.services;

import static java.time.temporal.ChronoUnit.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.booking.builders.oxygen.OxygenCategoryBuilder;
import ca.ulaval.glo4002.booking.constants.FestivalConstants;
import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.domainObjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domainObjects.oxygen.categories.OxygenCategory;
import ca.ulaval.glo4002.booking.parsers.OxygenTankParser;
import ca.ulaval.glo4002.booking.repositories.OxygenRepository;

public class OxygenTankService {

	private OxygenRepository oxygenRepository;
	private OxygenTankParser oxygenTankParser;

	public OxygenTankService(OxygenRepository oxygenRepository) {
		this.oxygenRepository = oxygenRepository;
		this.oxygenTankParser = new OxygenTankParser();
	}

	public OxygenTankService() {
		// TODO Remove an correct in DomainObjects/oxygen/OxygenTank.java
	}

	public OxygenCategory getOxygenCategoryForTimeTable(OxygenCategory category, LocalDate timeRequested) {
		Long timeToProduce = category.getProduction().getProductionTime().toDays();
		LocalDate timeReady = timeRequested.plusDays(timeToProduce);
		OxygenCategoryBuilder categoryBuilder = new OxygenCategoryBuilder();

		if (timeReady.isBefore(FestivalConstants.Dates.START_DATE)) {
			return category;
		} else if (timeRequested.plus(10, DAYS).isBefore(FestivalConstants.Dates.START_DATE)) {
			return categoryBuilder.buildById(OxygenConstants.Categories.B_ID);
		} else {
			return categoryBuilder.buildById(OxygenConstants.Categories.E_ID);
		}
	}

	public void save(OxygenTank oxygenTank) {
		// TODO make this work
	}

	public Iterable<OxygenTank> findAll() {
		List<OxygenTank> oxygenTanks = new ArrayList<>();

		oxygenRepository.findAll()
				.forEach(oxygenTankEntity -> oxygenTanks.add(oxygenTankParser.parseEntity(oxygenTankEntity)));

		return oxygenTanks;
	}
}
