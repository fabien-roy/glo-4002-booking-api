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

public class InventoryParser implements ToDtoParser<Inventory, List<InventoryItemDto>>, EntityParser<Inventory, InventoryEntity>  {

    private OxygenCategoryBuilder oxygenCategoryBuilder = new OxygenCategoryBuilder();

    @Override
    public List<InventoryItemDto> toDto(Inventory inventory) {
        List<InventoryItemDto> inventoryItemDtos = new ArrayList<>();

        inventory.getInUseTanks().forEach((categoryId, quantity) -> {
            InventoryItemDto inventoryItemDto = new InventoryItemDto();
            inventoryItemDto.gradeTankOxygen = oxygenCategoryBuilder.buildById(categoryId).getName();
            inventoryItemDto.quantity = quantity;
        });

        return inventoryItemDtos;
    }

    @Override
    public Inventory parseEntity(InventoryEntity entity) {
        Map<Long, Long> inUseTanks = new HashMap<>();
        Map<Long, Long> notInUseTanks = new HashMap<>();

        entity.getInUseTanks().forEach(inventoryItemEntity -> inUseTanks.put(inventoryItemEntity.getOxygenCategoryId(), inventoryItemEntity.getQuantity()));
        entity.getInUseTanks().forEach(inventoryItemEntity -> notInUseTanks.put(inventoryItemEntity.getOxygenCategoryId(), inventoryItemEntity.getQuantity()));

        return new Inventory(inUseTanks, notInUseTanks);
    }

    @Override
    public InventoryEntity toEntity(Inventory inventory) {
        List<InventoryItemEntity> inUseTanks = new ArrayList<>();
        List<InventoryItemEntity> notInUseTanks = new ArrayList<>();

        inventory.getInUseTanks().forEach((categoryId, quantity) -> {
            inUseTanks.add(new InventoryItemEntity(
                    categoryId,
                    inventory.getInUseTanks().get(categoryId)
            ));
        });

        inventory.getNotInUseTanks().forEach((categoryId, quantity) -> {
            notInUseTanks.add(new InventoryItemEntity(
                    categoryId,
                    inventory.getNotInUseTanks().get(categoryId)
            ));
        });

        return new InventoryEntity(inUseTanks, notInUseTanks);
    }
}