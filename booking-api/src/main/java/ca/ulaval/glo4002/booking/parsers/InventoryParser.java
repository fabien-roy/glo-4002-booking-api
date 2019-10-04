package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.builders.oxygen.OxygenCategoryBuilder;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.categories.OxygenCategory;
import ca.ulaval.glo4002.booking.domainobjects.report.Inventory;
import ca.ulaval.glo4002.booking.dto.InventoryItemDto;
import ca.ulaval.glo4002.booking.entities.InventoryEntity;
import ca.ulaval.glo4002.booking.entities.InventoryItemEntity;
import ca.ulaval.glo4002.booking.exceptions.UnusedMethodException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryParser implements DtoParser<Inventory, List<InventoryItemDto>>, EntityParser<Inventory, InventoryEntity>  {

    @Override
    public Inventory parseDto(List<InventoryItemDto> dto) {
        throw new UnusedMethodException();
    }

    @Override
    public List<InventoryItemDto> toDto(Inventory inventory) {
        // TODO : Refactoring, try to not use OxygenCategoryBuilder
        List<InventoryItemDto> inventoryItemDtos = new ArrayList<>();

        inventory.getStoredTanks().forEach((categoryId, storedTank) -> {
            if(storedTank > 0) {
                OxygenCategoryBuilder builder = new OxygenCategoryBuilder();
                OxygenCategory category = builder.buildById(categoryId);
                InventoryItemDto inventoryItemDto = new InventoryItemDto();

                inventoryItemDto.gradeTankOxygen = category.getName();
                inventoryItemDto.quantity = storedTank;

                inventoryItemDtos.add(inventoryItemDto);
            }
        });

        return inventoryItemDtos;
    }

    @Override
    public Inventory parseEntity(InventoryEntity entity) {
        Map<Long, Long> storedTanks = new HashMap<>();
        Map<Long, Long> inUseTanks = new HashMap<>();

        if (!entity.getInventoryItems().isEmpty()) {
            entity.getInventoryItems().forEach(inventoryItemEntity -> {
                storedTanks.put(inventoryItemEntity.getCategoryId(), inventoryItemEntity.getQuantityStored());
                inUseTanks.put(inventoryItemEntity.getCategoryId(), inventoryItemEntity.getQuantityInUse());
            });
        }

        return new Inventory(storedTanks, inUseTanks);
    }

    @Override
    public InventoryEntity toEntity(Inventory inventory) {
        List<InventoryItemEntity> inventoryItems = new ArrayList<>();

        inventory.getInUseTanks().forEach((categoryId, inUseTanks) ->  {
            InventoryItemEntity inventoryItem = new InventoryItemEntity(
                    categoryId,
                    inUseTanks,
                    inventory.getStoredTanksByCategoryId(categoryId)
            );

            inventoryItems.add(inventoryItem);
        });

        return new InventoryEntity(inventoryItems);
    }
}