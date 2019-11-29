package ca.ulaval.glo4002.booking.oxygen.history.rest.mappers;

import ca.ulaval.glo4002.booking.oxygen.history.domain.OxygenHistory;
import ca.ulaval.glo4002.booking.oxygen.history.rest.OxygenHistoryItemDto;

import java.util.ArrayList;
import java.util.List;

public class OxygenHistoryMapper {

    public List<OxygenHistoryItemDto> toDto(OxygenHistory history) {
        List<OxygenHistoryItemDto> itemDtos = new ArrayList<>();

        history.getHistoryItems().forEach((date, item) -> {
            OxygenHistoryItemDto itemDto = new OxygenHistoryItemDto(
                    date.toString(),
                    item.getQtyOxygenTankBought(),
                    item.getQtyWaterUsed().intValue(),
                    item.getQtyCandlesUsed(),
                    item.getQtyOxygenTankMade()
            );

            itemDtos.add(itemDto);
        });

        return itemDtos;
    }
}
