package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.domainobjects.report.History;
import ca.ulaval.glo4002.booking.dto.HistoryItemDto;

import java.util.ArrayList;
import java.util.List;

public class HistoryParser implements DtoParser<History, List<HistoryItemDto>>{

    @Override
    public History parseDto(List<HistoryItemDto> dto) {
        // TODO
        return null;
    }

    @Override
    public List<HistoryItemDto> toDto(History object) {
        // TODO
        return new ArrayList<>();
    }
}
