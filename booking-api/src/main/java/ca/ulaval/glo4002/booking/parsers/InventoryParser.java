package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.domainobjects.report.Inventory;
import ca.ulaval.glo4002.booking.dto.InventoryItemDto;

import java.util.ArrayList;
import java.util.List;

public class InventoryParser implements DtoParser<Inventory, List<InventoryItemDto>>{

    @Override
    public Inventory parseDto(List<InventoryItemDto> dto) {
        // TODO
        return null;
    }

    @Override
    public List<InventoryItemDto> toDto(Inventory object) {
        // TODO
        return new ArrayList<>();
    }
}
