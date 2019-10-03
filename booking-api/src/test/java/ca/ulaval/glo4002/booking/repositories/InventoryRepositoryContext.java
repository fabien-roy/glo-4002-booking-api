package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.constants.RepositoryConstants;
import ca.ulaval.glo4002.booking.entities.InventoryEntity;
import ca.ulaval.glo4002.booking.entities.InventoryItemEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InventoryRepositoryContext {

	public EntityManager entityManager;
	public InventoryEntity anInventory;
	public InventoryEntity aNonExistentInventory;

	private List<InventoryItemEntity> inUseTank;
	private List<InventoryItemEntity> notInUseTank;
	private InventoryItemEntity aValidInventoryItemCategoryA;
	private InventoryItemEntity aValidInventoryItemCategoryB;
	private InventoryItemEntity aValidInventoryItemCategoryE;

	private final static Long AN_INVENTORY_ID = 1L;
	private final static Long A_NON_EXISTENT_INVENTORY_ID = 2L;
	private static final Long A_VALID_NUMBER_OF_TANK_CATEGORY_A_STORED = 1L;
	private static final Long A_VALID_NUMBER_OF_TANK_CATEGORY_B_STORED = 2L;
	private static final Long A_VALID_NUMBER_OF_TANK_CATEGORY_E_STORED = 3L;

	public InventoryRepositoryContext() {
		setupInventoryItemList();
		setUpInventory();
		setUpEntityManager();
	}

	private void setupInventoryItemList() {
		aValidInventoryItemCategoryA =
				new InventoryItemEntity(OxygenConstants.Categories.A_ID, A_VALID_NUMBER_OF_TANK_CATEGORY_A_STORED);
		aValidInventoryItemCategoryA =
				new InventoryItemEntity(OxygenConstants.Categories.B_ID, A_VALID_NUMBER_OF_TANK_CATEGORY_B_STORED);
		aValidInventoryItemCategoryA =
				new InventoryItemEntity(OxygenConstants.Categories.A_ID, A_VALID_NUMBER_OF_TANK_CATEGORY_E_STORED);

		inUseTank.add(aValidInventoryItemCategoryA);
		inUseTank.add(aValidInventoryItemCategoryB);
		inUseTank.add(aValidInventoryItemCategoryE);

		notInUseTank.add(aValidInventoryItemCategoryA);
		notInUseTank.add(aValidInventoryItemCategoryB);
		notInUseTank.add(aValidInventoryItemCategoryE);
	}

	private void setUpInventory() {
		anInventory = new InventoryEntity(AN_INVENTORY_ID, inUseTank, notInUseTank);
		aNonExistentInventory = new InventoryEntity(A_NON_EXISTENT_INVENTORY_ID, inUseTank, notInUseTank);
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
