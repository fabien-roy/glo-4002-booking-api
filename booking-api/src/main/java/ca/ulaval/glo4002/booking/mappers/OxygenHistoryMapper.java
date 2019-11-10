package ca.ulaval.glo4002.booking.mappers;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenHistory;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.dto.oxygen.OxygenHistoryItemDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class OxygenHistoryMapper {



	public List<OxygenHistoryItemDto> toDto(OxygenHistory oxygenHistory) {
		Map<LocalDate, OxygenHistoryItemDto> oxygenHistoryItemDto = new TreeMap<>();

		return null;
		/*List<OxygenHistoryItemDto> history = new ArrayList<>();

		oxygenHistory.getRequestedOxygenTanks().forEach((requestedDate, oxygenTanks) -> {
			OxygenHistoryItemDto itemDto = buildItemDto(requestedDate, oxygenTanks);

			history.add(itemDto);
		});

		return history;*/
	}

	// TODO : OXY : Need refactoring RequestDate is good For production unit but not for Tank quantity (except for E)
	private OxygenHistoryItemDto buildItemDto(LocalDate requestedDate, List<OxygenTank> oxygenTanks) {
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

		return new OxygenHistoryItemDto(
				requestedDate.toString(),
				qtyOxygenTankBought,
				qtyWaterUsed,
				qtyCandlesUsed,
				qtyOxygenTankMade.longValue()
		);
	}
}
