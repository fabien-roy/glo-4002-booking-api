package ca.ulaval.glo4002.booking.passes.rest.mappers;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.interfaces.rest.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.booking.passes.domain.*;
import ca.ulaval.glo4002.booking.passes.rest.PassRefactoredRequest;
import ca.ulaval.glo4002.booking.passes.rest.PassResponse;
import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.program.events.rest.mappers.EventDateMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PassListMapperTest {

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

        PassList passList = mapper.fromRequest(request);

        assertEquals(expectedCategory, passList.getCategory());
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

        PassList passList = mapper.fromRequest(request);

        assertEquals(expectedOption, passList.getOption());
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

        PassList passList = mapper.fromRequest(request);

        assertEquals(festivalConfiguration.getAllEventDates().get(0), passList.getPasses().get(0).getEventDates().get(0));
        assertEquals(festivalConfiguration.getAllEventDates().get(1), passList.getPasses().get(0).getEventDates().get(1));
    }

    @Test
    void fromRequest_shouldSetASingleEventDate_whenOptionIsSinglePassAndThereIsASingleEventDate() {
        String category = PassCategories.SUPERNOVA.toString();
        String option = PassOptions.SINGLE_PASS.toString();
        EventDate expectedEventDate = festivalConfiguration.getStartEventDate();
        List<String> eventDates = Collections.singletonList(expectedEventDate.toString());
        PassRefactoredRequest request = new PassRefactoredRequest(category, option, eventDates);
        when(eventDateMapper.fromString(eventDates)).thenReturn(Collections.singletonList(expectedEventDate));

        PassList passList = mapper.fromRequest(request);

        assertEquals(expectedEventDate, passList.getPasses().get(0).getEventDates().get(0));
    }

    @Test
    void fromRequest_shouldSetMultipleEventDates_whenOptionIsSinglePassAndThereAreMultipleEventDates() {
        String category = PassCategories.SUPERNOVA.toString();
        String option = PassOptions.SINGLE_PASS.toString();
        List<EventDate> expectedEventDates = festivalConfiguration.getAllEventDates();
        List<String> eventDates = expectedEventDates.stream().map(EventDate::toString).collect(Collectors.toList());
        PassRefactoredRequest request = new PassRefactoredRequest(category, option, eventDates);
        when(eventDateMapper.fromString(eventDates)).thenReturn(expectedEventDates);

        PassList passList = mapper.fromRequest(request);

        assertEquals(expectedEventDates.get(0), passList.getPasses().get(0).getEventDates().get(0));
        assertEquals(expectedEventDates.get(1), passList.getPasses().get(1).getEventDates().get(0));
    }

    @Test
    void fromRequest_shouldSetArrivalDatesForEachEventDate_whenOptionIsSinglePass() {
        String category = PassCategories.SUPERNOVA.toString();
        String option = PassOptions.SINGLE_PASS.toString();
        List<EventDate> expectedArrivalDates = festivalConfiguration.getAllEventDates();
        List<String> eventDates = expectedArrivalDates.stream().map(EventDate::toString).collect(Collectors.toList());
        PassRefactoredRequest request = new PassRefactoredRequest(category, option, eventDates);
        when(eventDateMapper.fromString(eventDates)).thenReturn(expectedArrivalDates);

        PassList passList = mapper.fromRequest(request);

        assertEquals(expectedArrivalDates.get(0), passList.getArrivalDates().get(0));
        assertEquals(expectedArrivalDates.get(1), passList.getArrivalDates().get(1));
    }

    @Test
    void fromRequest_shouldSetASingleArrivalDate_whenOptionIsPackage() {
        String category = PassCategories.SUPERNOVA.toString();
        String option = PassOptions.PACKAGE.toString();
        PassRefactoredRequest request = new PassRefactoredRequest(category, option);

        PassList passList = mapper.fromRequest(request);

        assertEquals(1, passList.getArrivalDates().size());
    }

    @Test
    void fromRequest_shouldSetArrivalDateOfFestivalStart_whenOptionIsPackage() {
        String category = PassCategories.SUPERNOVA.toString();
        String option = PassOptions.PACKAGE.toString();
        EventDate expectedArrivalDate = festivalConfiguration.getStartEventDate();
        PassRefactoredRequest request = new PassRefactoredRequest(category, option);

        PassList passList = mapper.fromRequest(request);

        assertEquals(expectedArrivalDate, passList.getArrivalDates().get(0));
    }

    @Test
    void fromRequest_shouldSetDepartureDatesForEachEventDate_whenOptionIsSinglePass() {
        String category = PassCategories.SUPERNOVA.toString();
        String option = PassOptions.SINGLE_PASS.toString();
        List<EventDate> expectedDepartureDates = festivalConfiguration.getAllEventDates();
        List<String> eventDates = expectedDepartureDates.stream().map(EventDate::toString).collect(Collectors.toList());
        PassRefactoredRequest request = new PassRefactoredRequest(category, option, eventDates);
        when(eventDateMapper.fromString(eventDates)).thenReturn(expectedDepartureDates);

        PassList passList = mapper.fromRequest(request);

        assertEquals(expectedDepartureDates.get(0), passList.getDepartureDates().get(0));
        assertEquals(expectedDepartureDates.get(1), passList.getDepartureDates().get(1));
    }

    @Test
    void fromRequest_shouldSetASingleDepartureDate_whenOptionIsPackage() {
        String category = PassCategories.SUPERNOVA.toString();
        String option = PassOptions.PACKAGE.toString();
        PassRefactoredRequest request = new PassRefactoredRequest(category, option);

        PassList passList = mapper.fromRequest(request);

        assertEquals(1, passList.getDepartureDates().size());
    }

    @Test
    void fromRequest_shouldSetDepartureDateOfFestivalStart_whenOptionIsPackage() {
        String category = PassCategories.SUPERNOVA.toString();
        String option = PassOptions.PACKAGE.toString();
        EventDate expectedDepartureDate = festivalConfiguration.getEndEventDate();
        PassRefactoredRequest request = new PassRefactoredRequest(category, option);

        PassList passList = mapper.fromRequest(request);

        assertEquals(expectedDepartureDate, passList.getDepartureDates().get(0));
    }

    @Test
    void fromRequest_shouldSetCorrectPrice_whenCategoryIsSupernovaAndOptionIsPackage() {
        Money expectedPrice = new Money(BigDecimal.valueOf(700000.0));
        String category = PassCategories.SUPERNOVA.toString();
        String option = PassOptions.PACKAGE.toString();
        PassRefactoredRequest request = new PassRefactoredRequest(category, option);

        PassList passList = mapper.fromRequest(request);

        assertEquals(expectedPrice, passList.getPrice());
    }

    @Test
    void fromRequest_shouldSetCorrectPriceForASinglePass_whenCategoryIsSupernovaAndOptionIsSinglePass() {
        Money expectedPrice = new Money(BigDecimal.valueOf(150000.0));
        String category = PassCategories.SUPERNOVA.toString();
        String option = PassOptions.SINGLE_PASS.toString();
        List<String> eventDates = Collections.singletonList(festivalConfiguration.getStartEventDate().toString());
        PassRefactoredRequest request = new PassRefactoredRequest(category, option, eventDates);

        PassList passList = mapper.fromRequest(request);

        assertEquals(expectedPrice, passList.getPrice());
    }

    @Test
    void fromRequest_shouldSetCorrectPriceForMultiplePasses_whenCategoryIsSupernovaAndOptionIsSinglePass() {
        Money expectedPrice = new Money(BigDecimal.valueOf(300000.0));
        String category = PassCategories.SUPERNOVA.toString();
        String option = PassOptions.SINGLE_PASS.toString();
        List<String> eventDates = festivalConfiguration.getAllEventDates().stream().map(EventDate::toString).collect(Collectors.toList());
        PassRefactoredRequest request = new PassRefactoredRequest(category, option, eventDates);

        PassList passList = mapper.fromRequest(request);

        assertEquals(expectedPrice, passList.getPrice());
    }

    @Test
    void fromRequest_shouldSetCorrectPrice_whenCategoryIsSupergiantAndOptionIsPackage() {
        Money expectedPrice = new Money(BigDecimal.valueOf(500000.0));
        String category = PassCategories.SUPERGIANT.toString();
        String option = PassOptions.PACKAGE.toString();
        PassRefactoredRequest request = new PassRefactoredRequest(category, option);

        PassList passList = mapper.fromRequest(request);

        assertEquals(expectedPrice, passList.getPrice());
    }

    @Test
    void fromRequest_shouldSetCorrectPriceForASinglePass_whenCategoryIsSupergiantAndOptionIsSinglePass() {
        Money expectedPrice = new Money(BigDecimal.valueOf(100000.0));
        String category = PassCategories.SUPERGIANT.toString();
        String option = PassOptions.SINGLE_PASS.toString();
        List<String> eventDates = Collections.singletonList(festivalConfiguration.getStartEventDate().toString());
        PassRefactoredRequest request = new PassRefactoredRequest(category, option, eventDates);

        PassList passList = mapper.fromRequest(request);

        assertEquals(expectedPrice, passList.getPrice());
    }

    @Test
    void fromRequest_shouldSetCorrectPriceForMultiplePasses_whenCategoryIsSupergiantAndOptionIsSinglePass() {
        Money expectedPrice = new Money(BigDecimal.valueOf(200000.0));
        String category = PassCategories.SUPERGIANT.toString();
        String option = PassOptions.SINGLE_PASS.toString();
        List<String> eventDates = Collections.nCopies(2, festivalConfiguration.getStartEventDate().toString());
        PassRefactoredRequest request = new PassRefactoredRequest(category, option, eventDates);

        PassList passList = mapper.fromRequest(request);

        assertEquals(expectedPrice, passList.getPrice());
    }

    @Test
    void fromRequest_shouldSetCorrectPriceForMultiplePassesOverThreshold_whenCategoryIsSupergiantAndOptionIsSinglePass() {
        int threshold = 5;
        Money expectedPrice = new Money(BigDecimal.valueOf(450000.0));
        String category = PassCategories.SUPERGIANT.toString();
        String option = PassOptions.SINGLE_PASS.toString();
        List<String> eventDates = Collections.nCopies(threshold, festivalConfiguration.getStartEventDate().toString());
        PassRefactoredRequest request = new PassRefactoredRequest(category, option, eventDates);

        PassList passList = mapper.fromRequest(request);

        assertEquals(expectedPrice, passList.getPrice());
    }

    @Test
    void fromRequest_shouldSetCorrectPrice_whenCategoryIsNebulaAndOptionIsPackage() {
        Money expectedPrice = new Money(BigDecimal.valueOf(250000.0));
        String category = PassCategories.NEBULA.toString();
        String option = PassOptions.PACKAGE.toString();
        PassRefactoredRequest request = new PassRefactoredRequest(category, option);

        PassList passList = mapper.fromRequest(request);

        assertEquals(expectedPrice, passList.getPrice());
    }

    @Test
    void fromRequest_shouldSetCorrectPriceForASinglePass_whenCategoryIsNebulaAndOptionIsSinglePass() {
        Money expectedPrice = new Money(BigDecimal.valueOf(50000.0));
        String category = PassCategories.NEBULA.toString();
        String option = PassOptions.SINGLE_PASS.toString();
        List<String> eventDates = Collections.singletonList(festivalConfiguration.getStartEventDate().toString());
        PassRefactoredRequest request = new PassRefactoredRequest(category, option, eventDates);

        PassList passList = mapper.fromRequest(request);

        assertEquals(expectedPrice, passList.getPrice());
    }

    @Test
    void fromRequest_shouldSetCorrectPriceForMultiplePasses_whenCategoryIsNebulaAndOptionIsSinglePass() {
        Money expectedPrice = new Money(BigDecimal.valueOf(100000.0));
        String category = PassCategories.NEBULA.toString();
        String option = PassOptions.SINGLE_PASS.toString();
        List<String> eventDates = Collections.nCopies(2, festivalConfiguration.getStartEventDate().toString());
        PassRefactoredRequest request = new PassRefactoredRequest(category, option, eventDates);

        PassList passList = mapper.fromRequest(request);

        assertEquals(expectedPrice, passList.getPrice());
    }

    @Test
    void fromRequest_shouldSetCorrectPriceForMultiplePassesOverThreshold_whenCategoryIsNebulaAndOptionIsSinglePass() {
        int threshold = 4;
        Money expectedPrice = new Money(BigDecimal.valueOf(180000.0));
        String category = PassCategories.NEBULA.toString();
        String option = PassOptions.SINGLE_PASS.toString();
        List<String> eventDates = Collections.nCopies(threshold, festivalConfiguration.getStartEventDate().toString());
        PassRefactoredRequest request = new PassRefactoredRequest(category, option, eventDates);

        PassList passList = mapper.fromRequest(request);

        assertEquals(expectedPrice, passList.getPrice());
    }

    @Test
    void toResponse_shouldBuildASinglePass_whenOptionIsPackage() {
        PassOptions option = PassOptions.PACKAGE;
        PassRefactored pass = mock(PassRefactored.class);
        PassList passList = new PassList(Collections.singletonList(pass), PassCategories.SUPERNOVA, option, mock(Money.class), Collections.emptyList(), Collections.emptyList());

        List<PassResponse> passResponses = mapper.toResponse(passList);

        assertEquals(1, passResponses.size());
    }

    @Test
    void toResponse_shouldSetEventDate_whenThereIsASinglePass() {
        EventDate expectedEventDate = FestivalConfiguration.getDefaultStartEventDate();
        PassRefactored pass = new PassRefactored(Collections.singletonList(expectedEventDate));
        PassList passList = new PassList(Collections.singletonList(pass), PassCategories.SUPERNOVA, PassOptions.SINGLE_PASS, mock(Money.class), Collections.emptyList(), Collections.emptyList());

        List<PassResponse> passResponses = mapper.toResponse(passList);

        assertEquals(expectedEventDate.toString(), passResponses.get(0).getEventDate());
    }

    @Test
    void toResponse_shouldSetEventDates_whenThereIsAreMultiplePasses() {
        EventDate expectedEventDate = FestivalConfiguration.getDefaultStartEventDate();
        EventDate otherExpectedEventDate = expectedEventDate.plusDays(1);
        PassRefactored pass = new PassRefactored(Collections.singletonList(expectedEventDate));
        PassRefactored otherPass = new PassRefactored(Collections.singletonList(otherExpectedEventDate));
        List<PassRefactored> passes = Arrays.asList(pass, otherPass);
        PassList passList = new PassList(passes, PassCategories.SUPERNOVA, PassOptions.SINGLE_PASS, mock(Money.class), Collections.emptyList(), Collections.emptyList());

        List<PassResponse> passResponses = mapper.toResponse(passList);

        assertEquals(expectedEventDate.toString(), passResponses.get(0).getEventDate());
        assertEquals(otherExpectedEventDate.toString(), passResponses.get(1).getEventDate());
    }

    @Test
    void toResponse_shouldSetPassNumber() {
        long expectedPassNumber = 1L;
        EventDate eventDate = mock(EventDate.class);
        PassRefactored pass = new PassRefactored(Collections.singletonList(eventDate));
        pass.setNumber(expectedPassNumber);
        List<PassRefactored> passes = Collections.singletonList(pass);
        PassList passList = new PassList(passes, PassCategories.SUPERNOVA, PassOptions.PACKAGE, mock(Money.class), Collections.emptyList(), Collections.emptyList());

        List<PassResponse> passResponses = mapper.toResponse(passList);

        assertEquals(expectedPassNumber, passResponses.get(0).getPassNumber());
    }

    @Test
    void toResponse_shouldSetCategory() {
        PassCategories expectedCategory = PassCategories.SUPERNOVA;
        PassRefactored pass = mock(PassRefactored.class);
        List<PassRefactored> passes = Collections.singletonList(pass);
        PassList passList = new PassList(passes, expectedCategory, PassOptions.PACKAGE, mock(Money.class), Collections.emptyList(), Collections.emptyList());

        List<PassResponse> passResponses = mapper.toResponse(passList);

        assertEquals(expectedCategory.toString(), passResponses.get(0).getPassCategory());
    }

    @Test
    void toResponse_shouldSetCategoryForAllPasses() {
        PassCategories expectedCategory = PassCategories.SUPERNOVA;
        EventDate eventDate = mock(EventDate.class);
        PassRefactored pass = new PassRefactored(Collections.singletonList(eventDate));
        List<PassRefactored> passes = Collections.nCopies(2, pass);
        PassList passList = new PassList(passes, expectedCategory, PassOptions.SINGLE_PASS, mock(Money.class), Collections.emptyList(), Collections.emptyList());

        List<PassResponse> passResponses = mapper.toResponse(passList);

        assertEquals(expectedCategory.toString(), passResponses.get(0).getPassCategory());
        assertEquals(expectedCategory.toString(), passResponses.get(1).getPassCategory());
    }

    @Test
    void toResponse_shouldSetOption() {
        PassOptions expectedOption = PassOptions.PACKAGE;
        PassRefactored pass = mock(PassRefactored.class);
        List<PassRefactored> passes = Collections.singletonList(pass);
        PassList passList = new PassList(passes, PassCategories.SUPERNOVA, expectedOption, mock(Money.class), Collections.emptyList(), Collections.emptyList());

        List<PassResponse> passResponses = mapper.toResponse(passList);

        assertEquals(expectedOption.toString(), passResponses.get(0).getPassOption());
    }

    @Test
    void toResponse_shouldSetOptionForAllPasses() {
        PassOptions expectedOption = PassOptions.SINGLE_PASS;
        EventDate eventDate = mock(EventDate.class);
        PassRefactored pass = new PassRefactored(Collections.singletonList(eventDate));
        List<PassRefactored> passes = Collections.nCopies(2, pass);
        PassList passList = new PassList(passes, PassCategories.SUPERNOVA, expectedOption, mock(Money.class), Collections.emptyList(), Collections.emptyList());

        List<PassResponse> passResponses = mapper.toResponse(passList);

        assertEquals(expectedOption.toString(), passResponses.get(0).getPassOption());
        assertEquals(expectedOption.toString(), passResponses.get(1).getPassOption());
    }
}