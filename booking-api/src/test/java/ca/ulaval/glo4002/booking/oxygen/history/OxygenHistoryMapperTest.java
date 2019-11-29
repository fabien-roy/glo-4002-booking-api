package ca.ulaval.glo4002.booking.oxygen.history;

import ca.ulaval.glo4002.booking.program.events.EventDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OxygenHistoryMapperTest {

    OxygenHistoryMapper mapper;
    OxygenHistory history;

    @BeforeEach
    void setUpMapper() {
        mapper = new OxygenHistoryMapper();
        history = new OxygenHistory();
    }

    @Test
    void toDto_shouldReturnEmptyList_whenHistoryIsEmpty() {
        List<OxygenHistoryItemDto> itemDtos = mapper.toDto(history);

        assertTrue(itemDtos.isEmpty());
    }

    @Test
    void toDto_shouldReturnHistoryItemWithCorrectMadeTanks() {
        Integer amountOfTanksMade = 1;
        LocalDate date = EventDate.getDefaultStartEventDate().getValue();
        history.addMadeTanks(date, amountOfTanksMade);

        List<OxygenHistoryItemDto> itemDtos = mapper.toDto(history);

        assertEquals(amountOfTanksMade, itemDtos.get(0).getQtyOxygenTankMade());
    }

    @Test
    void toDto_shouldReturnHistoryItemWithCorrectBoughtTanks() {
        Integer amountOfTanksBought = 1;
        LocalDate date = EventDate.getDefaultStartEventDate().getValue();
        history.addTanksBought(date, amountOfTanksBought);

        List<OxygenHistoryItemDto> itemDtos = mapper.toDto(history);

        assertEquals(amountOfTanksBought, itemDtos.get(0).getQtyOxygenTankBought());
    }

    @Test
    void toDto_shouldReturnHistoryItemWithCorrectWaterUsed() {
        Double amountOfWaterUsed = 1D;
        LocalDate date = EventDate.getDefaultStartEventDate().getValue();
        history.addWaterUsed(date, amountOfWaterUsed);

        List<OxygenHistoryItemDto> itemDtos = mapper.toDto(history);

        assertEquals(amountOfWaterUsed.intValue(), itemDtos.get(0).getQtyWaterUsed());
    }

    @Test
    void toDto_shouldReturnHistoryItemWithCorrectCandlesUsed() {
        Integer amountOfCandlesUsed = 1;
        LocalDate date = EventDate.getDefaultStartEventDate().getValue();
        history.addCandlesUsed(date, amountOfCandlesUsed);

        List<OxygenHistoryItemDto> itemDtos = mapper.toDto(history);

        assertEquals(amountOfCandlesUsed, itemDtos.get(0).getQtyCandlesUsed());
    }

    @Test
    void toDto_shouldReturnMultipleHistoryItems_whenHistoryHasMultipleItems() {
        LocalDate date = EventDate.getDefaultStartEventDate().getValue();
        LocalDate anotherDate = date.plusDays(1);
        history.addMadeTanks(date, 1);
        history.addTanksBought(anotherDate, 1);

        List<OxygenHistoryItemDto> itemDtos = mapper.toDto(history);

        assertEquals(2, itemDtos.size());
    }

    @Test
    void toDto_shouldReturnHistoryItemsOrderedByDate() {
        LocalDate firstDate = EventDate.getDefaultStartEventDate().getValue();
        LocalDate secondDate = firstDate.plusDays(1);
        history.addMadeTanks(firstDate, 1);
        history.addMadeTanks(secondDate, 1);

        List<OxygenHistoryItemDto> itemDtos = mapper.toDto(history);

        assertEquals(firstDate.toString(), itemDtos.get(0).getDate());
        assertEquals(secondDate.toString(), itemDtos.get(1).getDate());
    }
}
