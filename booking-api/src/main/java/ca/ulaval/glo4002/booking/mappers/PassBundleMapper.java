package ca.ulaval.glo4002.booking.mappers;

import ca.ulaval.glo4002.booking.domain.passes.PassBundle;
import ca.ulaval.glo4002.booking.dto.PassDto;

import java.util.ArrayList;
import java.util.List;

public class PassBundleMapper {

    List<PassDto> toDto(PassBundle passBundle) {
        String passCategory = passBundle.getCategory().getName();
        String passOption = passBundle.getOption().toString();

        List<PassDto> passDtos = new ArrayList<>();
        passBundle.getPasses().forEach(pass -> {
                String eventDate = null;

                if (pass.getEventDate() != null) eventDate = pass.getEventDate().toString();

                passDtos.add(new PassDto(
                    pass.getPassNumber().getValue(),
                    passCategory,
                    passOption,
                    eventDate
                ));
            }
        );

        return passDtos;
    }
}
