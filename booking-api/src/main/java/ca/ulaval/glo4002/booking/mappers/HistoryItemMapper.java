package ca.ulaval.glo4002.booking.mappers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.ulaval.glo4002.booking.domain.oxygen.History;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.dto.HistoryItemDto;

public class HistoryItemMapper {

	public List<HistoryItemDto> toDto(History history) {
		List<HistoryItemDto> historyList = new ArrayList<>();

		for (Map.Entry<LocalDate, List<OxygenTank>> entry : history.getProducedOxygenTanks().entrySet()) {
			String date = entry.getKey().toString();
			List<OxygenTank> tanks = entry.getValue();
			Long qtyOxygenTankBought = 0L;
			Long qtyWaterUsed = 0L;
			Long qtyCandlesUsed = 0L;
			Long qtyOxygenTankMade = new Long(tanks.size());
			tanks.forEach(tank -> {
				// Calculate number of ressources
			});
			HistoryItemDto dto = new HistoryItemDto(date, qtyOxygenTankBought, qtyWaterUsed, qtyCandlesUsed,
					qtyOxygenTankMade);
		}

		for (Map.Entry<LocalDate, List<OxygenTank>> entry : history.getRequestedOxygenTanks().entrySet()) {
			String date = entry.getKey().toString();
			List<OxygenTank> tanks = entry.getValue();
			Long qtyOxygenTankBought = 0L;
			Long qtyWaterUsed = 0L;
			Long qtyCandlesUsed = 0L;
			Long qtyOxygenTankMade = new Long(tanks.size());
			tanks.forEach(tank -> {
				// Calculate number of ressources
			});
			HistoryItemDto dto = new HistoryItemDto(date, qtyOxygenTankBought, qtyWaterUsed, qtyCandlesUsed,
					qtyOxygenTankMade);
		}

		return historyList;
	}

}
