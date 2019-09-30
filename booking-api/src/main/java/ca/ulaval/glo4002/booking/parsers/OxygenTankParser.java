package ca.ulaval.glo4002.booking.parsers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ca.ulaval.glo4002.booking.builders.oxygen.OxygenCategoryBuilder;
import ca.ulaval.glo4002.booking.constants.FestivalConstants;
import ca.ulaval.glo4002.booking.domainObjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domainObjects.oxygen.categories.OxygenCategory;
import ca.ulaval.glo4002.booking.dto.OxygenTankDto;
import ca.ulaval.glo4002.booking.entities.OxygenTankEntity;

public class OxygenTankParser implements Parser<OxygenTank, OxygenTankDto, OxygenTankEntity> {

	private OxygenCategoryBuilder oxygenCategoryBuilder = new OxygenCategoryBuilder();
	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(FestivalConstants.Dates.DATE_FORMAT);

	@Override
	public OxygenTank parseDto(OxygenTankDto dto) {
		OxygenCategory category = oxygenCategoryBuilder.buildById(dto.oxygenCategory);
		LocalDate requestDate = LocalDate.parse(dto.requestDate, dateTimeFormatter);

		return new OxygenTank(category, requestDate);
	}

	@Override
	public OxygenTank parseEntity(OxygenTankEntity entity) {
		OxygenCategory category = oxygenCategoryBuilder.buildById(entity.catId);

		return new OxygenTank(category, entity.requestDate);
	}

	@Override
	public OxygenTankDto toDto(OxygenTank tank) {
		OxygenTankDto oxygenTankDto = new OxygenTankDto();
		oxygenTankDto.id = tank.getId();
		oxygenTankDto.oxygenCategory = tank.getOxygenTankCategory().getId();
		oxygenTankDto.requestDate = tank.getTimeRequested().toString();

		return oxygenTankDto;
	}

	@Override
	public OxygenTankEntity toEntity(OxygenTank tank) {
		return new OxygenTankEntity(tank.getId(), tank.getOxygenTankCategory().getId(),
				tank.getTimeRequested());
	}
}
