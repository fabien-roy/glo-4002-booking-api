package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.builders.oxygen.OxygenCategoryBuilder;
import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domainobjects.report.Inventory;
import ca.ulaval.glo4002.booking.entities.InventoryEntity;
import ca.ulaval.glo4002.booking.parsers.InventoryParser;
import ca.ulaval.glo4002.booking.repositories.InventoryRepository;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InventoryServiceContext {

    public InventoryService subject;

    public InventoryParser parser = new InventoryParser();
    private static final LocalDate A_VALID_DATE = LocalDate.of(2050, 6, 20);
    private static final LocalDate A_DATE_AFTER_THE_OTHER_ONE = A_VALID_DATE.plusDays(20);

    public InventoryRepository repository;
    public Inventory anInventory;
    public OxygenTank anOxygenTank;
    private InventoryEntity anInventoryEntity;

    /*
    private final static LocalDateTime A_ORDER_EVENT_DATE = DateConstants.ORDER_START_DATE_TIME;
    private final static VendorBuilder vendorBuilder = new VendorBuilder();
    private OrderEntity anotherOrderEntity;
    private OrderEntity aNonExistentOrderEntity;
    public Order anotherOrder;
    public Order aNonExistentOrder;
    public final static Long A_ORDER_ID = 1L;
    public final static Long ANOTHER_ORDER_ID = 2L;
    public final static Long A_NON_EXISTANT_ORDER_ID = 0L;
    */

    public InventoryServiceContext() {
        setUpObjects();
        setUpRepository();
        setUpSubject();
    }

    private void setUpObjects() {
        OxygenCategoryBuilder categoryBuilder = new OxygenCategoryBuilder();

        anInventory = new Inventory();

        anOxygenTank = new OxygenTank(
                categoryBuilder.buildById(OxygenConstants.Categories.E_ID),
                A_VALID_DATE,
                A_DATE_AFTER_THE_OTHER_ONE
        );

        anInventoryEntity = parser.toEntity(anInventory);
    }

    private void setUpRepository() {
        repository = mock(InventoryRepository.class);

        when(repository.findAll()).thenReturn(Collections.singletonList(anInventoryEntity));
        when(repository.save(any(InventoryEntity.class))).thenReturn(anInventoryEntity);
        /*
        when(repository.findById(A_ORDER_ID)).thenReturn(Optional.of(aOrderEntity));
        when(repository.findById(ANOTHER_ORDER_ID)).thenReturn(Optional.of(anotherOrderEntity));
        when(repository.findById(A_NON_EXISTANT_ORDER_ID)).thenReturn(Optional.empty());
        when(repository.save(any(OrderEntity.class))).thenReturn(aOrderEntity);
        */
    }

    private void setUpSubject() {
        subject = new InventoryServiceImpl(repository);
    }

    // TODO : Setup for save
    public void setUpForSave() {
        //when(repository.findAll()).thenReturn()
    }
}
