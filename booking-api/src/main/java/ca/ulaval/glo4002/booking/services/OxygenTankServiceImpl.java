package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.builders.oxygen.OxygenCategoryBuilder;
import ca.ulaval.glo4002.booking.constants.DateConstants;
import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.categories.OxygenCategory;
import ca.ulaval.glo4002.booking.entities.OxygenTankEntity;
import ca.ulaval.glo4002.booking.exceptions.oxygen.OxygenTankNotFoundException;
import ca.ulaval.glo4002.booking.parsers.OxygenTankParser;
import ca.ulaval.glo4002.booking.repositories.OxygenTankRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OxygenTankServiceImpl implements OxygenTankService {
	private OxygenTankRepository oxygenTankRepository;
	private OxygenTankParser oxygenTankParser;

	public OxygenTankServiceImpl(OxygenTankRepository oxygenTankRepository) {
		this.oxygenTankRepository = oxygenTankRepository;
		this.oxygenTankParser = new OxygenTankParser();
	}

	public OxygenTankServiceImpl() {
		// TODO Remove an correct in DomainObjects/oxygen/OxygenTank.java
	}

	@Override
	public OxygenCategory getOxygenCategoryForTimeTable(OxygenCategory category, LocalDate timeRequested) {
		Long timeToProduce = category.getProduction().getProductionTime().toDays();
		LocalDate timeReady = timeRequested.plusDays(timeToProduce);
		OxygenCategoryBuilder categoryBuilder = new OxygenCategoryBuilder();

		// TODO : Put "10" in a constant
		if (timeReady.isBefore(DateConstants.START_DATE)) {
			return category;
		} else if (timeRequested.plusDays(10).isBefore(DateConstants.START_DATE)) {
			return categoryBuilder.buildById(OxygenConstants.Categories.B_ID);
		} else {
			return categoryBuilder.buildById(OxygenConstants.Categories.E_ID);
		}
	}

	@Override
	public OxygenTank save(OxygenTank oxygenTank) {
		this.oxygenTankRepository.save(this.oxygenTankParser.toEntity(oxygenTank));

		return oxygenTank;
	}

	@Override
	public Iterable<OxygenTank> findAll() {
		List<OxygenTank> oxygenTanks = new ArrayList<>();

		oxygenTankRepository.findAll()
				.forEach(oxygenTankEntity -> oxygenTanks.add(oxygenTankParser.parseEntity(oxygenTankEntity)));

		return oxygenTanks;
	}

	@Override
	public OxygenTank findById(Long id) {
		OxygenTankEntity oxygenTankEntity = this.oxygenTankRepository.findById(id)
				.orElseThrow(OxygenTankNotFoundException::new);

		return this.oxygenTankParser.parseEntity(oxygenTankEntity);
	}
}
