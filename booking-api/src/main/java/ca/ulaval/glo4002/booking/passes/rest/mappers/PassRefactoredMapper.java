package ca.ulaval.glo4002.booking.passes.rest.mappers;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.interfaces.rest.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.booking.passes.domain.PassCategories;
import ca.ulaval.glo4002.booking.passes.domain.PassOptions;
import ca.ulaval.glo4002.booking.passes.domain.PassRefactored;
import ca.ulaval.glo4002.booking.passes.rest.PassRefactoredRequest;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.program.events.rest.mappers.EventDateMapper;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

public class PassRefactoredMapper {

    private final FestivalConfiguration festivalConfiguration;
    private final EventDateMapper eventDateMapper;

    @Inject
    public PassRefactoredMapper(FestivalConfiguration festivalConfiguration, EventDateMapper eventDateMapper) {
        this.festivalConfiguration = festivalConfiguration;
        this.eventDateMapper = eventDateMapper;
    }

    public PassRefactored fromRequest(PassRefactoredRequest request) {
        PassCategories category = parsePassCategory(request.getPassCategory());
        PassOptions option = parsePassOption(request.getPassOption());
        List<EventDate> eventDates = buildEventDates(request.getEventDates(), option);
        List<EventDate> arrivalDates = buildArrivalDates(eventDates, option);
        List<EventDate> departureDates = buildDepartureDates(eventDates, option);

        // TODO : Handle price calculation
        return new PassRefactored(category, option, null, eventDates, arrivalDates, departureDates);
    }

    private PassCategories parsePassCategory(String category) {
        return PassCategories.get(category);
    }

    private PassOptions parsePassOption(String option) {
        return PassOptions.get(option);
    }

    private List<EventDate> buildEventDates(List<String> eventDates, PassOptions option) {
        List<EventDate> builtEventDates;

        switch (option) {
            case PACKAGE:
                if (eventDates != null) {
                    throw new InvalidFormatException();
                }

                builtEventDates = festivalConfiguration.getAllEventDates();

                break;
            default:
            case SINGLE_PASS:
                if (eventDates == null) {
                    throw new InvalidFormatException();
                }

                builtEventDates = eventDateMapper.fromString(eventDates);

                break;
        }

        return builtEventDates;
    }

    private List<EventDate> buildArrivalDates(List<EventDate> eventDates, PassOptions option) {
        switch (option) {
            case PACKAGE:
                return Collections.singletonList(festivalConfiguration.getStartEventDate());
            default:
            case SINGLE_PASS:
                return eventDates;
        }
    }

    private List<EventDate> buildDepartureDates(List<EventDate> eventDates, PassOptions option) {
        switch (option) {
            case PACKAGE:
                return Collections.singletonList(festivalConfiguration.getEndEventDate());
            default:
            case SINGLE_PASS:
                return eventDates;
        }
    }
}
