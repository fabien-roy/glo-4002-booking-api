package ca.ulaval.glo4002.booking.passes.bundles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ca.ulaval.glo4002.booking.events.EventDateFactory;
import ca.ulaval.glo4002.booking.passes.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import ca.ulaval.glo4002.booking.numbers.Number;
import ca.ulaval.glo4002.booking.numbers.NumberGenerator;
import ca.ulaval.glo4002.booking.events.EventDate;
import ca.ulaval.glo4002.booking.profits.Money;
import ca.ulaval.glo4002.booking.passes.pricecalculationstrategy.NebulaPriceCalculationStrategy;
import ca.ulaval.glo4002.booking.passes.pricecalculationstrategy.NoDiscountPriceCalculationStrategy;
import ca.ulaval.glo4002.booking.passes.pricecalculationstrategy.PriceCalculationStrategy;
import ca.ulaval.glo4002.booking.passes.pricecalculationstrategy.SupergiantPriceCalculationStrategy;
import ca.ulaval.glo4002.booking.exceptions.InvalidFormatException;

class PassBundleFactoryTest {

	private PassBundleFactory passBundleFactory;

	@BeforeEach
	void setUpFactory() {
		NumberGenerator numberGenerator = new NumberGenerator();
		EventDateFactory eventDateFactory = mock(EventDateFactory.class);
		PassFactory passFactory = new PassFactory(numberGenerator, eventDateFactory);
		passBundleFactory = new PassBundleFactory(passFactory);
	}

	@Test
	void buildWithDto_shouldBuildAPassList() {
		String aPassCategory = PassCategories.SUPERNOVA.toString();
		String aPassOption = PassOptions.SINGLE_PASS.toString();
		PassBundleDto passBundleDto = new PassBundleDto(aPassCategory, aPassOption, new ArrayList<>());
		Number aNumber = new Number(1L);
		BigDecimal aBigDecimal = new BigDecimal(100.0);
		Money aPrice = new Money(aBigDecimal);
		EventDate aEventDate = EventDate.getStartEventDate();
		Pass pass = new Pass(aNumber, aPrice, aEventDate);
		PassFactory passFactory = mock(PassFactory.class);
		when(passFactory.buildAll(any(), any())).thenReturn(Collections.singletonList(pass));
		passBundleFactory = new PassBundleFactory(passFactory);

		passBundleFactory.build(passBundleDto);

		verify(passFactory).buildAll(any(), any());
	}

	@Test
	void build_shouldBuildCategory_whenCategoryIsSupernova() {
		String supernovaCategory = PassCategories.SUPERNOVA.toString();
		String aPassOption = PassOptions.PACKAGE.toString();
		PassBundleDto passBundleDto = new PassBundleDto(supernovaCategory, aPassOption, null);

		PassBundle passBundle = passBundleFactory.build(passBundleDto);

		assertEquals(PassCategories.SUPERNOVA, passBundle.getCategory());
	}

	@Test
	void build_shouldBuildCategory_whenCategoryIsSupergiant() {
		String supergiantCategory = PassCategories.SUPERGIANT.toString();
		String aPassOption = PassOptions.PACKAGE.toString();
		PassBundleDto passBundleDto = new PassBundleDto(supergiantCategory, aPassOption, null);

		PassBundle passBundle = passBundleFactory.build(passBundleDto);

		assertEquals(PassCategories.SUPERGIANT, passBundle.getCategory());
	}

	@Test
	void build_shouldBuildCategory_whenCategoryIsNebula() {
		String nebulaPassCategory = PassCategories.NEBULA.toString();
		String aPassOption = PassOptions.PACKAGE.toString();
		PassBundleDto passBundleDto = new PassBundleDto(nebulaPassCategory, aPassOption, null);

		PassBundle passBundle = passBundleFactory.build(passBundleDto);

		assertEquals(PassCategories.NEBULA, passBundle.getCategory());
	}

	@Test
	void build_shouldBuildCategoryPricePerOption_whenCategoryIsSupernova() {
		String supernovaCategory = PassCategories.SUPERNOVA.toString();
		String aPassOption = PassOptions.PACKAGE.toString();
		PassBundleDto passBundleDto = new PassBundleDto(supernovaCategory, aPassOption, null);

		PassBundle passBundle = passBundleFactory.build(passBundleDto);

		assertEquals(PassBundleFactory.SUPERNOVA_PACKAGE_PRICE, passBundle.getPricePerOption(PassOptions.PACKAGE));
		assertEquals(PassBundleFactory.SUPERNOVA_SINGLE_PASS_PRICE,
				passBundle.getPricePerOption(PassOptions.SINGLE_PASS));
	}

