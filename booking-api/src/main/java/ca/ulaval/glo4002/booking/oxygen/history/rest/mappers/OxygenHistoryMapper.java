package ca.ulaval.glo4002.booking.oxygen.history.rest.mappers;

import ca.ulaval.glo4002.booking.oxygen.history.domain.OxygenHistory;
import ca.ulaval.glo4002.booking.oxygen.history.rest.OxygenHistoryItemResponse;

import java.util.ArrayList;
import java.util.List;

public class OxygenHistoryMapper {

    public List<OxygenHistoryItemResponse> toResponse(OxygenHistory history) {
        List<OxygenHistoryItemResponse> itemResponses = new ArrayList<>();

        history.getHistoryItems().forEach((date, item) -> {
            OxygenHistoryItemResponse itemDto = new OxygenHistoryItemResponse(
                    date.toString(),
                    item.getQtyOxygenTankBought(),
                    item.getQtyWaterUsed().intValue(),
                    item.getQtyCandlesUsed(),
                    item.getQtyOxygenTankMade()
            );

            itemResponses.add(itemDto);
        });

        return itemResponses;
    }
}
