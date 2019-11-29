package ca.ulaval.glo4002.booking.passes.rest.mappers;

import ca.ulaval.glo4002.booking.passes.domain.PassBundle;
import ca.ulaval.glo4002.booking.passes.rest.PassResponse;

import java.util.ArrayList;
import java.util.List;

public class PassBundleMapper {

    public List<PassResponse> toResponse(PassBundle passBundle) {
        String passCategory = passBundle.getCategory().toString();
        String passOption = passBundle.getOption().toString();

        List<PassResponse> passResponses = new ArrayList<>();
        passBundle.getPasses().forEach(pass -> {
                String eventDate = null;

                if (pass.getEventDate() != null) eventDate = pass.getEventDate().toString();

                passResponses.add(new PassResponse(
                    pass.getPassNumber().getValue(),
                    passCategory,
                    passOption,
                    eventDate
                ));
            }
        );

        return passResponses;
    }
}
