package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.domainobjects.report.History;
import ca.ulaval.glo4002.booking.dto.HistoryItemDto;
import ca.ulaval.glo4002.booking.exceptions.UnusedMethodException;

import java.util.ArrayList;
import java.util.List;

public class HistoryParser implements DtoParser<History, List<HistoryItemDto>>{

    // TODO : OXY : Will we really have to parse a History Dto??
    @Override
    public History parseDto(List<HistoryItemDto> dto) {
        throw new UnusedMethodException();
    }

    @Override
    public List<HistoryItemDto> toDto(History history) {
        // TODO : OXY

        return new ArrayList<>();
    }
}
