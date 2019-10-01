package ca.ulaval.glo4002.booking.parsers;

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

    // TODO : Test
    @Override
    public Inventory parseDto(List<InventoryItemDto> dto) {
        throw new UnusedMethodException();
    }

    @Override
    public List<InventoryItemDto> toDto(Inventory object) {
        // TODO

        List<InventoryItemDto> inventoryItemDtos = new ArrayList<>();

        //object.getStoredTanks().forEach(item -> item.);

        return null;
    }

    // TODO : Test
    @Override
    public Inventory parseEntity(InventoryEntity entity) {
        Map<Long, Long> storedTanks = new HashMap<>();
        Map<Long, Long> inUseTanks = new HashMap<>();

        entity.getInventoryItems().forEach(inventoryItemEntity -> {
            storedTanks.put(inventoryItemEntity.getCategoryId(), inventoryItemEntity.getQuantityStored());
            inUseTanks.put(inventoryItemEntity.getCategoryId(), inventoryItemEntity.getQuantityInUse());
        });

        return new Inventory(storedTanks, inUseTanks);
    }

    // TODO : Test
    @Override
    public InventoryEntity toEntity(Inventory inventory) {
        List<InventoryItemEntity> inventoryItems = new ArrayList<>();

        inventory.getInUseTanks().forEach((categoryId, inUseTanks) -> inventoryItems.add(
                new InventoryItemEntity(
                    categoryId,
                    inUseTanks,
                    inventory.getStoredTanksByCategoryId(categoryId)
                )
        ));

        return new InventoryEntity(inventoryItems);
    }
}
