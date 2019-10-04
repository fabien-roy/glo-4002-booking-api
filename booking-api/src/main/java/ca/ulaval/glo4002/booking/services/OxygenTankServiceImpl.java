package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.categories.OxygenCategory;
import ca.ulaval.glo4002.booking.entities.OxygenTankEntity;
import ca.ulaval.glo4002.booking.entities.OxygenTankInventoryEntity;
import ca.ulaval.glo4002.booking.exceptions.oxygen.OxygenTankNotFoundException;
import ca.ulaval.glo4002.booking.parsers.OxygenTankParser;
import ca.ulaval.glo4002.booking.repositories.OxygenTankRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OxygenTankServiceImpl implements OxygenTankService {

	private final OxygenTankRepository repository;
	private final OxygenTankParser parser;

	public OxygenTankServiceImpl(OxygenTankRepository repository) {
		this.repository = repository;
		this.parser = new OxygenTankParser();
	}

	// TODO : OXY : Useless?
	@Override
	public Iterable<OxygenTank> saveAll(Iterable<OxygenTank> oxygenTanks) {
		List<OxygenTankEntity> entities = new ArrayList<>();
		oxygenTanks.forEach(oxygenTank -> entities.add(parser.toEntity(oxygenTank)));

		repository.saveAll(entities);

		return oxygenTanks;
	}

	// TODO : OXY : Useless?
	@Override
	public Iterable<OxygenTank> findAll() {
		List<OxygenTank> oxygenTanks = new ArrayList<>();
		repository.findAll().forEach(oxygenTankEntity -> oxygenTanks.add(parser.parseEntity(oxygenTankEntity)));

		return oxygenTanks;
	}

	// TODO : OXY : Useless?
	@Override
	public OxygenTank findById(Long id) {
		OxygenTankEntity oxygenTankEntity = repository.findById(id).orElseThrow(OxygenTankNotFoundException::new);

		return this.parser.parseEntity(oxygenTankEntity);
	}

	@Override
	public OxygenTank order(OxygenTankInventoryEntity inventory, OxygenCategory availableCategory, LocalDate date) {
		OxygenTankEntity oxygenTank = parser.toEntity(new OxygenTank(availableCategory, date));

		oxygenTank.setInventory(inventory);
		inventory.addInUseTank(oxygenTank);

		return parser.parseEntity(repository.save(oxygenTank));
	}

	@Override
	public OxygenTank setToInUse(OxygenTankInventoryEntity inventory, OxygenTank oxygenTank) {
		OxygenTankEntity oxygenTankEntity = repository.findById(oxygenTank.getId()).get();

		oxygenTankEntity.setInventory(inventory);
		inventory.addInUseTank(oxygenTankEntity);
		inventory.removeNotInUseTank(oxygenTankEntity);

		return parser.parseEntity(repository.save(oxygenTankEntity));
	}
}
