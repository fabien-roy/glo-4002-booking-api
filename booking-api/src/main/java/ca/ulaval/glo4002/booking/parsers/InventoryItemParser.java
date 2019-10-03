package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.builders.oxygen.OxygenCategoryBuilder;
import ca.ulaval.glo4002.booking.domainobjects.report.Inventory;
import ca.ulaval.glo4002.booking.dto.InventoryItemDto;
import ca.ulaval.glo4002.booking.entities.InventoryEntity;
import ca.ulaval.glo4002.booking.entities.InventoryItemEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryItemParser implements ToDtoParser<Inventory, List<InventoryItemDto>>, EntityParser<Inventory, InventoryEntity>  {

    @Override
    public List<InventoryItemDto> toDto(Inventory inventory) {
        List<InventoryItemDto> inventoryItemDtos = new ArrayList<>();
        OxygenCategoryBuilder categoryBuilder = new OxygenCategoryBuilder();

        inventory.getOxygenTanks().forEach((categoryId, quantity) -> {
            InventoryItemDto inventoryItemDto = new InventoryItemDto();
            inventoryItemDto.gradeTankOxygen = categoryBuilder.buildById(categoryId).getName();
            inventoryItemDto.quantity = quantity;
        });

        return inventoryItemDtos;
    }

    @Override
    public Inventory parseEntity(InventoryEntity entity) {
        Map<Long, Long> storedTanks = new HashMap<>();

        entity.getInventoryItems().forEach(inventoryItemEntity -> storedTanks.put(inventoryItemEntity.getOxygenCategoryId(), inventoryItemEntity.getQuantity()));

        return new Inventory(storedTanks);
    }

    @Override
    public InventoryEntity toEntity(Inventory inventory) {
        List<InventoryItemEntity> inventoryItems = new ArrayList<>();

        inventory.getOxygenTanks().forEach((categoryId, quantity) -> {
            inventoryItems.add(new InventoryItemEntity(
                    categoryId,
                    inventory.getOxygenTanks().get(categoryId)
            ));
        });

        return new InventoryEntity(inventoryItems);
    }
}