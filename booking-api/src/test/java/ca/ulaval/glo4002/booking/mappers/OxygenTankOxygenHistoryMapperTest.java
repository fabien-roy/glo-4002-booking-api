package ca.ulaval.glo4002.booking.mappers;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenHistory;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenInventory;
import ca.ulaval.glo4002.booking.enums.OxygenCategories;

import java.time.LocalDate;

class OxygenTankOxygenHistoryMapperTest {

    // TODO : Refactor OxygenTankHistoryMapper tests

	private static final OxygenCategories CATEGORY_A = OxygenCategories.A;
	private static final OxygenCategories CATEGORY_B = OxygenCategories.B;
	private static final OxygenCategories CATEGORY_E = OxygenCategories.E;

    private OxygenHistoryMapper mapper;
    private OxygenInventory mockedInventory;
    private OxygenHistory mockedOxygenHistory;
    private LocalDate date = LocalDate.of(2050, 7, 1);

   /* @BeforeEach
    void setUpMapper() {
        mapper = new OxygenHistoryMapper();
        mockedInventory = mock(OxygenInventory.class);
    }

    @BeforeEach
    void setupHistory() {
        List<OxygenTank> tanks = new ArrayList<>();
        OxygenTank tankA = mock(OxygenTank.class);
        when(tankA.getCategory()).thenReturn(CATEGORY_A);
        tanks.add(tankA);
        OxygenTank tankB = mock(OxygenTank.class);
        when(tankB.getCategory()).thenReturn(CATEGORY_B);
        tanks.add(tankB);
        OxygenTank tankE = mock(OxygenTank.class);
        when(tankE.getCategory()).thenReturn(CATEGORY_E);
        tanks.add(tankE);

        mockedOxygenHistory = mock(OxygenHistory.class);
        when(mockedOxygenHistory.getProducedOxygenTanksForDate(any())).thenReturn(tanks);

        Map<LocalDate, List<OxygenTank>> oxygenMap = new HashMap<>();
        oxygenMap.put(date, tanks);

        when(mockedOxygenHistory.getProducedOxygenTanks()).thenReturn(oxygenMap);
        when(mockedOxygenHistory.getRequestedOxygenTanks()).thenReturn(oxygenMap);
    }

    @Test
    void toDto_shouldBuildWithCorrectDate() {
        List<OxygenHistoryItemDto> items = mapper.toDto(mockedOxygenHistory);

        assertEquals(items.get(0).getDate(), date.toString());
    }

    @Test
    void toDto_shouldBuildWithCorrectQtyOxygenTankBought() {
        List<OxygenHistoryItemDto> items = mapper.toDto(mockedOxygenHistory);

        List<OxygenTank> tanks = mockedOxygenHistory.getProducedOxygenTanksForDate(date);
        // TODO not sure about this part
        int nbTankE = 0;
        for (int i = 0; i < tanks.size(); i++) {
            if (tanks.get(i).getCategory() == OxygenCategories.E) {
                nbTankE++;
            }
        }
        int nbRessource = OxygenTank.CATEGORY_E_NUMBER_OF_RESOURCES_NEEDED;
        int nbTankCreated = OxygenTank.CATEGORY_E_NUMBER_OF_TANKS_CREATED;
        // ---
        Integer expectedOxygenBought = (nbRessource / nbTankCreated) * nbTankE;

        assertEquals(expectedOxygenBought.longValue(), items.get(0).getQtyOxygenTankBought());
    }

    @Test
    void toDto_shouldBuildWithCorrectQtyWaterUsed() {
        List<OxygenHistoryItemDto> items = mapper.toDto(mockedOxygenHistory);

        List<OxygenTank> tanks = mockedOxygenHistory.getProducedOxygenTanksForDate(date);
        int nbTankB = 0;
        for (int i = 0; i < tanks.size(); i++) {
            if (tanks.get(i).getCategory() == OxygenCategories.B) {
                nbTankB++;
            }
        }
        int nbRessource = OxygenTank.CATEGORY_B_NUMBER_OF_RESOURCES_NEEDED;
        int nbTankCreated = OxygenTank.CATEGORY_B_NUMBER_OF_TANKS_CREATED;
        // ---
        Integer expectedWater = (nbRessource / nbTankCreated) * nbTankB;

        assertEquals(expectedWater.longValue(), items.get(0).getQtyWaterUsed());
    }

    @Test
    void toDto_shouldBuildWithCorrectQtyCandlesUsed() {
        List<OxygenHistoryItemDto> items = mapper.toDto(mockedOxygenHistory);

        List<OxygenTank> tanks = mockedOxygenHistory.getProducedOxygenTanksForDate(date);
        int nbTankA = 0;
        for (int i = 0; i < tanks.size(); i++) {
            if (tanks.get(i).getCategory() == OxygenCategories.A) {
                nbTankA++;
            }
        }
        int nbRessource = OxygenTank.CATEGORY_A_NUMBER_OF_RESOURCES_NEEDED;
        int nbTankCreated = OxygenTank.CATEGORY_A_NUMBER_OF_TANKS_CREATED;
        // ---
        Integer expectedCandle = (nbRessource / nbTankCreated) * nbTankA;

        assertEquals(expectedCandle.longValue(), items.get(0).getQtyCandlesUsed());
    }

    @Test
    void toDto_shouldBuildWithCorrectQtyOxygenTankMade() {
        List<OxygenHistoryItemDto> items = mapper.toDto(mockedOxygenHistory);

        Integer qtyOxygen = mockedOxygenHistory.getProducedOxygenTanksForDate(date).size();

        assertEquals(qtyOxygen.longValue(), items.get(0).getQtyOxygenTankMade());
    }

    private void fillInventory(OxygenInventory mockedInventory) {
        // TODO test this (and implement it)
    }*/
}
