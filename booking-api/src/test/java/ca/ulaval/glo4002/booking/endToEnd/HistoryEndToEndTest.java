package ca.ulaval.glo4002.booking.endToEnd;

import ca.ulaval.glo4002.booking.dto.HistoryItemDto;
import ca.ulaval.glo4002.booking.dto.ReportDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HistoryEndToEndTest {

    private HistoryEndToEndContext context;

    @BeforeEach
    void setUp(){
        context = new HistoryEndToEndContext();
    }

    @Test
    public void getO2_shouldReturnCorrectHistoryDto() {
        context.setUp().withHistory();

        ResponseEntity<ReportDto> response = (ResponseEntity<ReportDto>) context.reportController.getOxygenTanks();
        List<HistoryItemDto> dtos = response.getBody().history;

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatusCodeValue());
        assertEquals(4, response.getBody().history.size());

        assertEquals(dtos.get(0).date, context.firstHistoryItemDto.date);
        assertEquals(dtos.get(0).qtyOxygenTankBought, context.firstHistoryItemDto.qtyOxygenTankBought);
        assertEquals(dtos.get(0).qtyWaterUsed, context.firstHistoryItemDto.qtyWaterUsed);
        assertEquals(dtos.get(0).qtyCandlesUsed, context.firstHistoryItemDto.qtyCandlesUsed);
        assertEquals(dtos.get(0).qtyOxygenTankMade, context.firstHistoryItemDto.qtyOxygenTankMade);

        assertEquals(dtos.get(1).date, context.secondHistoryItemDto.date);
        assertEquals(dtos.get(1).qtyOxygenTankBought, context.secondHistoryItemDto.qtyOxygenTankBought);
        assertEquals(dtos.get(1).qtyWaterUsed, context.secondHistoryItemDto.qtyWaterUsed);
        assertEquals(dtos.get(1).qtyCandlesUsed, context.secondHistoryItemDto.qtyCandlesUsed);
        assertEquals(dtos.get(1).qtyOxygenTankMade, context.secondHistoryItemDto.qtyOxygenTankMade);

        assertEquals(dtos.get(2).date, context.thirdHistoryItemDto.date);
        assertEquals(dtos.get(2).qtyOxygenTankBought, context.thirdHistoryItemDto.qtyOxygenTankBought);
        assertEquals(dtos.get(2).qtyWaterUsed, context.thirdHistoryItemDto.qtyWaterUsed);
        assertEquals(dtos.get(2).qtyCandlesUsed, context.thirdHistoryItemDto.qtyCandlesUsed);
        assertEquals(dtos.get(2).qtyOxygenTankMade, context.thirdHistoryItemDto.qtyOxygenTankMade);

        assertEquals(dtos.get(3).date, context.fourthHistoryItemDto.date);
        assertEquals(dtos.get(3).qtyOxygenTankBought, context.fourthHistoryItemDto.qtyOxygenTankBought);
        assertEquals(dtos.get(3).qtyWaterUsed, context.fourthHistoryItemDto.qtyWaterUsed);
        assertEquals(dtos.get(3).qtyCandlesUsed, context.fourthHistoryItemDto.qtyCandlesUsed);
        assertEquals(dtos.get(3).qtyOxygenTankMade, context.fourthHistoryItemDto.qtyOxygenTankMade);
    }
}
