package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.constants.RepositoryConstants;
import ca.ulaval.glo4002.booking.entities.InventoryItemEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InventoryItemRepositoryContext {

    public final static Long AN_INVENTORYITEM_ID = 1L;
    public final static Long ANOTHER_INVENTORYITEM_ID = 2L;
    public final static Long A_NON_EXISTENT_INVENTORYITEM_ID = 0L;


    public EntityManager entityManager;
    public InventoryItemEntity anInventoryItem;
    public InventoryItemEntity anotherInventoryItem;
    public InventoryItemEntity aNonExistentInventoryItem;

    private static final Long A_VALID_NUMBER_OF_TANK_CATEGORY_A_STORED = 1L;
    private static final Long A_VALID_NUMBER_OF_TANK_CATEGORY_B_STORED = 2L;
    private static final Long A_VALID_NUMBER_OF_TANK_CATEGORY_E_STORED = 3L;

    public InventoryItemRepositoryContext() {
        setUpInventoryItems();
        setupEntityManager();
    }

    private void setUpInventoryItems() {
        anInventoryItem = new InventoryItemEntity(OxygenConstants.Categories.A_ID,
                A_VALID_NUMBER_OF_TANK_CATEGORY_A_STORED);
        anotherInventoryItem = new InventoryItemEntity(OxygenConstants.Categories.B_ID,
                A_VALID_NUMBER_OF_TANK_CATEGORY_B_STORED);
        aNonExistentInventoryItem = new InventoryItemEntity(OxygenConstants.Categories.E_ID,
                A_VALID_NUMBER_OF_TANK_CATEGORY_E_STORED);
    }

    private void setupEntityManager() {
        entityManager = mock(EntityManager.class);
        TypedQuery<InventoryItemEntity> createQuery = mock(TypedQuery.class);

        when(createQuery.getResultList()).thenReturn(Arrays.asList(anInventoryItem, anotherInventoryItem));
        when(entityManager.createQuery(RepositoryConstants.INVENTORYITEM_FIND_ALL_QUERY, InventoryItemEntity.class)).thenReturn(createQuery);
        when(entityManager.find(InventoryItemEntity.class, AN_INVENTORYITEM_ID)).thenReturn(anInventoryItem);
        when(entityManager.find(InventoryItemEntity.class, ANOTHER_INVENTORYITEM_ID)).thenReturn(anotherInventoryItem);
        when(entityManager.find(InventoryItemEntity.class, A_NON_EXISTENT_INVENTORYITEM_ID)).thenReturn(null);
    }

    public void setupEntityManagerForSave() {
        EntityTransaction transaction = mock(EntityTransaction.class);
        when(entityManager.getTransaction()).thenReturn(transaction);

        aNonExistentInventoryItem.setId(null);
        when(entityManager.find(InventoryItemEntity.class, A_NON_EXISTENT_INVENTORYITEM_ID)).thenReturn(aNonExistentInventoryItem);
    }

}
