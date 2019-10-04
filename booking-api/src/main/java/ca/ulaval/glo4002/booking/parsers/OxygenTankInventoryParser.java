package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.builders.oxygen.OxygenCategoryBuilder;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTankInventory;
import ca.ulaval.glo4002.booking.dto.InventoryItemDto;
import ca.ulaval.glo4002.booking.entities.OxygenTankInventoryEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryParser implements ToDtoParser<OxygenTankInventory, List<InventoryItemDto>>, EntityParser<OxygenTankInventory, OxygenTankInventoryEntity>  {

    private final OxygenCategoryBuilder oxygenCategoryBuilder = new OxygenCategoryBuilder();

    @Override
    public List<InventoryItemDto> toDto(OxygenTankInventory oxygenTankInventory) {
        List<InventoryItemDto> inventoryItemDtos = new ArrayList<>();

        oxygenTankInventory.getNotInUseTanks().forEach((categoryId, quantity) -> {
            Long totalTankCategory = quantity + oxygenTankInventory.getInUseTanksByCategoryId(categoryId);
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
    public OxygenTankInventory parseEntity(OxygenTankInventoryEntity entity) {
        Map<Long, Long> inUseTanks = new HashMap<>();
        Map<Long, Long> notInUseTanks = new HashMap<>();

        entity.getInUseTanks().forEach(inventoryItemEntity -> inUseTanks.put(inventoryItemEntity.getOxygenCategoryId(), inventoryItemEntity.getQuantity()));
        entity.getInUseTanks().forEach(inventoryItemEntity -> notInUseTanks.put(inventoryItemEntity.getOxygenCategoryId(), inventoryItemEntity.getQuantity()));

        return new OxygenTankInventory(inUseTanks, notInUseTanks);
    }

    @Override
    public OxygenTankInventoryEntity toEntity(OxygenTankInventory oxygenTankInventory) {
        List<InventoryItemEntity> inUseTanks = new ArrayList<>();
        List<InventoryItemEntity> notInUseTanks = new ArrayList<>();

        oxygenTankInventory.getInUseTanks().forEach((categoryId, quantity) -> {
            inUseTanks.add(new InventoryItemEntity(
                    categoryId,
                    oxygenTankInventory.getInUseTanks().get(categoryId)
            ));
        });

        oxygenTankInventory.getNotInUseTanks().forEach((categoryId, quantity) -> {
            notInUseTanks.add(new InventoryItemEntity(
                    categoryId,
                    oxygenTankInventory.getNotInUseTanks().get(categoryId)
            ));
        });

        return new OxygenTankInventoryEntity(inUseTanks, notInUseTanks);
    }
}