package ca.ulaval.glo4002.booking.mappers;

import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.passes.*;
import ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy.PriceCalculationStrategy;
import ca.ulaval.glo4002.booking.dto.PassDto;
import ca.ulaval.glo4002.booking.dto.PassListDto;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.enums.PassOptions;
import ca.ulaval.glo4002.booking.exceptions.passes.*;
import ca.ulaval.glo4002.booking.factories.PassFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PassListMapperTest {

    private PassListMapper subject;
    private PassFactory passFactory;
    private PassList passList;

    @BeforeEach
    void setUpSubject() {
        passFactory = mock(PassFactory.class);
        passList = new PassList(mock(PassCategory.class), mock(PassOption.class), mock(PriceCalculationStrategy.class));
        when(passFactory.build(any(), any())).thenReturn(passList);

        subject = new PassListMapper(passFactory);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void toDto_shouldBuildCorrectQuantityOfDtos(int expectedSize) {
        PassNumber aPassNumber = new PassNumber(new Number(1L));
        Pass aPass = new Pass(aPassNumber, mock(EventDate.class));
        List<Pass> passes = new ArrayList<>(Collections.nCopies(expectedSize, aPass));
        passList = new PassList(passes, mock(PassCategory.class), mock(PassOption.class));

        List<PassDto> passDtos = subject.toDto(passList);

        assertEquals(expectedSize, passDtos.size());
    }

    @Test
    void toDto_shouldBuildDtoWithCorrectPassNumbers() {
        PassNumber aPassNumber = new PassNumber(new Number(1L));
        PassNumber anotherPassNumber = new PassNumber(new Number(2L));
        Pass aPass = new Pass(aPassNumber, mock(EventDate.class));
        Pass anotherPass = new Pass(anotherPassNumber, mock(EventDate.class));
        List<Pass> passes = new ArrayList<>(Arrays.asList(aPass, anotherPass));
        passList = new PassList(passes, mock(PassCategory.class), mock(PassOption.class));

        List<PassDto> passDtos = subject.toDto(passList);

        assertTrue(passDtos.stream().anyMatch(pass -> pass.getPassNumber().equals(aPassNumber.getId().getValue())));
        assertTrue(passDtos.stream().anyMatch(pass -> pass.getPassNumber().equals(anotherPassNumber.getId().getValue())));
    }

    @Test
    void toDto_shouldBuildDtoWithCorrectCategory() {
        PassNumber aPassNumber = new PassNumber(new Number(1L));
        Pass aPass = new Pass(aPassNumber, mock(EventDate.class));
        List<Pass> passes = new ArrayList<>(Collections.singletonList(aPass));
        PassCategory passCategory = mock(PassCategory.class);
        String expectedPassCategoryName = "expectedPassCategoryName";
        when(passCategory.getName()).thenReturn(expectedPassCategoryName);
        passList = new PassList(passes, passCategory, mock(PassOption.class));

        List<PassDto> passDtos = subject.toDto(passList);

        assertEquals(expectedPassCategoryName, passDtos.get(0).getPassCategory());
    }

    @Test
    void toDto_shouldSetSameCategoryForAllPasses() {
        PassNumber aPassNumber = new PassNumber(new Number(1L));
        PassNumber anotherPassNumber = new PassNumber(new Number(2L));
        Pass aPass = new Pass(aPassNumber, mock(EventDate.class));
        Pass anotherPass = new Pass(anotherPassNumber, mock(EventDate.class));
        List<Pass> passes = new ArrayList<>(Arrays.asList(aPass, anotherPass));
        PassCategory passCategory = mock(PassCategory.class);
        String expectedPassCategoryName = "expectedPassCategoryName";
        when(passCategory.getName()).thenReturn(expectedPassCategoryName);
        passList = new PassList(passes, passCategory, mock(PassOption.class));

        List<PassDto> passDtos = subject.toDto(passList);

        assertTrue(passDtos.stream().allMatch(pass -> pass.getPassCategory().equals(expectedPassCategoryName)));
    }

    @Test
    void toDto_shouldBuildDtoWithCorrectOption() {
        PassNumber aPassNumber = new PassNumber(new Number(1L));
        Pass aPass = new Pass(aPassNumber, mock(EventDate.class));
        List<Pass> passes = new ArrayList<>(Collections.singletonList(aPass));
        PassOption passOption = mock(PassOption.class);
        String expectedPassOptionName = "expectedPassOptionName";
        when(passOption.getName()).thenReturn(expectedPassOptionName);
        passList = new PassList(passes, mock(PassCategory.class), passOption);

        List<PassDto> passDtos = subject.toDto(passList);

        assertEquals(expectedPassOptionName, passDtos.get(0).getPassOption());
    }

    @Test
    void toDto_shouldSetSameOptionForAllPasses() {
        PassNumber aPassNumber = new PassNumber(new Number(1L));
        PassNumber anotherPassNumber = new PassNumber(new Number(2L));
        Pass aPass = new Pass(aPassNumber, mock(EventDate.class));
        Pass anotherPass = new Pass(anotherPassNumber, mock(EventDate.class));
        List<Pass> passes = new ArrayList<>(Arrays.asList(aPass, anotherPass));
        PassOption passOption = mock(PassOption.class);
        String expectedPassOptionName = "expectedPassOptionName";
        when(passOption.getName()).thenReturn(expectedPassOptionName);
        passList = new PassList(passes, mock(PassCategory.class), passOption);

        List<PassDto> passDtos = subject.toDto(passList);

        assertTrue(passDtos.stream().allMatch(pass -> pass.getPassOption().equals(expectedPassOptionName)));
    }

    @Test
    void toDto_shouldBuildDtoWithCorrectEventDates() {
        PassNumber aPassNumber = new PassNumber(new Number(1L));
        PassNumber anotherPassNumber = new PassNumber(new Number(2L));
        EventDate aEventDate = new EventDate(EventDate.START_DATE);
        EventDate anotherEventDate = new EventDate(EventDate.START_DATE.plusDays(1));
        Pass aPass = new Pass(aPassNumber, aEventDate);
        Pass anotherPass = new Pass(anotherPassNumber, anotherEventDate);
        List<Pass> passes = new ArrayList<>(Arrays.asList(aPass, anotherPass));
        passList = new PassList(passes, mock(PassCategory.class), mock(PassOption.class));

        List<PassDto> passDtos = subject.toDto(passList);

        assertTrue(passDtos.stream().anyMatch(pass -> aEventDate.toString().equals(pass.getEventDate())));
        assertTrue(passDtos.stream().anyMatch(pass -> anotherEventDate.toString().equals(pass.getEventDate())));
    }

    @Test
    void parseDto_shouldBuildAPassList() {
        PassListDto passListDto = new PassListDto(PassCategories.SUPERNOVA.toString(), PassOptions.SINGLE_PASS.toString(), new ArrayList<>());

        subject.parseDto(passListDto);

        verify(passFactory).build(any(), any());
    }

    @Test
    void parseDto_shouldParseASinglePass_whenThereIsOnlyOneEventDate() {
        String aPassCategory = PassCategories.SUPERNOVA.toString();
        String aPassOption = PassOptions.SINGLE_PASS.toString();
        List<String> aEventDate = new ArrayList<>(Collections.singleton(EventDate.START_DATE.toString()));
        PassListDto passListDto = new PassListDto(aPassCategory, aPassOption, aEventDate);

        PassList passList = subject.parseDto(passListDto);

        assertEquals(1, passList.size());
    }

    @Test
    void parseDto_shouldParseMultiplePasses_whenThereAreMultipleEventDates() {
        String aPassCategory = PassCategories.SUPERNOVA.toString();
        String aPassOption = PassOptions.SINGLE_PASS.toString();
        List<String> someEventDates = new ArrayList<>(Arrays.asList(EventDate.START_DATE.toString(), EventDate.START_DATE.plusDays(1).toString()));
        PassListDto passListDto = new PassListDto(aPassCategory, aPassOption, someEventDates);

        PassList passList = subject.parseDto(passListDto);

        assertEquals(2, passList.size());
    }

    @Test
    void parseDto_shouldThrowPackagePassWithEventDateException_whenEventDateIsNotNullAndPassOptionIsPackage() {
        String aPassCategory = PassCategories.SUPERNOVA.toString();
        List<String> someEventDates = new ArrayList<>(Arrays.asList(EventDate.START_DATE.toString(), EventDate.START_DATE.plusDays(1).toString()));
        PassListDto passListDto = new PassListDto(aPassCategory, PassOptions.PACKAGE.toString(), someEventDates);
        passList.setOption(mock(PassOption.class));

        assertThrows(PackagePassWithEventDateException.class, () -> subject.parseDto(passListDto));
    }

    @Test
    void parseDto_shouldThrowSinglePassWithoutEventDateException_whenEventDateIsNullAndPassOptionIsSinglePass() {
        String aPassCategory = PassCategories.SUPERNOVA.toString();
        PassListDto passListDto = new PassListDto(aPassCategory, PassOptions.SINGLE_PASS.toString(), null);
        passList.setOption(mock(PassOption.class));

        assertThrows(SinglePassWithoutEventDateException.class, () -> subject.parseDto(passListDto));
    }

    @Test
    void parseDto_shouldThrowInvalidPassCategoryException_whenPassCategoryDoesNotExist() {
        String anInvalidPassOption = "anInvalidPassCategory";
        PassListDto passListDto = new PassListDto(anInvalidPassOption, PassOptions.PACKAGE.toString(), new ArrayList<>());

        assertThrows(InvalidPassCategoryException.class, () -> subject.parseDto(passListDto));
    }

    @Test
    void parseDto_shouldThrowInvalidPassOptionException_whenPassOptionDoesNotExist() {
        String anInvalidPassOption = "anInvalidPassOption";
        PassListDto passListDto = new PassListDto(PassCategories.SUPERNOVA.toString(), anInvalidPassOption, new ArrayList<>());

        assertThrows(InvalidPassOptionException.class, () -> subject.parseDto(passListDto));
    }
}