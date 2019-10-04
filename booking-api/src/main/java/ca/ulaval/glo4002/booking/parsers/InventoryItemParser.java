package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.builders.oxygen.OxygenCategoryBuilder;
import ca.ulaval.glo4002.booking.domainobjects.report.Inventory;
import ca.ulaval.glo4002.booking.domainobjects.report.InventoryItem;
import ca.ulaval.glo4002.booking.dto.InventoryItemDto;
import ca.ulaval.glo4002.booking.entities.InventoryItemEntity;

import java.util.ArrayList;
import java.util.List;

public class InventoryItemParser implements ToDtoParser<Inventory, List<InventoryItemDto>>, EntityParser<InventoryItem, InventoryItemEntity>  {

    private final OxygenCategoryBuilder oxygenCategoryBuilder = new OxygenCategoryBuilder();

    @Override
    public List<InventoryItemDto> toDto(Inventory inventory) {
        List<InventoryItemDto> inventoryItemDtos = new ArrayList<>();

        inventory.getNotInUseTanks().forEach((categoryId, quantity) -> {
            Long totalTankCategory = quantity + inventory.getInUseTanksByCategoryId(categoryId);
            if(totalTankCategory != 0) {
                InventoryItemDto inventoryItemDto = new InventoryItemDto();
                inventoryItemDto.gradeTankOxygen = oxygenCategoryBuilder.buildById(categoryId).getName();
                inventoryItemDto.quantity = totalTankCategory;
                inventoryItemDtos.add(inventoryItemDto);
            }
        });

        return inventoryItemDtos;
    }

    @Override
    public InventoryItem parseEntity(InventoryItemEntity entity) {
        return new InventoryItem(entity.getId(), entity.getQuantity(), entity.getOxygenCategoryId());
    }

    @Override
    public InventoryItemEntity toEntity(InventoryItem inventoryItem) {
        return new InventoryItemEntity(inventoryItem.getId(), inventoryItem.getOxygenCategoryId(), inventoryItem.getQuantity());
    }

}