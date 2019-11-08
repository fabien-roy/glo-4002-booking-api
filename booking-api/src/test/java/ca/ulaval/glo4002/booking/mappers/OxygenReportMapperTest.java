package ca.ulaval.glo4002.booking.mappers;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.domain.oxygen.History;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTankInventory;
import ca.ulaval.glo4002.booking.domain.oxygen.Report;
import ca.ulaval.glo4002.booking.dto.oxygen.OxygenReportDto;

class OxygenReportMapperTest {

	OxygenReportMapper mapper;
	Report mockedReport;

	@BeforeEach
	void setUpMapper() {
		mapper = new OxygenReportMapper();
	}

	@BeforeEach
	void setUpReport() {
		mockedReport = mock(Report.class);
		History mockedHistory = mock(History.class);
		OxygenTankInventory mockedOxygenTankInventory = mock(OxygenTankInventory.class);
		when(mockedReport.getHistory()).thenReturn(mockedHistory);
		when(mockedReport.getOxygenTankInventory()).thenReturn(mockedOxygenTankInventory);
	}

	@Test
	void dto_shouldBuildWithCorrectInventoryDtoList() {
		OxygenReportDto reportDto = mapper.toDto(mockedReport);
	}

	@Test
	void dto_shouldBuildWithCorrectHistoryDtoList() {
		OxygenReportDto reportDto = mapper.toDto(mockedReport);
	}

}
