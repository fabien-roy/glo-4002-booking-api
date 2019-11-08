package ca.ulaval.glo4002.booking.mappers;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenHistory;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.dto.oxygen.OxygenHistoryItemDto;

public class OxygenHistoryMapper {

	public List<OxygenHistoryItemDto> toDto(OxygenHistory oxygenHistory) {
		List<OxygenHistoryItemDto> historyList = new ArrayList<>();

		oxygenHistory.getRequestedOxygenTanks().forEach((requestedDate, oxygenTanks) -> {
			Long qtyOxygenTankBought = 0L;
			Long qtyWaterUsed = 0L;
			Long qtyCandlesUsed = 0L;
			Integer qtyOxygenTankMade = oxygenTanks.size();

			for(OxygenTank oxygenTank : oxygenTanks) {
			    Integer nbResource;
			    Integer nbTank;

				switch(oxygenTank.getCategory()) {
					case E:
						nbResource = OxygenTank.CATEGORY_E_NUMBER_OF_RESOURCES_NEEDED;
						nbTank = OxygenTank.CATEGORY_E_NUMBER_OF_TANKS_CREATED;

						qtyOxygenTankBought += nbResource / nbTank;
						break;
					case B:
						nbResource = OxygenTank.CATEGORY_B_NUMBER_OF_RESOURCES_NEEDED;
						nbTank = OxygenTank.CATEGORY_B_NUMBER_OF_TANKS_CREATED;

						qtyWaterUsed += nbResource / nbTank;
						break;
					default:
					case A:
						nbResource = OxygenTank.CATEGORY_A_NUMBER_OF_RESOURCES_NEEDED;
						nbTank = OxygenTank.CATEGORY_A_NUMBER_OF_TANKS_CREATED;

						qtyCandlesUsed += nbResource / nbTank;
						break;
				}
			}

			OxygenHistoryItemDto dto = new OxygenHistoryItemDto(
					requestedDate.toString(),
					qtyOxygenTankBought,
					qtyWaterUsed,
					qtyCandlesUsed,
					qtyOxygenTankMade.longValue()
			);

			historyList.add(dto);
		});

		return historyList;
	}

}
