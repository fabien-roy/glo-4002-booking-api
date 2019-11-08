package ca.ulaval.glo4002.booking.mappers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.ulaval.glo4002.booking.domain.oxygen.History;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.dto.oxygen.HistoryItemDto;
import ca.ulaval.glo4002.booking.enums.OxygenCategories;

public class HistoryItemMapper {

	public List<HistoryItemDto> toDto(History history) {
		List<HistoryItemDto> historyList = new ArrayList<HistoryItemDto>();

		Map<LocalDate, List<OxygenTank>> requestedOxygenTanks = history.getRequestedOxygenTanks();
		for (Map.Entry<LocalDate, List<OxygenTank>> entry : requestedOxygenTanks.entrySet()) {
			LocalDate date = entry.getKey();
			Integer qtyOxygenTankBought = 0;
			Integer qtyWaterUsed = 0;
			Integer qtyCandlesUsed = 0;
			List<OxygenTank> tanks = entry.getValue();
			Integer qtyOxygenTankMade = tanks.size();
			for (int i = 0; i < tanks.size(); i++) {
				if (tanks.get(i).getCategory() == OxygenCategories.E) {
					int nbRessource = OxygenTank.CATEGORY_E_NUMBER_OF_RESOURCES_NEEDED;
					int nbTank = OxygenTank.CATEGORY_E_NUMBER_OF_TANKS_CREATED;
					qtyOxygenTankBought += nbRessource / nbTank;
				} else if (tanks.get(i).getCategory() == OxygenCategories.B) {
					int nbRessource = OxygenTank.CATEGORY_B_NUMBER_OF_RESOURCES_NEEDED;
					int nbTank = OxygenTank.CATEGORY_B_NUMBER_OF_TANKS_CREATED;
					qtyWaterUsed += nbRessource / nbTank;
				} else if (tanks.get(i).getCategory() == OxygenCategories.A) {
					int nbRessource = OxygenTank.CATEGORY_A_NUMBER_OF_RESOURCES_NEEDED;
					int nbTank = OxygenTank.CATEGORY_A_NUMBER_OF_TANKS_CREATED;
					qtyCandlesUsed += nbRessource / nbTank;
				}
			}

			HistoryItemDto dto = new HistoryItemDto(date.toString(), qtyOxygenTankBought.longValue(),
					qtyWaterUsed.longValue(), qtyCandlesUsed.longValue(), qtyOxygenTankMade.longValue());
			historyList.add(dto);
		}

		return historyList;
	}

}
