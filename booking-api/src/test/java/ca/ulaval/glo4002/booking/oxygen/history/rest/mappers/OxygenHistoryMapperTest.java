package ca.ulaval.glo4002.booking.oxygen.history.rest.mappers;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.oxygen.history.domain.OxygenHistory;
import ca.ulaval.glo4002.booking.oxygen.history.rest.OxygenHistoryItemResponse;
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
    void toResponse_shouldReturnEmptyList_whenHistoryIsEmpty() {
        List<OxygenHistoryItemResponse> itemResponses = mapper.toResponse(history);

        assertTrue(itemResponses.isEmpty());
    }

    @Test
    void toResponse_shouldReturnHistoryItemWithCorrectMadeTanks() {
        Integer amountOfTanksMade = 1;
        LocalDate date = FestivalConfiguration.getDefaultStartEventDate().getValue();
        history.addMadeTanks(date, amountOfTanksMade);

        List<OxygenHistoryItemResponse> itemResponses = mapper.toResponse(history);

        assertEquals(amountOfTanksMade, itemResponses.get(0).getQtyOxygenTankMade());
    }

    @Test
    void toResponse_shouldReturnHistoryItemWithCorrectBoughtTanks() {
        Integer amountOfTanksBought = 1;
        LocalDate date = FestivalConfiguration.getDefaultStartEventDate().getValue();
        history.addTanksBought(date, amountOfTanksBought);

        List<OxygenHistoryItemResponse> itemResponses = mapper.toResponse(history);

        assertEquals(amountOfTanksBought, itemResponses.get(0).getQtyOxygenTankBought());
    }

    @Test
    void toResponse_shouldReturnHistoryItemWithCorrectWaterUsed() {
        Double amountOfWaterUsed = 1D;
        LocalDate date = FestivalConfiguration.getDefaultStartEventDate().getValue();
        history.addWaterUsed(date, amountOfWaterUsed);

        List<OxygenHistoryItemResponse> itemResponses = mapper.toResponse(history);

        assertEquals(amountOfWaterUsed.intValue(), itemResponses.get(0).getQtyWaterUsed());
    }

    @Test
    void toResponse_shouldReturnHistoryItemWithCorrectCandlesUsed() {
        Integer amountOfCandlesUsed = 1;
        LocalDate date = FestivalConfiguration.getDefaultStartEventDate().getValue();
        history.addCandlesUsed(date, amountOfCandlesUsed);

        List<OxygenHistoryItemResponse> itemResponses = mapper.toResponse(history);

        assertEquals(amountOfCandlesUsed, itemResponses.get(0).getQtyCandlesUsed());
    }

    @Test
    void toResponse_shouldReturnMultipleHistoryItems_whenHistoryHasMultipleItems() {
        LocalDate date = FestivalConfiguration.getDefaultStartEventDate().getValue();
        LocalDate anotherDate = date.plusDays(1);
        int amountOfTanksMade = 1;
        int amountOfTanksBought = 2;
        history.addMadeTanks(date, amountOfTanksMade);
        history.addTanksBought(anotherDate, amountOfTanksBought);

        List<OxygenHistoryItemResponse> itemResponses = mapper.toResponse(history);

        assertEquals(amountOfTanksMade, itemResponses.get(0).getQtyOxygenTankMade());
        assertEquals(amountOfTanksBought, itemResponses.get(1).getQtyOxygenTankBought());
    }

    @Test
    void toResponse_shouldReturnHistoryItemsOrderedByDate() {
        LocalDate firstDate = FestivalConfiguration.getDefaultStartEventDate().getValue();
        LocalDate secondDate = firstDate.plusDays(1);
        history.addMadeTanks(firstDate, 1);
        history.addMadeTanks(secondDate, 1);

        List<OxygenHistoryItemResponse> itemResponses = mapper.toResponse(history);

        assertEquals(firstDate.toString(), itemResponses.get(0).getDate());
        assertEquals(secondDate.toString(), itemResponses.get(1).getDate());
    }
}
