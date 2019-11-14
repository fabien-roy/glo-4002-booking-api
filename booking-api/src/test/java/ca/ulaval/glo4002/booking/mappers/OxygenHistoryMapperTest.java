package ca.ulaval.glo4002.booking.mappers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.booking.domain.events.EventDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenHistory;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenHistoryItem;
import ca.ulaval.glo4002.booking.dto.oxygen.OxygenHistoryItemDto;
import ca.ulaval.glo4002.booking.enums.OxygenCategories;

class OxygenHistoryMapperTest {

    // TODO : OxygenHistoryMapperTest

    OxygenHistoryMapper mapper;

    @BeforeEach
    void setUpMapper() {
        mapper = new OxygenHistoryMapper();
    }

    @Test
    void toDto_shouldReturnEmptyList_whenHistoryIsEmpty() {
        OxygenHistory history = new OxygenHistory();

        List<OxygenHistoryItemDto> itemDtos = mapper.toDto(history);

        assertTrue(itemDtos.isEmpty());
    }

    @Test
    void toDto_shouldReturnHistoryItemWithCorrectMadeTanks() {
        Integer amountOfTanksMade = 1;
        LocalDate date = EventDate.START_DATE;
        OxygenHistory history = new OxygenHistory();
        history.addMadeTanks(date, amountOfTanksMade);

        List<OxygenHistoryItemDto> itemDtos = mapper.toDto(history);

        assertEquals(amountOfTanksMade, itemDtos.get(0).getQtyOxygenTankMade());
    }

    @Test
    void toDto_shouldReturnHistoryItemWithCorrectBoughtTanks() {
        Integer amountOfTanksBought = 1;
        LocalDate date = EventDate.START_DATE;
        OxygenHistory history = new OxygenHistory();
        history.addTanksBought(date, amountOfTanksBought);

        List<OxygenHistoryItemDto> itemDtos = mapper.toDto(history);

        assertEquals(amountOfTanksBought, itemDtos.get(0).getQtyOxygenTankBought());
    }

    @Test
    void toDto_shouldReturnHistoryItemWithCorrectWaterUsed() {
        Integer amountOfWaterUsed = 1;
        LocalDate date = EventDate.START_DATE;
        OxygenHistory history = new OxygenHistory();
        history.addWaterUsed(date, amountOfWaterUsed);

        List<OxygenHistoryItemDto> itemDtos = mapper.toDto(history);

        assertEquals(amountOfWaterUsed, itemDtos.get(0).getQtyWaterUsed());
    }

    @Test
    void toDto_shouldReturnHistoryItemWithCorrectCandlesUsed() {
        Integer amountOfCandlesUsed = 1;
        LocalDate date = EventDate.START_DATE;
        OxygenHistory history = new OxygenHistory();
        history.addCandlesUsed(date, amountOfCandlesUsed);

        List<OxygenHistoryItemDto> itemDtos = mapper.toDto(history);

        assertEquals(amountOfCandlesUsed, itemDtos.get(0).getQtyCandlesUsed());
    }

    @Test
    void toDto_shouldReturnMultipleHistoryItems_whenHistoryHasMultipleItems() {
        LocalDate date = EventDate.START_DATE;
        LocalDate anotherDate = date.plusDays(1);
        OxygenHistory history = new OxygenHistory();
        history.addMadeTanks(date, 1);
        history.addTanksBought(anotherDate, 1);

        List<OxygenHistoryItemDto> itemDtos = mapper.toDto(history);

        assertEquals(2, itemDtos.size());
    }

    @Test
    void toDto_shouldReturnHistoryItemsOrderedByDate() {
        LocalDate firstDate = EventDate.START_DATE;
        LocalDate secondDate = firstDate.plusDays(1);
        OxygenHistory history = new OxygenHistory();
        history.addMadeTanks(firstDate, 1);
        history.addMadeTanks(secondDate, 1);

        List<OxygenHistoryItemDto> itemDtos = mapper.toDto(history);

        assertEquals(firstDate.toString(), itemDtos.get(0).getDate());
        assertEquals(secondDate.toString(), itemDtos.get(1).getDate());
    }
}
