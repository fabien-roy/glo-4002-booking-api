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
import ca.ulaval.glo4002.booking.entities.OxygenTankEntity;
import ca.ulaval.glo4002.booking.exceptions.oxygen.OxygenTankNotFoundException;
import ca.ulaval.glo4002.booking.parsers.OxygenTankParser;
import ca.ulaval.glo4002.booking.repositories.OxygenRepository;

public class OxygenTankServiceImpl implements OxygenTankService {
	private OxygenRepository oxygenRepository;
	private OxygenTankParser oxygenTankParser;

	public OxygenTankServiceImpl(OxygenRepository oxygenRepository) {
		this.oxygenRepository = oxygenRepository;
		this.oxygenTankParser = new OxygenTankParser();
	}

	public OxygenTankServiceImpl() {
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

	@Override
	public OxygenTank save(OxygenTank oxygenTank) {
		this.oxygenRepository.save(this.oxygenTankParser.toEntity(oxygenTank));
		return oxygenTank;
	}

	public Iterable<OxygenTank> findAll() {
		List<OxygenTank> oxygenTanks = new ArrayList<>();

		oxygenRepository.findAll()
				.forEach(oxygenTankEntity -> oxygenTanks.add(oxygenTankParser.parseEntity(oxygenTankEntity)));

		return oxygenTanks;
	}

	@Override
	public OxygenTank findById(Long id) {
		OxygenTankEntity oxygenTankEntity = this.oxygenRepository.findById(id)
				.orElseThrow(OxygenTankNotFoundException::new);

		return this.oxygenTankParser.parseEntity(oxygenTankEntity);
	}

	@Override
	public Iterable<OxygenTank> saveAll(Iterable<OxygenTank> objects) {
		// TODO Auto-generated method stub
		return null;
	}
}
