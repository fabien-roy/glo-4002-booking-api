package ca.ulaval.glo4002.booking.passes.rest.mappers;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.interfaces.rest.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.booking.passes.domain.PassCategories;
import ca.ulaval.glo4002.booking.passes.domain.PassOptions;
import ca.ulaval.glo4002.booking.passes.domain.PassRefactored;
import ca.ulaval.glo4002.booking.passes.rest.PassRefactoredRequest;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.program.events.rest.mappers.EventDateMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PassRefactoredMapperTest {

    private PassRefactoredMapper mapper;
    private FestivalConfiguration festivalConfiguration;
    private EventDateMapper eventDateMapper;

    @BeforeEach
    void setUpMapper() {
        EventDate startEventDate = FestivalConfiguration.getDefaultStartEventDate();
        EventDate endEventDate = FestivalConfiguration.getDefaultEndEventDate();

        eventDateMapper = mock(EventDateMapper.class);
        festivalConfiguration = mock(FestivalConfiguration.class);

        when(festivalConfiguration.getStartEventDate()).thenReturn(startEventDate);
        when(festivalConfiguration.getEndEventDate()).thenReturn(endEventDate);
        when(festivalConfiguration.getAllEventDates()).thenReturn(Arrays.asList(startEventDate, startEventDate.plusDays(1)));

        mapper = new PassRefactoredMapper(festivalConfiguration, eventDateMapper);
    }

    @Test
    void fromRequest_shouldThrowInvalidFormatException_whenCategoryIsInvalid() {
        String invalidCategory = "invalidCategory";
        String option = PassOptions.PACKAGE.toString();
        PassRefactoredRequest request = new PassRefactoredRequest(invalidCategory, option);

        assertThrows(InvalidFormatException.class, () -> mapper.fromRequest(request));
    }

    @Test
    void fromRequest_shouldSetCategory() {
        PassCategories expectedCategory = PassCategories.SUPERNOVA;
        String category = expectedCategory.toString();
        String option = PassOptions.PACKAGE.toString();
        PassRefactoredRequest request = new PassRefactoredRequest(category, option);

        PassRefactored pass = mapper.fromRequest(request);

        assertEquals(expectedCategory, pass.getCategory());
    }

    @Test
    void fromRequest_shouldThrowInvalidFormatException_whenOptionIsInvalid() {
        String category = PassCategories.SUPERNOVA.toString();
        String invalidOption = "invalidOption";
        PassRefactoredRequest request = new PassRefactoredRequest(category, invalidOption);

        assertThrows(InvalidFormatException.class, () -> mapper.fromRequest(request));
    }

    @Test
    void fromRequest_shouldSetOption() {
        PassOptions expectedOption = PassOptions.PACKAGE;
        String category = PassCategories.SUPERNOVA.toString();
        String option = expectedOption.toString();
        PassRefactoredRequest request = new PassRefactoredRequest(category, option);

        PassRefactored pass = mapper.fromRequest(request);

        assertEquals(expectedOption, pass.getOption());
    }

    @Test
    void fromRequest_shouldThrowInvalidFormatException_whenOptionIsPackageAndThereAreEventDates() {
        String category = PassCategories.SUPERNOVA.toString();
        String option = PassOptions.PACKAGE.toString();
        List<String> eventDates = Collections.singletonList(FestivalConfiguration.getDefaultStartEventDate().toString());
        PassRefactoredRequest request = new PassRefactoredRequest(category, option, eventDates);

        assertThrows(InvalidFormatException.class, () -> mapper.fromRequest(request));
    }

    @Test
    void fromRequest_shouldThrowInvalidFormatException_whenOptionIsSingleAndThereAreNoEventDates() {
        String category = PassCategories.SUPERNOVA.toString();
        String option = PassOptions.SINGLE_PASS.toString();
        PassRefactoredRequest request = new PassRefactoredRequest(category, option);

        assertThrows(InvalidFormatException.class, () -> mapper.fromRequest(request));
    }

    @Test
    void fromRequest_shouldSetAllFestivalDates_whenOptionIsPackage() {
        String category = PassCategories.SUPERNOVA.toString();
        String option = PassOptions.PACKAGE.toString();
        PassRefactoredRequest request = new PassRefactoredRequest(category, option);

        PassRefactored pass = mapper.fromRequest(request);

        assertEquals(festivalConfiguration.getAllEventDates().get(0), pass.getEventDates().get(0));
        assertEquals(festivalConfiguration.getAllEventDates().get(1), pass.getEventDates().get(1));
    }

    @Test
    void fromRequest_shouldSetASingleEventDate_whenOptionIsSinglePassAndThereIsASingleEventDate() {
        String category = PassCategories.SUPERNOVA.toString();
        String option = PassOptions.SINGLE_PASS.toString();
        EventDate expectedEventDate = festivalConfiguration.getStartEventDate();
        List<String> eventDates = Collections.singletonList(expectedEventDate.toString());
        PassRefactoredRequest request = new PassRefactoredRequest(category, option, eventDates);
        when(eventDateMapper.fromString(eventDates)).thenReturn(Collections.singletonList(expectedEventDate));

        PassRefactored pass = mapper.fromRequest(request);

        assertEquals(expectedEventDate, pass.getEventDates().get(0));
    }

    @Test
    void fromRequest_shouldSetMultipleEventDates_whenOptionIsSinglePassAndThereAreMultipleEventDates() {
        String category = PassCategories.SUPERNOVA.toString();
        String option = PassOptions.SINGLE_PASS.toString();
        List<EventDate> expectedEventDates = festivalConfiguration.getAllEventDates();
        List<String> eventDates = expectedEventDates.stream().map(EventDate::toString).collect(Collectors.toList());
        PassRefactoredRequest request = new PassRefactoredRequest(category, option, eventDates);
        when(eventDateMapper.fromString(eventDates)).thenReturn(expectedEventDates);

        PassRefactored pass = mapper.fromRequest(request);

        assertEquals(expectedEventDates.get(0), pass.getEventDates().get(0));
        assertEquals(expectedEventDates.get(1), pass.getEventDates().get(1));
    }

    // TODO : Arrival and departure dates

    // TODO : Money calculation
}