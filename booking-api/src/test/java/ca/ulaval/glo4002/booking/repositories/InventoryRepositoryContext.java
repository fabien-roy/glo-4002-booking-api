package ca.ulaval.glo4002.booking.repositories;

import static org.mockito.Mockito.*;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ca.ulaval.glo4002.booking.constants.RepositoryConstants;
import ca.ulaval.glo4002.booking.entities.InventoryEntity;

public class InventoryRepositoryContext {
	public EntityManager entityManager;
	public Long AN_INVENTORY_ID = 1L;
	public Long AN_OTHER_INVENTORY_ID = 2L;
	public Long NON_EXISTENT_INVENTORY_ID = 0L;
	public InventoryEntity AN_INVENTORY;
	public InventoryEntity AN_OTHER_INVENTORY;
	public InventoryEntity NON_EXISTENT_INVENTORY;

	public InventoryRepositoryContext() {
		this.setUpInventory();
		this.setUpEntityManager();
	}

	private void setUpInventory() {
		this.AN_INVENTORY = new InventoryEntity(this.AN_INVENTORY_ID);
		this.AN_OTHER_INVENTORY = new InventoryEntity(this.AN_OTHER_INVENTORY_ID);
		this.NON_EXISTENT_INVENTORY = new InventoryEntity(this.NON_EXISTENT_INVENTORY_ID);
	}

	private void setUpEntityManager() {
		entityManager = mock(EntityManager.class);
		TypedQuery<InventoryEntity> createQuery = mock(TypedQuery.class);

		when(createQuery.getResultList()).thenReturn(Arrays.asList(this.AN_INVENTORY, this.AN_OTHER_INVENTORY));
		when(entityManager.createQuery(RepositoryConstants.ORDER_FIND_ALL_QUERY, InventoryEntity.class))
				.thenReturn(createQuery);
		when(entityManager.find(InventoryEntity.class, AN_INVENTORY_ID)).thenReturn(this.AN_INVENTORY);
		when(entityManager.find(InventoryEntity.class, AN_OTHER_INVENTORY_ID)).thenReturn(this.AN_OTHER_INVENTORY);
		when(entityManager.find(InventoryEntity.class, NON_EXISTENT_INVENTORY_ID)).thenReturn(null);
	}

	public void setUpEntityManagerForSave() {
		when(entityManager.find(InventoryEntity.class, NON_EXISTENT_INVENTORY_ID)).thenReturn(NON_EXISTENT_INVENTORY);
	}
}