	@Test
	void build_shouldBuildCategoryPricePerOption_whenCategoryIsSupergiant() {
		String supergiantCategory = PassCategories.SUPERGIANT.toString();
		String aPassOption = PassOptions.PACKAGE.toString();
		PassBundleDto passBundleDto = new PassBundleDto(supergiantCategory, aPassOption, null);

		PassBundle passBundle = passBundleFactory.build(passBundleDto);

		assertEquals(PassBundleFactory.SUPERGIANT_PACKAGE_PRICE, passBundle.getPricePerOption(PassOptions.PACKAGE));
		assertEquals(PassBundleFactory.SUPERGIANT_SINGLE_PASS_PRICE,
				passBundle.getPricePerOption(PassOptions.SINGLE_PASS));
	}

	@Test
	void build_shouldBuildCategoryPricePerOption_whenCategoryIsNebula() {
		String nebulaCategory = PassCategories.NEBULA.toString();
		String aPassOption = PassOptions.PACKAGE.toString();
		PassBundleDto passBundleDto = new PassBundleDto(nebulaCategory, aPassOption, null);

		PassBundle passBundle = passBundleFactory.build(passBundleDto);

		assertEquals(PassBundleFactory.NEBULA_PACKAGE_PRICE, passBundle.getPricePerOption(PassOptions.PACKAGE));
		assertEquals(PassBundleFactory.NEBULA_SINGLE_PASS_PRICE, passBundle.getPricePerOption(PassOptions.SINGLE_PASS));
	}

	@Test
	void build_shouldBuildOption_whenOptionIsSinglePass() {
		String aPassCategory = PassCategories.SUPERNOVA.toString();
		String singlePassOption = PassOptions.SINGLE_PASS.toString();
		List<String> someEventDates = Arrays.asList(
				EventDate.getStartEventDate().toString(),
				EventDate.getStartEventDate().plusDays(1).toString()
		);
		PassBundleDto passBundleDto = new PassBundleDto(aPassCategory, singlePassOption, someEventDates);

		PassBundle passBundle = passBundleFactory.build(passBundleDto);

		assertEquals(passBundle.getOption().toString(), PassOptions.SINGLE_PASS.toString());
	}

	@Test
	void build_shouldBuildOption_whenOptionIsPackage() {
		String aPassCategory = PassCategories.SUPERNOVA.toString();
		String packageOption = PassOptions.PACKAGE.toString();
		PassBundleDto passBundleDto = new PassBundleDto(aPassCategory, packageOption, null);

		PassBundle passBundle = passBundleFactory.build(passBundleDto);

		assertEquals(passBundle.getOption().toString(), PassOptions.PACKAGE.toString());
	}

	@ParameterizedTest
	@ValueSource(ints = { 1, 2, 5 })
	void build_shouldBuildNoDiscountPriceCalculationStrategy_whenPassOptionIsSinglePassAndPassCategoryIsSupernova(
			int passQuantity) {
		String supernovaCategory = PassCategories.SUPERNOVA.toString();
		String singlePassOption = PassOptions.SINGLE_PASS.toString();
		List<String> someEventDates = Collections.nCopies(passQuantity, EventDate.getStartEventDate().toString());
		PassBundleDto passBundleDto = new PassBundleDto(supernovaCategory, singlePassOption, someEventDates);
		PriceCalculationStrategy priceCalculationStrategy = new NoDiscountPriceCalculationStrategy();

		PassBundle passBundle = passBundleFactory.build(passBundleDto);
		Money passPrice = passBundle.getPasses().get(0).getPrice();
		Money expectedPrice = priceCalculationStrategy.calculatePassPrice(passQuantity, passPrice);

		assertEquals(expectedPrice, passBundle.getPrice());
	}

	@ParameterizedTest
	@ValueSource(ints = { 1, 2, SupergiantPriceCalculationStrategy.PASS_QUANTITY_THRESHOLD + 1 })
	void build_shouldBuildSupergiantPriceCalculationStrategy_whenPassOptionIsSinglePassAndPassCategoryIsSupergiant(
			int passQuantity) {
		String supergiantCategory = PassCategories.SUPERGIANT.toString();
		String singlePassOption = PassOptions.SINGLE_PASS.toString();
		List<String> someEventDates = Collections.nCopies(passQuantity, EventDate.getStartEventDate().toString());
		PassBundleDto passBundleDto = new PassBundleDto(supergiantCategory, singlePassOption, someEventDates);
		PriceCalculationStrategy priceCalculationStrategy = new SupergiantPriceCalculationStrategy();
		Money passPrice = priceCalculationStrategy.calculatePassPrice(passQuantity,
				PassBundleFactory.SUPERGIANT_SINGLE_PASS_PRICE);
		Money expectedPrice = passPrice.multiply(new BigDecimal(passQuantity));

		PassBundle passBundle = passBundleFactory.build(passBundleDto);

		assertEquals(expectedPrice, passBundle.getPrice());
	}

