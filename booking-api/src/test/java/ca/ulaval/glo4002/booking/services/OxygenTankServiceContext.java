package ca.ulaval.glo4002.booking.services;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import ca.ulaval.glo4002.booking.builders.oxygen.OxygenCategoryBuilder;
import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.domainObjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.entities.OxygenTankEntity;
import ca.ulaval.glo4002.booking.parsers.OxygenTankParser;
import ca.ulaval.glo4002.booking.repositories.OxygenRepository;

public class OxygenTankServiceContext {
	public OxygenRepository repository;
	public OxygenTank oxygenTankA;
	public OxygenTank oxygenTankB;
	public OxygenTank oxygenTankE;
	public OxygenTank oxygenTankNonExistant;
	public OxygenTankEntity oxygenTankAEntity;
	public OxygenTankEntity oxygenTankBEntity;
	public OxygenTankEntity oxygenTankEEntity;
	public OxygenTankEntity oxygenTankNonExistantEntity;
	public Long oxygenTankAId;
	private Long oxygenTankBId;
	private Long oxygenTankEId;
	public Long oxygenTankNonExistantId = 0L;
	private LocalDate A_VALID_DATE = LocalDate.of(2050, 6, 20);
	private OxygenTankParser parser = new OxygenTankParser();

	public OxygenTankServiceContext() {
		this.setUpOxygenTanks();
		this.setUpRepository();
	}

	private void setUpRepository() {
		this.repository = mock(OxygenRepository.class);
		when(repository.findAll()).thenReturn(Arrays.asList(oxygenTankAEntity, oxygenTankBEntity, oxygenTankEEntity));
		when(repository.findById(oxygenTankAId)).thenReturn(Optional.of(oxygenTankAEntity));
		when(repository.findById(oxygenTankBId)).thenReturn(Optional.of(oxygenTankBEntity));
		when(repository.findById(oxygenTankEId)).thenReturn(Optional.of(oxygenTankEEntity));
	}

	private void setUpOxygenTanks() {
		OxygenCategoryBuilder categoryBuilder = new OxygenCategoryBuilder();

		this.oxygenTankA = new OxygenTank(categoryBuilder.buildById(OxygenConstants.Categories.A_ID),
				this.A_VALID_DATE);
		this.oxygenTankB = new OxygenTank(categoryBuilder.buildById(OxygenConstants.Categories.B_ID),
				this.A_VALID_DATE);
		this.oxygenTankE = new OxygenTank(categoryBuilder.buildById(OxygenConstants.Categories.E_ID),
				this.A_VALID_DATE);
		this.oxygenTankNonExistant = new OxygenTank(categoryBuilder.buildById(0L), this.A_VALID_DATE);

		this.oxygenTankAEntity = this.parser.toEntity(this.oxygenTankA);
		this.oxygenTankAId = this.oxygenTankAEntity.id;
		this.oxygenTankBEntity = this.parser.toEntity(this.oxygenTankB);
		this.oxygenTankBId = this.oxygenTankBEntity.id;
		this.oxygenTankEEntity = this.parser.toEntity(this.oxygenTankE);
		this.oxygenTankEId = this.oxygenTankEEntity.id;
		this.oxygenTankNonExistantEntity = this.parser.toEntity(this.oxygenTankNonExistant);
	}

	public void setUpRepositoryForSave() {
		when(repository.findById(this.oxygenTankNonExistantId))
				.thenReturn(Optional.of(this.oxygenTankNonExistantEntity));
	}
}
