package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.domain.passes.*;
import ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy.NebulaPriceCalculationStrategy;
import ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy.NoDiscountPriceCalculationStrategy;
import ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy.PriceCalculationStrategy;
import ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy.SupergiantPriceCalculationStrategy;
import ca.ulaval.glo4002.booking.dto.PassBundleDto;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.enums.PassOptions;
import ca.ulaval.glo4002.booking.exceptions.InvalidFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PassBundleFactoryTest {

    private PassBundleFactory subject;
    private PassFactory passFactory;

    @BeforeEach
    void setUpSubject() {
        passFactory = mock(PassFactory.class);
        Pass pass = new Pass(
                new Number(1L),
                new EventDate(EventDate.START_DATE),
                new Money(new BigDecimal(100.0))
        );
        List<Pass> passes = Collections.singletonList(pass);
        when(passFactory.buildAll(any(), any())).thenReturn(passes);

        subject = new PassBundleFactory(passFactory);
    }

    @Test
    void buildWithDto_shouldBuildAPassList() {
        PassBundleDto passBundleDto = new PassBundleDto(PassCategories.SUPERNOVA.toString(), PassOptions.SINGLE_PASS.toString(), new ArrayList<>());

        subject.build(passBundleDto);

        verify(passFactory).buildAll(any(), any());
    }

    @Test
    void build_shouldBuildCategory_whenCategoryIsSupernova() {
        String aPassCategory = PassCategories.SUPERNOVA.toString();
        String aPassOption = PassOptions.PACKAGE.toString();
        PassBundleDto passBundleDto = new PassBundleDto(aPassCategory, aPassOption, null);

        PassBundle passBundle = subject.build(passBundleDto);

        assertEquals(passBundle.getCategory().getName(), PassCategories.SUPERNOVA.toString());
    }

    @Test
    void build_shouldBuildCategory_whenCategoryIsSupergiant() {
        String aPassCategory = PassCategories.SUPERGIANT.toString();
        String aPassOption = PassOptions.PACKAGE.toString();
        PassBundleDto passBundleDto = new PassBundleDto(aPassCategory, aPassOption, null);

        PassBundle passBundle = subject.build(passBundleDto);

        assertEquals(passBundle.getCategory().getName(), PassCategories.SUPERGIANT.toString());
    }

    @Test
    void build_shouldBuildCategory_whenCategoryIsNebula() {
        String aPassCategory = PassCategories.NEBULA.toString();
        String aPassOption = PassOptions.PACKAGE.toString();
        PassBundleDto passBundleDto = new PassBundleDto(aPassCategory, aPassOption, null);

        PassBundle passBundle = subject.build(passBundleDto);

        assertEquals(passBundle.getCategory().getName(), PassCategories.NEBULA.toString());
    }

    @Test
    void build_shouldBuildCategoryPricePerOption_whenCategoryIsSupernova() {
        String aPassCategory = PassCategories.SUPERNOVA.toString();
        String aPassOption = PassOptions.PACKAGE.toString();
        PassBundleDto passBundleDto = new PassBundleDto(aPassCategory, aPassOption, null);

        PassBundle passBundle = subject.build(passBundleDto);

        assertEquals(PassBundleFactory.SUPERNOVA_PACKAGE_PRICE, passBundle.getCategory().getPricePerOption(PassOptions.PACKAGE));
        assertEquals(PassBundleFactory.SUPERNOVA_SINGLE_PASS_PRICE, passBundle.getCategory().getPricePerOption(PassOptions.SINGLE_PASS));
    }

    @Test
    void build_shouldBuildCategoryPricePerOption_whenCategoryIsSupergiant() {
        String aPassCategory = PassCategories.SUPERGIANT.toString();
        String aPassOption = PassOptions.PACKAGE.toString();
        PassBundleDto passBundleDto = new PassBundleDto(aPassCategory, aPassOption, null);

        PassBundle passBundle = subject.build(passBundleDto);

        assertEquals(PassBundleFactory.SUPERGIANT_PACKAGE_PRICE, passBundle.getCategory().getPricePerOption(PassOptions.PACKAGE));
        assertEquals(PassBundleFactory.SUPERGIANT_SINGLE_PASS_PRICE, passBundle.getCategory().getPricePerOption(PassOptions.SINGLE_PASS));
    }

    @Test
    void build_shouldBuildCategoryPricePerOption_whenCategoryIsNebula() {
        String aPassCategory = PassCategories.NEBULA.toString();
        String aPassOption = PassOptions.PACKAGE.toString();
        PassBundleDto passBundleDto = new PassBundleDto(aPassCategory, aPassOption, null);

        PassBundle passBundle = subject.build(passBundleDto);

        assertEquals(PassBundleFactory.NEBULA_PACKAGE_PRICE, passBundle.getCategory().getPricePerOption(PassOptions.PACKAGE));
        assertEquals(PassBundleFactory.NEBULA_SINGLE_PASS_PRICE, passBundle.getCategory().getPricePerOption(PassOptions.SINGLE_PASS));
    }

    @Test
    void build_shouldBuildOption_whenOptionIsSinglePass() {
        String aPassCategory = PassCategories.SUPERNOVA.toString();
        String aPassOption = PassOptions.SINGLE_PASS.toString();
        List<String> someEventDates = Arrays.asList(EventDate.START_DATE.toString(), EventDate.START_DATE.plusDays(1).toString());
        PassBundleDto passBundleDto = new PassBundleDto(aPassCategory, aPassOption, someEventDates);

        PassBundle passBundle = subject.build(passBundleDto);

        assertEquals(passBundle.getOption().getName(), PassOptions.SINGLE_PASS.toString());
    }

    @Test
    void build_shouldBuildOption_whenOptionIsPackage() {
        String aPassCategory = PassCategories.SUPERNOVA.toString();
        String aPassOption = PassOptions.PACKAGE.toString();
        PassBundleDto passBundleDto = new PassBundleDto(aPassCategory, aPassOption, null);

        PassBundle passBundle = subject.build(passBundleDto);

        assertEquals(passBundle.getOption().getName(), PassOptions.PACKAGE.toString());
    }

    @Test
    void build_shouldBuildNoDiscountPriceCalculationStrategy_whenPassOptionIsSinglePassAndPassCategoryIsSupernova() {
        String aPassCategory = PassCategories.SUPERNOVA.toString();
        String aPassOption = PassOptions.SINGLE_PASS.toString();
        List<String> someEventDates = Arrays.asList(EventDate.START_DATE.toString(), EventDate.START_DATE.plusDays(1).toString());
        PassBundleDto passBundleDto = new PassBundleDto(aPassCategory, aPassOption, someEventDates);
        PriceCalculationStrategy priceCalculationStrategy = new NoDiscountPriceCalculationStrategy();

        PassBundle passBundle = subject.build(passBundleDto);
        Money passPrice = passBundle.getPasses().get(0).getPrice();
        Money expectedPrice = priceCalculationStrategy.calculatePassPrice(someEventDates.size(), passPrice);

        assertEquals(expectedPrice, passBundle.getPrice());
    }

    @Test
    void build_shouldBuildSupergiantPriceCalculationStrategy_whenPassOptionIsSinglePassAndPassCategoryIsSupergiant() {
        String aPassCategory = PassCategories.SUPERGIANT.toString();
        String aPassOption = PassOptions.SINGLE_PASS.toString();
        List<String> someEventDates = Arrays.asList(EventDate.START_DATE.toString(), EventDate.START_DATE.plusDays(1).toString());
        PassBundleDto passBundleDto = new PassBundleDto(aPassCategory, aPassOption, someEventDates);
        PriceCalculationStrategy priceCalculationStrategy = new SupergiantPriceCalculationStrategy();

        PassBundle passBundle = subject.build(passBundleDto);
        Money passPrice = passBundle.getPasses().get(0).getPrice();
        Money expectedPrice = priceCalculationStrategy.calculatePassPrice(someEventDates.size(), passPrice);

        assertEquals(expectedPrice, passBundle.getPrice());
    }

    @Test
    void build_shouldBuildNebulaPriceCalculationStrategy_whenPassOptionIsSinglePassAndPassCategoryIsNebula() {
        String aPassCategory = PassCategories.NEBULA.toString();
        String aPassOption = PassOptions.SINGLE_PASS.toString();
        List<String> someEventDates = Arrays.asList(EventDate.START_DATE.toString(), EventDate.START_DATE.plusDays(1).toString());
        PassBundleDto passBundleDto = new PassBundleDto(aPassCategory, aPassOption, someEventDates);
        PriceCalculationStrategy priceCalculationStrategy = new NebulaPriceCalculationStrategy();

        PassBundle passBundle = subject.build(passBundleDto);
        Money passPrice = passBundle.getPasses().get(0).getPrice();
        Money expectedPrice = priceCalculationStrategy.calculatePassPrice(someEventDates.size(), passPrice);

        assertEquals(expectedPrice, passBundle.getPrice());
    }

    @ParameterizedTest
    @EnumSource(PassCategories.class)
    void build_shouldBuildNoDiscountPriceCalculationStrategy_whenPassOptionIsPackage(PassCategories category) {
        String aPassCategory = category.toString();
        String aPassOption = PassOptions.PACKAGE.toString();
        PassBundleDto passBundleDto = new PassBundleDto(aPassCategory, aPassOption, null);
        PriceCalculationStrategy priceCalculationStrategy = new NoDiscountPriceCalculationStrategy();

        PassBundle passBundle = subject.build(passBundleDto);
        Money passPrice = passBundle.getPasses().get(0).getPrice();
        Money expectedPrice = priceCalculationStrategy.calculatePassPrice(1, passPrice);

        assertEquals(expectedPrice, passBundle.getPrice());
    }

    @Test
    void build_shouldThrowInvalidFormatException_whenEventDateIsNotNullAndPassOptionIsPackage() {
        String aPassCategory = PassCategories.SUPERNOVA.toString();
        String aPassOption = PassOptions.PACKAGE.toString();
        List<String> someEventDates = Collections.singletonList(EventDate.START_DATE.toString());
        PassBundleDto passBundleDto = new PassBundleDto(aPassCategory, aPassOption, someEventDates);

        assertThrows(InvalidFormatException.class, () -> subject.build(passBundleDto));
    }

    @Test
    void build_shouldThrowSinglePassWithoutEventDateException_whenEventDateIsNullAndPassOptionIsSinglePass() {
        String aPassCategory = PassCategories.SUPERNOVA.toString();
        String aPassOption = PassOptions.SINGLE_PASS.toString();
        PassBundleDto passBundleDto = new PassBundleDto(aPassCategory, aPassOption, null);

        assertThrows(InvalidFormatException.class, () -> subject.build(passBundleDto));
    }

    @Test
    void build_shouldThrowInvalidFormatException_whenPassCategoryDoesNotExist() {
        String anInvalidPassCategory = "anInvalidPassCategory";
        String aPassOption = PassOptions.PACKAGE.toString();
        PassBundleDto passBundleDto = new PassBundleDto(anInvalidPassCategory, aPassOption, null);

        assertThrows(InvalidFormatException.class, () -> subject.build(passBundleDto));
    }

    @Test
    void build_shouldThrowInvalidFormatException_whenPassOptionDoesNotExist() {
        String aPassCategory = PassCategories.SUPERNOVA.toString();
        String anInvalidPassOption = "anInvalidPassOption";
        PassBundleDto passBundleDto = new PassBundleDto(aPassCategory, anInvalidPassOption, null);

        assertThrows(InvalidFormatException.class, () -> subject.build(passBundleDto));
    }

    @Test
    void build_shouldThrowInvalidFormatException_whenEventDateIsInvalid() {
        String aPassCategory = PassCategories.SUPERNOVA.toString();
        String aPassOption = PassOptions.PACKAGE.toString();
        List<String> anInvalidEventDate = Collections.singletonList("anInvalidEventDate");
        PassBundleDto passBundleDto = new PassBundleDto(aPassCategory, aPassOption, anInvalidEventDate);

        assertThrows(InvalidFormatException.class, () -> subject.build(passBundleDto));
    }
}