	@ParameterizedTest
	@ValueSource(ints = { 1, 2, NebulaPriceCalculationStrategy.PASS_QUANTITY_THRESHOLD + 1 })
	void build_shouldBuildNebulaPriceCalculationStrategy_whenPassOptionIsSinglePassAndPassCategoryIsNebula(
			int passQuantity) {
		String nebulaCategory = PassCategories.NEBULA.toString();
		String singlePassOption = PassOptions.SINGLE_PASS.toString();
		List<String> someEventDates = Collections.nCopies(passQuantity, EventDate.getStartEventDate().toString());
		PassBundleDto passBundleDto = new PassBundleDto(nebulaCategory, singlePassOption, someEventDates);
		PriceCalculationStrategy priceCalculationStrategy = new NebulaPriceCalculationStrategy();
		Money passPrice = priceCalculationStrategy.calculatePassPrice(passQuantity,
				PassBundleFactory.NEBULA_SINGLE_PASS_PRICE);
		Money expectedPrice = passPrice.multiply(new BigDecimal(passQuantity));

		PassBundle passBundle = passBundleFactory.build(passBundleDto);

		assertEquals(expectedPrice, passBundle.getPrice());
	}

	@ParameterizedTest
	@EnumSource(PassCategories.class)
	void build_shouldBuildNoDiscountPriceCalculationStrategy_whenPassOptionIsPackage(PassCategories category) {
		String aPassCategory = category.toString();
		String packageOption = PassOptions.PACKAGE.toString();
		PassBundleDto passBundleDto = new PassBundleDto(aPassCategory, packageOption, null);
		PriceCalculationStrategy priceCalculationStrategy = new NoDiscountPriceCalculationStrategy();

		PassBundle passBundle = passBundleFactory.build(passBundleDto);
		Money passPrice = passBundle.getPasses().get(0).getPrice();
		Money expectedPrice = priceCalculationStrategy.calculatePassPrice(1, passPrice);

		assertEquals(expectedPrice, passBundle.getPrice());
	}

	@Test
	void build_shouldThrowInvalidFormatException_whenEventDateIsNotNullAndPassOptionIsPackage() {
		String aPassCategory = PassCategories.SUPERNOVA.toString();
		String packageOption = PassOptions.PACKAGE.toString();
		List<String> someEventDates = Collections.singletonList(EventDate.getStartEventDate().toString());
		PassBundleDto passBundleDto = new PassBundleDto(aPassCategory, packageOption, someEventDates);

		assertThrows(InvalidFormatException.class, () -> passBundleFactory.build(passBundleDto));
	}

	@Test
	void build_shouldThrowSinglePassWithoutEventDateException_whenEventDateIsNullAndPassOptionIsSinglePass() {
		String aPassCategory = PassCategories.SUPERNOVA.toString();
		String singlePassOption = PassOptions.SINGLE_PASS.toString();
		PassBundleDto passBundleDto = new PassBundleDto(aPassCategory, singlePassOption, null);

		assertThrows(InvalidFormatException.class, () -> passBundleFactory.build(passBundleDto));
	}

	@Test
	void build_shouldThrowInvalidFormatException_whenPassCategoryDoesNotExist() {
		String anInvalidPassCategory = "anInvalidPassCategory";
		String aPassOption = PassOptions.PACKAGE.toString();
		PassBundleDto passBundleDto = new PassBundleDto(anInvalidPassCategory, aPassOption, null);

		assertThrows(InvalidFormatException.class, () -> passBundleFactory.build(passBundleDto));
	}

	@Test
	void build_shouldThrowInvalidFormatException_whenPassOptionDoesNotExist() {
		String aPassCategory = PassCategories.SUPERNOVA.toString();
		String anInvalidPassOption = "anInvalidPassOption";
		PassBundleDto passBundleDto = new PassBundleDto(aPassCategory, anInvalidPassOption, null);

		assertThrows(InvalidFormatException.class, () -> passBundleFactory.build(passBundleDto));
	}

	@Test
	void build_shouldThrowInvalidFormatException_whenEventDateIsInvalid() {
		String aPassCategory = PassCategories.SUPERNOVA.toString();
		String aPassOption = PassOptions.PACKAGE.toString();
		List<String> anInvalidEventDate = Collections.singletonList("anInvalidEventDate");
		PassBundleDto passBundleDto = new PassBundleDto(aPassCategory, aPassOption, anInvalidEventDate);

		assertThrows(InvalidFormatException.class, () -> passBundleFactory.build(passBundleDto));
	}
}