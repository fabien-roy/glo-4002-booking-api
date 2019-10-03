package ca.ulaval.glo4002.booking.endToEnd;


import ca.ulaval.glo4002.booking.dto.InventoryItemDto;
import ca.ulaval.glo4002.booking.dto.ReportDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventoryEndToEndTest {

    private InventoryEndToEndContext context;

    @BeforeEach
    void setup() {
        context = new InventoryEndToEndContext();
    }

    @Test
    public void getO2_shouldReturnCorrctInventoryDto() {
        context.setup().withInventory();

        ResponseEntity<ReportDto> response = (ResponseEntity<ReportDto>) context.reportController.getOxygenTanks();
        List<InventoryItemDto> dtos = response.getBody().inventory;

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatusCodeValue());
        assertEquals(3, response.getBody().inventory.size());

        assertEquals(dtos.get(0).quantity, context.firstInventoryItemDto.quantity);
        assertEquals(dtos.get(0).gradeTankOxygen, context.firstInventoryItemDto.gradeTankOxygen);

        assertEquals(dtos.get(1).quantity, context.secondInventoryItemDto.quantity);
        assertEquals(dtos.get(1).gradeTankOxygen, context.secondInventoryItemDto.gradeTankOxygen);

        assertEquals(dtos.get(2).quantity, context.thirdInventoryItemDto.quantity);
        assertEquals(dtos.get(2).gradeTankOxygen, context.thirdInventoryItemDto.gradeTankOxygen);
    }
}
