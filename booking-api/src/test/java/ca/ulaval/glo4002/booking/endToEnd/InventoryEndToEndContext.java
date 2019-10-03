package ca.ulaval.glo4002.booking.endToEnd;

import ca.ulaval.glo4002.booking.builders.oxygen.OxygenCategoryBuilder;
import ca.ulaval.glo4002.booking.constants.DateConstants;
import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.constants.RepositoryConstants;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.categories.OxygenCategory;
import ca.ulaval.glo4002.booking.domainobjects.report.Inventory;
import ca.ulaval.glo4002.booking.dto.InventoryItemDto;
import ca.ulaval.glo4002.booking.dto.ReportDto;
import ca.ulaval.glo4002.booking.entities.OxygenTankEntity;
import ca.ulaval.glo4002.booking.repositories.InventoryItemRepository;
import ca.ulaval.glo4002.booking.repositories.InventoryRepository;
import ca.ulaval.glo4002.booking.repositories.OxygenTankRepository;
import ca.ulaval.glo4002.booking.repositories.OxygenTankRepositoryImpl;
import ca.ulaval.glo4002.booking.services.InventoryItemService;
import ca.ulaval.glo4002.booking.services.InventoryService;
import ca.ulaval.glo4002.booking.services.OxygenTankService;
import ca.ulaval.glo4002.booking.services.OxygenTankServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InventoryEndToEndContext {

    private static final LocalDate FIRST_DATE = DateConstants.START_DATE.minusDays(15);
    private static final LocalDate SECOND_DATE = DateConstants.START_DATE.minusDays(10);
    private static final LocalDate THIRD_DATE = DateConstants.START_DATE.minusDays(5);
    private static final LocalDate FOURTH_DATE = DateConstants.START_DATE;

    private EntityManager entityManager;
    public OxygenCategoryBuilder categoryBuilder = new OxygenCategoryBuilder();
    private ReportDto aReportDto = new ReportDto();

    public OxygenTankEntity aFirstAndEOxygenTank;
    public OxygenTankEntity aSecondAndBOxygenTank;
    public OxygenTankEntity aThirdAndAOxygenTank;
    public InventoryItemDto firstInventoryItemDto = new InventoryItemDto();
    public InventoryItemDto secondInventoryItemDto = new InventoryItemDto();
    public InventoryItemDto thirdInventoryItemDto = new InventoryItemDto();
    public InventoryItemDto fourthInventoryItemDto = new InventoryItemDto();

    public InventoryEndToEndContext() {

    }

    private void setUpObjects() {
        OxygenCategory oxygenCategoryE = categoryBuilder.buildById(OxygenConstants.Categories.E_ID);
        OxygenCategory oxygenCategoryB = categoryBuilder.buildById(OxygenConstants.Categories.B_ID);
        OxygenCategory oxygenCategoryA = categoryBuilder.buildById(OxygenConstants.Categories.A_ID);

        aFirstAndEOxygenTank = new OxygenTankEntity(
                oxygenCategoryE.getId(),
                FIRST_DATE,
                SECOND_DATE
        );

        aSecondAndBOxygenTank = new OxygenTankEntity(
                oxygenCategoryB.getId(),
                FIRST_DATE,
                THIRD_DATE
        );

        aThirdAndAOxygenTank = new OxygenTankEntity(
                oxygenCategoryA.getId(),
                SECOND_DATE,
                FOURTH_DATE
        );
    }

    private void settUpEntityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(RepositoryConstants.STAGING_PERSISTENCE_NAME);
        entityManager = entityManagerFactory.createEntityManager();
    }

    public InventoryEndToEndContext setup() {
        InventoryItemRepository inventoryItemRepository = mock(InventoryItemRepository.class);
        InventoryRepository inventoryRepository = mock(InventoryRepository.class);
        InventoryItemService inventoryItemService = mock(InventoryItemService.class);
        Inventory inventory = mock(Inventory.class);
        when(inventory.getNotInUseTanks()).thenReturn(new HashMap<>());

        OxygenTankRepository oxygenTankRepository = new OxygenTankRepositoryImpl(entityManager);
        OxygenTankService oxygenTankService = new OxygenTankServiceImpl(oxygenTankRepository, inventoryService);



        return this;
    }

    public InventoryEndToEndContext withInventory() {

        return this;
    }

    private Long addOxygenTank(OxygenTankEntity oxygenTank){
        entityManager.getTransaction().begin();
        entityManager.persist(oxygenTank);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        List<OxygenTankEntity> oxygenTanks = entityManager.createQuery(RepositoryConstants.OXYGEN_TANK_FIND_ALL_QUERY);
        Long oxygenTankId = oxygenTanks.get(0).getId();
        entityManager.getTransaction().commit();

        return oxygenTankId;
    }
}
