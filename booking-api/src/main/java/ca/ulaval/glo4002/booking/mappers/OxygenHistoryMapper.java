package ca.ulaval.glo4002.booking.mappers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenHistory;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.dto.oxygen.OxygenHistoryItemDto;

public class OxygenHistoryMapper {

	public List<OxygenHistoryItemDto> toDto(OxygenHistory oxygenHistory) {
		OxygenHistoryItemMapper mapper = new OxygenHistoryItemMapper();
		List<OxygenHistoryItemDto> historyDto = new ArrayList<>();

		oxygenHistory.getHistoryItems()
				.forEach(oxygenHistoryItem -> historyDto.add(mapper.toDto(oxygenHistoryItem)));

		return historyDto;

		/*
		 * Map<LocalDate, OxygenHistoryItemDto> oxygenHistoryItemDto = new TreeMap<>();
		 * 
		 * 
		 * List<OxygenHistoryItemDto> history = new ArrayList<>();
		 * 
		 * oxygenHistory.getRequestedOxygenTanks().forEach((requestedDate, oxygenTanks)
		 * -> { OxygenHistoryItemDto itemDto = buildItemDto(requestedDate, oxygenTanks);
		 * 
		 * history.add(itemDto); });
		 * 
		 * return history;
		 */
	}

	/*
	 * TODO : OXY : Need refactoring RequestDate is good For production unit but not
	 * for Tank quantity (except for E) This logic is now in OxygenHistory and
	 * OxygenHistoryItem not sure if OxygenHistoryItem is needed
	 * 
	 * OxygenHistoryItem is easier/cleaner to test ans more similar to the domain,
	 * but still dont know if its needed its maybe only easier
	 */
	private OxygenHistoryItemDto buildItemDto(LocalDate requestedDate, List<OxygenTank> oxygenTanks) {
		Integer qtyOxygenTankBought = 0;
		Integer qtyWaterUsed = 0;
		Integer qtyCandlesUsed = 0;
		Integer qtyOxygenTankMade = oxygenTanks.size();

		for (OxygenTank oxygenTank : oxygenTanks) {
			Integer nbResource;
			Integer nbTank;

			switch (oxygenTank.getCategory()) {
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

		return new OxygenHistoryItemDto(requestedDate.toString(), qtyOxygenTankBought, qtyWaterUsed, qtyCandlesUsed,
				qtyOxygenTankMade);
	}
}
