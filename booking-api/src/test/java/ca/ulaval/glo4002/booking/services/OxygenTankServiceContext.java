package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.builders.oxygen.OxygenCategoryBuilder;
import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.entities.OxygenTankEntity;
import ca.ulaval.glo4002.booking.parsers.OxygenTankParser;
import ca.ulaval.glo4002.booking.repositories.OxygenTankRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OxygenTankServiceContext {

	private static final LocalDate A_VALID_DATE = LocalDate.of(2050, 6, 20);
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

	OxygenTankServiceContext() {
		this.setUpOxygenTanks();
		this.setUpRepository();
	}

	private void setUpOxygenTanks() {
		OxygenCategoryBuilder categoryBuilder = new OxygenCategoryBuilder();

		oxygenTankA = new OxygenTank(
				categoryBuilder.buildById(OxygenConstants.Categories.A_ID),
				A_VALID_DATE,
				A_DATE_AFTER_THE_OTHER_ONE
		);

		oxygenTankB = new OxygenTank(categoryBuilder.buildById(OxygenConstants.Categories.B_ID),
				A_VALID_DATE,
				A_DATE_AFTER_THE_OTHER_ONE
		);

		oxygenTankE = new OxygenTank(
				categoryBuilder.buildById(OxygenConstants.Categories.E_ID),
				A_VALID_DATE,
				A_DATE_AFTER_THE_OTHER_ONE
		);

		nonExistentOxygenTank = new OxygenTank(
				categoryBuilder.buildById(OxygenConstants.Categories.A_ID),
				A_VALID_DATE,
				A_DATE_AFTER_THE_OTHER_ONE
		);

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

	void setUpRepositoryForSave() {
		when(repository.findById(nonExistentOxygenTankId)).thenReturn(Optional.of(oxygenTankNonExistentEntity));
	}
}
