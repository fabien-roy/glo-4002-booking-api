package ca.ulaval.glo4002.booking.mappers;

import ca.ulaval.glo4002.booking.domain.passes.PassList;
import ca.ulaval.glo4002.booking.dto.PassDto;

import java.util.ArrayList;
import java.util.List;

public class PassListMapper {

    List<PassDto> toDto(PassList passList) {
        String passCategory = passList.getCategory().getName();
        String passOption = passList.getOption().getName();

        List<PassDto> passDtos = new ArrayList<>();
        passList.getPasses().forEach(pass ->
                passDtos.add(new PassDto(
                    pass.getPassNumber().getValue(),
                    passCategory,
                    passOption,
                    pass.getEventDate().toString()
                )
        ));

        return passDtos;
    }
}
