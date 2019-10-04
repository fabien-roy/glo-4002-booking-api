package ca.ulaval.glo4002.booking.services;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Collections;

import ca.ulaval.glo4002.booking.builders.oxygen.OxygenCategoryBuilder;
import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domainobjects.report.Inventory;
import ca.ulaval.glo4002.booking.entities.InventoryEntity;
import ca.ulaval.glo4002.booking.parsers.InventoryParser;
import ca.ulaval.glo4002.booking.repositories.InventoryRepository;

public class InventoryServiceContext {

	private static final LocalDate A_VALID_DATE = LocalDate.of(2050, 6, 20);
	private static final LocalDate A_DATE_AFTER_THE_OTHER_ONE = A_VALID_DATE.plusDays(20);

	private InventoryEntity anInventoryEntity;
	public InventoryService subject;
	public InventoryParser parser = new InventoryParser();
	public InventoryRepository repository;
	public Inventory anInventory;
	public OxygenTank anOxygenTank;

	public InventoryServiceContext() {
		setUpObjects();
		setUpRepository();
		setUpSubject();
	}

	private void setUpObjects() {
		OxygenCategoryBuilder categoryBuilder = new OxygenCategoryBuilder();

		anInventory = new Inventory();

		anOxygenTank = new OxygenTank(categoryBuilder.buildById(OxygenConstants.Categories.E_ID), A_VALID_DATE,
				A_DATE_AFTER_THE_OTHER_ONE);

		anInventoryEntity = parser.toEntity(anInventory);
	}

	private void setUpRepository() {
		repository = mock(InventoryRepository.class);

		when(repository.findAll()).thenReturn(Collections.singletonList(anInventoryEntity));
		when(repository.save(any(InventoryEntity.class))).thenReturn(anInventoryEntity);
	}

	private void setUpSubject() {
		subject = new InventoryServiceImpl(repository);
	}

	// TODO : Setup for save
	public void setUpForSave() {

	}
}
