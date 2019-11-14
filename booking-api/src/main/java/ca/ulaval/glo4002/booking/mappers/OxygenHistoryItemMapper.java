package ca.ulaval.glo4002.booking.mappers;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenHistoryItem;
import ca.ulaval.glo4002.booking.dto.oxygen.OxygenHistoryItemDto;

public class OxygenHistoryItemMapper {

    public OxygenHistoryItemDto toDto(OxygenHistoryItem oxygenHistoryItem) {
        String date = oxygenHistoryItem.getDate().toString();
        Integer qtyOxygenTankBought = oxygenHistoryItem.getQtyOxygenTankBought();
        Integer qtyWaterUsed = oxygenHistoryItem.getQtyWaterUsed();
        Integer qtyCandlesUsed = oxygenHistoryItem.getQtyCandlesUsed();
        Integer qtyOxygenTankMade = oxygenHistoryItem.getQtyOxygenTankMade();

        OxygenHistoryItemDto oxygenHistoryItemDto = new OxygenHistoryItemDto(date, qtyOxygenTankBought, qtyWaterUsed, qtyCandlesUsed, qtyOxygenTankMade);

        return oxygenHistoryItemDto;
    }
}
