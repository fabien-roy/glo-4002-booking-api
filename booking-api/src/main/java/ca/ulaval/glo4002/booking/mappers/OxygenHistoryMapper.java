package ca.ulaval.glo4002.booking.mappers;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenHistory;
import ca.ulaval.glo4002.booking.dto.oxygen.OxygenHistoryItemDto;

public class OxygenHistoryMapper {

    public List<OxygenHistoryItemDto> toDto(OxygenHistory history) {
        List<OxygenHistoryItemDto> itemDtos = new ArrayList<>();

        history.getHistoryItems().forEach((date, item) -> {
            OxygenHistoryItemDto itemDto = new OxygenHistoryItemDto(
                    date.toString(),
                    item.getQtyOxygenTankBought(),
                    item.getQtyWaterUsed(),
                    item.getQtyCandlesUsed(),
                    item.getQtyOxygenTankMade()
            );

            itemDtos.add(itemDto);
        });

        return itemDtos;
    }
}
