package ca.ulaval.glo4002.booking.mappers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        this.mapper = new OxygenHistoryMapper();
    }

    @Test
    void toDto_shouldReturnEmptyList_whenHistoryIsEmpty() {
        // TODO
    }

    @Test
    void toDto_shouldReturnASingleHistoryItem_whenHistoryHasASingleItem() {
        // TODO
    }

    @Test
    void toDto_shouldReturnMultipleHistoryItems_whenHistoryHasMultipleItems() {
        // TODO
    }

    @Test
    void toDto_shouldReturnHistoryItemsOrderedByDate() {
        // TODO
    }
}
