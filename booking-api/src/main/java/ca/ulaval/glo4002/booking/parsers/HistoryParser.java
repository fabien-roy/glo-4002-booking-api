package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.productions.OxygenProduction;
import ca.ulaval.glo4002.booking.domainobjects.report.History;
import ca.ulaval.glo4002.booking.dto.HistoryItemDto;

import java.time.LocalDate;
import java.util.*;

public class HistoryParser implements ToDtoParser<History, List<HistoryItemDto>>{

    @Override
    public List<HistoryItemDto> toDto(History history) {
        Map<LocalDate, HistoryItemDto> historyItemDtoMap = new TreeMap<>();

        history.getRequestedOxygenTanks().keySet().forEach(date -> {
            if (historyItemDtoMap.containsKey(date)) {
                historyItemDtoMap.put(date, buildRequestHistoryItemDto(historyItemDtoMap.get(date), history.getRequestedOxygenTanks().get(date)));
            } else {
                historyItemDtoMap.put(date, buildRequestHistoryItemDto(date, history.getRequestedOxygenTanks().get(date)));
            }
        });

        history.getProducedOxygenTanks().keySet().forEach(date -> {
            if (historyItemDtoMap.containsKey(date)) {
                historyItemDtoMap.put(date, buildProducedHistoryItemDto(historyItemDtoMap.get(date), history.getProducedOxygenTanks().get(date)));
            } else {
                historyItemDtoMap.put(date, buildProducedHistoryItemDto(date, history.getProducedOxygenTanks().get(date)));
            }
        });

        return new ArrayList<>(historyItemDtoMap.values());
    }

    private HistoryItemDto buildEmptyHistoryItemDto(LocalDate date) {
        HistoryItemDto historyItemDto = new HistoryItemDto();

        historyItemDto.date = date.toString();
        historyItemDto.qtyOxygenTankBought = 0L;
        historyItemDto.qtyCandlesUsed = 0L;
        historyItemDto.qtyWaterUsed = 0L;
        historyItemDto.qtyOxygenTankMade = 0L;

        return historyItemDto;
    }

    private HistoryItemDto buildRequestHistoryItemDto(LocalDate date, List<OxygenTank> oxygenTanks) {
        return buildRequestHistoryItemDto(buildEmptyHistoryItemDto(date), oxygenTanks);
    }

    private HistoryItemDto buildProducedHistoryItemDto(LocalDate date, List<OxygenTank> oxygenTanks) {
        return buildProducedHistoryItemDto(buildEmptyHistoryItemDto(date), oxygenTanks);
    }

    private HistoryItemDto buildRequestHistoryItemDto(HistoryItemDto historyItemDto, List<OxygenTank> oxygenTanks) {
        oxygenTanks.forEach(oxygenTank -> {
            OxygenProduction oxygenProduction = oxygenTank.getCategory().getProduction();

            historyItemDto.qtyOxygenTankBought += oxygenProduction.getProducedTanks();

            if (oxygenProduction.getUnitType().getId().equals(OxygenConstants.UnitTypes.SPARK_PLUGS_ID)) {
                historyItemDto.qtyCandlesUsed += oxygenProduction.getProducedUnits();
            } else if (oxygenProduction.getUnitType().getId().equals(OxygenConstants.UnitTypes.WATER_LITERS_ID)) {
                historyItemDto.qtyWaterUsed += oxygenProduction.getProducedUnits();
            }
        });

        return historyItemDto;
    }

    private HistoryItemDto buildProducedHistoryItemDto(HistoryItemDto historyItemDto, List<OxygenTank> oxygenTanks) {
        oxygenTanks.forEach(oxygenTank -> {
            OxygenProduction oxygenProduction = oxygenTank.getCategory().getProduction();

            historyItemDto.qtyOxygenTankMade += oxygenProduction.getProducedTanks();
        });

        return historyItemDto;
    }
}
