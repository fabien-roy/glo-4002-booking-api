package ca.ulaval.glo4002.booking.mappers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.ulaval.glo4002.booking.domain.oxygen.History;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.dto.oxygen.HistoryItemDto;

public class HistoryItemMapper {

	public List<HistoryItemDto> toDto(History history) {
		List<HistoryItemDto> historyList = new ArrayList<HistoryItemDto>();

		/*
		 * for (Map.Entry<LocalDate, List<OxygenTank>> entry :
		 * history.getProducedOxygenTanks().entrySet()) {
		 * 
		 */
		/*
		 * LocalDate date = entry.getKey(); List<OxygenTank> tanks = entry.getValue();
		 * Long qtyOxygenTankBought = 0L; Long qtyWaterUsed = 0L; Long qtyCandlesUsed =
		 * 0L; Long qtyOxygenTankMade = new Long(tanks.size()); tanks.forEach(tank -> {
		 * // Calculate number of ressources }); HistoryItemDto dto = new
		 * HistoryItemDto(date, qtyOxygenTankBought, qtyWaterUsed, qtyCandlesUsed,
		 * qtyOxygenTankMade);
		 */

		/*
		 * }
		 */
		Map<LocalDate, List<OxygenTank>> requestedOxygenTanks = history.getRequestedOxygenTanks();
		for (Map.Entry<LocalDate, List<OxygenTank>> entry : requestedOxygenTanks.entrySet()) {
			LocalDate date = entry.getKey();
			Integer qtyOxygenTankBought = 0;
			Integer qtyWaterUsed = 0;
			Integer qtyCandlesUsed = 0;
			List<OxygenTank> tanks = entry.getValue();
			Integer qtyOxygenTankMade = tanks.size();
			tanks.forEach(tank -> {
				// calculate ressources
			});

			HistoryItemDto dto = new HistoryItemDto(date.toString(), qtyOxygenTankBought.longValue(),
					qtyWaterUsed.longValue(), qtyCandlesUsed.longValue(), qtyOxygenTankMade.longValue());
			historyList.add(dto);
		}

		Map<LocalDate, List<OxygenTank>> producedOxygenTanks = history.getProducedOxygenTanks();

		return historyList;
	}

}
