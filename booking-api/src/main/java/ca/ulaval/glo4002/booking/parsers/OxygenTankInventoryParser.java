package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTankInventory;
import ca.ulaval.glo4002.booking.dto.InventoryItemDto;
import ca.ulaval.glo4002.booking.entities.OxygenTankEntity;
import ca.ulaval.glo4002.booking.entities.OxygenTankInventoryEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OxygenTankInventoryParser implements EntityParser<OxygenTankInventory, OxygenTankInventoryEntity>, ToDtoParser<OxygenTankInventory, List<InventoryItemDto>> {

    private OxygenTankParser oxygenTankParser = new OxygenTankParser();

    // TODO : OXY : Test
    @Override
    public OxygenTankInventory parseEntity(OxygenTankInventoryEntity entity) {
        List<OxygenTank> inUseTanks = new ArrayList<>();
        List<OxygenTank> notInUseTanks = new ArrayList<>();

        entity.getInUseTanks().forEach(tank -> inUseTanks.add(oxygenTankParser.parseEntity(tank)));
        entity.getNotInUseTanks().forEach(tank -> notInUseTanks.add(oxygenTankParser.parseEntity(tank)));

        return new OxygenTankInventory(entity.getId(), inUseTanks, notInUseTanks);
    }

    // TODO : OXY : Test
    @Override
    public OxygenTankInventoryEntity toEntity(OxygenTankInventory inventory) {
        List<OxygenTankEntity> inUseTanks = new ArrayList<>();
        List<OxygenTankEntity> notInUseTanks = new ArrayList<>();

        inventory.getInUseTanks().forEach(tank -> inUseTanks.add(oxygenTankParser.toEntity(tank)));
        inventory.getNotInUseTanks().forEach(tank -> notInUseTanks.add(oxygenTankParser.toEntity(tank)));

        return new OxygenTankInventoryEntity(inventory.getId(), inUseTanks, notInUseTanks);
    }

    // TODO : OXY : Test
    // TODO : OXY : This should not know oxygen qualities
    @Override
    public List<InventoryItemDto> toDto(OxygenTankInventory inventory) {
        InventoryItemDto eGradeDto = new InventoryItemDto();
        InventoryItemDto bGradeDto = new InventoryItemDto();
        InventoryItemDto aGradeDto = new InventoryItemDto();
        eGradeDto.gradeTankOxygen = OxygenConstants.Categories.E_NAME;
        bGradeDto.gradeTankOxygen = OxygenConstants.Categories.B_NAME;
        aGradeDto.gradeTankOxygen = OxygenConstants.Categories.A_NAME;
        eGradeDto.quantity = 0L;
        bGradeDto.quantity = 0L;
        aGradeDto.quantity = 0L;

        inventory.getNotInUseTanks().forEach(tank -> addQuantityToCategory(eGradeDto, bGradeDto, aGradeDto, tank));
        inventory.getInUseTanks().forEach(tank -> addQuantityToCategory(eGradeDto, bGradeDto, aGradeDto, tank));

        return new ArrayList<>(Arrays.asList(eGradeDto, bGradeDto, aGradeDto));
    }

    private void addQuantityToCategory(InventoryItemDto eGradeDto, InventoryItemDto bGradeDto, InventoryItemDto aGradeDto, OxygenTank tank) {
        switch (tank.getCategory().getName()) {
            case OxygenConstants.Categories.E_NAME:
                eGradeDto.quantity++;
                break;
            case OxygenConstants.Categories.B_NAME:
                bGradeDto.quantity++;
                break;
            case OxygenConstants.Categories.A_NAME:
                aGradeDto.quantity++;
                break;
        }
    }
}