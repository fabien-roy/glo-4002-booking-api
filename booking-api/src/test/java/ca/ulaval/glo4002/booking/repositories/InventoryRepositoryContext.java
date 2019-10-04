package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.constants.RepositoryConstants;
import ca.ulaval.glo4002.booking.entities.InventoryEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InventoryRepositoryContext {

	public EntityManager entityManager;

	private Long AN_INVENTORY_ID = 1L;
	private Long A_NON_EXISTENT_INVENTORY_ID = 2L;
	public InventoryEntity anInventory;
	public InventoryEntity aNonExistentInventory;

	public InventoryRepositoryContext() {
		this.setUpInventory();
		this.setUpEntityManager();
	}

	private void setUpInventory() {
		anInventory = new InventoryEntity(AN_INVENTORY_ID);
		aNonExistentInventory = new InventoryEntity(A_NON_EXISTENT_INVENTORY_ID);
	}

	private void setUpEntityManager() {
		entityManager = mock(EntityManager.class);
		TypedQuery<InventoryEntity> createQuery = mock(TypedQuery.class);

		when(createQuery.getResultList()).thenReturn(Collections.singletonList(anInventory));
		when(entityManager.createQuery(RepositoryConstants.INVENTORY_FIND_ALL_QUERY, InventoryEntity.class)).thenReturn(createQuery);
	}

	public void setUpEntityManagerForSave() {
		EntityTransaction transaction = mock(EntityTransaction.class);
		TypedQuery<InventoryEntity> createQuery = mock(TypedQuery.class);
		when(entityManager.getTransaction()).thenReturn(transaction);

		aNonExistentInventory.setId(null);
		when(createQuery.getResultList()).thenReturn(Collections.singletonList(aNonExistentInventory));
		when(entityManager.createQuery(RepositoryConstants.INVENTORY_FIND_ALL_QUERY, InventoryEntity.class)).thenReturn(createQuery);
	}
}
