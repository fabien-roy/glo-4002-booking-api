package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.builders.oxygen.OxygenCategoryBuilder;
import ca.ulaval.glo4002.booking.constants.DateConstants;
import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.categories.OxygenCategory;
import ca.ulaval.glo4002.booking.domainobjects.qualities.Quality;
import ca.ulaval.glo4002.booking.entities.OxygenTankEntity;
import ca.ulaval.glo4002.booking.exceptions.dates.InvalidDateException;
import ca.ulaval.glo4002.booking.exceptions.oxygen.OxygenTankNotFoundException;
import ca.ulaval.glo4002.booking.parsers.OxygenTankParser;
import ca.ulaval.glo4002.booking.repositories.OxygenTankRepository;
import ca.ulaval.glo4002.booking.util.FestivalDateUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OxygenTankServiceImpl implements OxygenTankService {

	private OxygenTankRepository repository;
	private OxygenTankParser parser;
	private OxygenCategoryBuilder categoryBuilder;

	public OxygenTankServiceImpl(OxygenTankRepository repository) {
		this.repository = repository;
		this.parser = new OxygenTankParser();
		this.categoryBuilder = new OxygenCategoryBuilder();
	}

	public OxygenTankServiceImpl() {
		// TODO Remove an correct in DomainObjects/oxygen/OxygenTank.java
	}

	@Override
	public OxygenTank save(OxygenTank oxygenTank) {
		this.repository.save(parser.toEntity(oxygenTank));

		return oxygenTank;
	}

	@Override
	public Iterable<OxygenTank> findAll() {
		List<OxygenTank> oxygenTanks = new ArrayList<>();

		repository.findAll().forEach(oxygenTankEntity -> oxygenTanks.add(parser.parseEntity(oxygenTankEntity)));

		return oxygenTanks;
	}

	@Override
	public OxygenTank findById(Long id) {
		OxygenTankEntity oxygenTankEntity = repository.findById(id).orElseThrow(OxygenTankNotFoundException::new);

		return this.parser.parseEntity(oxygenTankEntity);
	}

	@Override
	public Iterable<OxygenTank> order(Quality requestedQuality, LocalDate orderDate) {
		if (FestivalDateUtil.isAfterFestivalStart(orderDate)) {
			throw new InvalidDateException();
		}

		OxygenCategory requestedCategory = categoryBuilder.buildByQualityId(requestedQuality.getId());
		LocalDate readyDate = getReadyDate(requestedCategory, orderDate);
		OxygenCategory category = getCategoryForTimeToProduce(requestedCategory, orderDate, readyDate);
		readyDate = getReadyDate(category, orderDate);

		OxygenTank oxygenTank = new OxygenTank(category, orderDate, readyDate);

		return new ArrayList<>(Collections.singletonList(oxygenTank));
	}

	private LocalDate getReadyDate(OxygenCategory category, LocalDate orderDate) {
		return orderDate.plusDays(category.getProduction().getProductionTime().toDays());
	}

	private OxygenCategory getCategoryForTimeToProduce(OxygenCategory requestedCategory, LocalDate requestDate, LocalDate readyDate) {
		if (readyDate.isBefore(DateConstants.START_DATE)) {
			return requestedCategory;
		} else if (requestDate.plusDays(OxygenConstants.Productions.ELECTROLYTES_PRODUCTION_TIME.toDays())
				.isBefore(DateConstants.START_DATE)) {
			return categoryBuilder.buildById(OxygenConstants.Categories.B_ID);
		} else {
			return categoryBuilder.buildById(OxygenConstants.Categories.E_ID);
		}
	}
}
