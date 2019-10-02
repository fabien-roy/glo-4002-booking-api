package ca.ulaval.glo4002.booking.services;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import ca.ulaval.glo4002.booking.builders.oxygen.OxygenCategoryBuilder;
import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.entities.OxygenTankEntity;
import ca.ulaval.glo4002.booking.parsers.OxygenTankParser;
import ca.ulaval.glo4002.booking.repositories.OxygenTankRepository;

class OxygenTankServiceContext {

	public OxygenTankService subject;

	public static final LocalDate A_VALID_DATE = LocalDate.of(2050, 6, 20);
	private static final LocalDate A_DATE_AFTER_THE_OTHER_ONE = A_VALID_DATE.plusDays(20);

	OxygenTankRepository repository;
	OxygenTank oxygenTankA;
	OxygenTank oxygenTankB;
	OxygenTank oxygenTankE;
	public OxygenTank nonExistentOxygenTank;
	private OxygenTankEntity oxygenTankAEntity;
	private OxygenTankEntity oxygenTankBEntity;
	private OxygenTankEntity oxygenTankEEntity;
	private OxygenTankEntity oxygenTankNonExistentEntity;
	Long oxygenTankAId;
	private Long oxygenTankBId;
	private Long oxygenTankEId;
	public Long nonExistentOxygenTankId = 0L;
	private OxygenTankParser parser = new OxygenTankParser();
	private OxygenCategoryBuilder categoryBuilder = new OxygenCategoryBuilder();

	OxygenTankServiceContext() {
		setUpOxygenTanks();
		setUpRepository();
		setUpSubject();
	}

	private void setUpOxygenTanks() {
		oxygenTankA = new OxygenTank(categoryBuilder.buildById(OxygenConstants.Categories.A_ID), A_VALID_DATE,
				A_DATE_AFTER_THE_OTHER_ONE);

		oxygenTankB = new OxygenTank(categoryBuilder.buildById(OxygenConstants.Categories.B_ID), A_VALID_DATE,
				A_DATE_AFTER_THE_OTHER_ONE);

		oxygenTankE = new OxygenTank(categoryBuilder.buildById(OxygenConstants.Categories.E_ID), A_VALID_DATE,
				A_DATE_AFTER_THE_OTHER_ONE);

		nonExistentOxygenTank = new OxygenTank(categoryBuilder.buildById(OxygenConstants.Categories.A_ID), A_VALID_DATE,
				A_DATE_AFTER_THE_OTHER_ONE);

		oxygenTankAEntity = parser.toEntity(oxygenTankA);
		oxygenTankAId = oxygenTankAEntity.getId();
		oxygenTankBEntity = parser.toEntity(oxygenTankB);
		oxygenTankBId = oxygenTankBEntity.getId();
		oxygenTankEEntity = parser.toEntity(oxygenTankE);
		oxygenTankEId = oxygenTankEEntity.getId();
		oxygenTankNonExistentEntity = parser.toEntity(nonExistentOxygenTank);
	}

	private void setUpRepository() {
		this.repository = mock(OxygenTankRepository.class);

		when(repository.findAll()).thenReturn(Arrays.asList(oxygenTankAEntity, oxygenTankBEntity, oxygenTankEEntity));
		when(repository.findById(oxygenTankAId)).thenReturn(Optional.of(oxygenTankAEntity));
		when(repository.findById(oxygenTankBId)).thenReturn(Optional.of(oxygenTankBEntity));
		when(repository.findById(oxygenTankEId)).thenReturn(Optional.of(oxygenTankEEntity));
	}

	private void setUpSubject() {
		InventoryService inventoryService = mock(InventoryService.class);

		subject = new OxygenTankServiceImpl(repository, inventoryService);
	}

	void setUpRepositoryForSave() {
		when(repository.findById(nonExistentOxygenTankId)).thenReturn(Optional.of(oxygenTankNonExistentEntity));
	}
}
