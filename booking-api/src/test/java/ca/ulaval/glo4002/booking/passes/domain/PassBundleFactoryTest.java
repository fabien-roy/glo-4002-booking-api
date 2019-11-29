package ca.ulaval.glo4002.booking.passes.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.passes.rest.PassBundleRequest;
import ca.ulaval.glo4002.booking.program.events.domain.EventDateFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import ca.ulaval.glo4002.booking.numbers.Number;
import ca.ulaval.glo4002.booking.numbers.NumberGenerator;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.passes.domain.pricediscountstrategy.NebulaPriceDiscountStrategy;
import ca.ulaval.glo4002.booking.passes.domain.pricediscountstrategy.NoPriceDiscountStrategy;
import ca.ulaval.glo4002.booking.passes.domain.pricediscountstrategy.PriceDiscountStrategy;
import ca.ulaval.glo4002.booking.passes.domain.pricediscountstrategy.SupergiantPriceDiscountStrategy;
import ca.ulaval.glo4002.booking.interfaces.rest.exceptions.InvalidFormatException;

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
	void create_shouldCreatePassList() {
		String aPassCategory = PassCategories.SUPERNOVA.toString();
		String aPassOption = PassOptions.SINGLE_PASS.toString();
		PassBundleRequest passBundleRequest = new PassBundleRequest(aPassCategory, aPassOption, new ArrayList<>());
		Number aNumber = new Number(1L);
		BigDecimal aBigDecimal = new BigDecimal(100.0);
		Money aPrice = new Money(aBigDecimal);
		EventDate aEventDate = FestivalConfiguration.getDefaultStartEventDate();
		Pass pass = new Pass(aNumber, aPrice, aEventDate);
		PassFactory passFactory = mock(PassFactory.class);
		when(passFactory.createAll(any(), any())).thenReturn(Collections.singletonList(pass));
		passBundleFactory = new PassBundleFactory(passFactory);

		passBundleFactory.create(passBundleRequest);

		verify(passFactory).createAll(any(), any());
	}

	@Test
	void create_shouldCreateCategory_whenCategoryIsSupernova() {
		String supernovaCategory = PassCategories.SUPERNOVA.toString();
		String aPassOption = PassOptions.PACKAGE.toString();
		PassBundleRequest passBundleRequest = new PassBundleRequest(supernovaCategory, aPassOption, null);

		PassBundle passBundle = passBundleFactory.create(passBundleRequest);

		assertEquals(PassCategories.SUPERNOVA, passBundle.getCategory());
	}

	@Test
	void create_shouldCreateCategory_whenCategoryIsSupergiant() {
		String supergiantCategory = PassCategories.SUPERGIANT.toString();
		String aPassOption = PassOptions.PACKAGE.toString();
		PassBundleRequest passBundleRequest = new PassBundleRequest(supergiantCategory, aPassOption, null);

		PassBundle passBundle = passBundleFactory.create(passBundleRequest);

		assertEquals(PassCategories.SUPERGIANT, passBundle.getCategory());
	}

	@Test
	void create_shouldCreateCategory_whenCategoryIsNebula() {
		String nebulaPassCategory = PassCategories.NEBULA.toString();
		String aPassOption = PassOptions.PACKAGE.toString();
		PassBundleRequest passBundleRequest = new PassBundleRequest(nebulaPassCategory, aPassOption, null);

		PassBundle passBundle = passBundleFactory.create(passBundleRequest);

		assertEquals(PassCategories.NEBULA, passBundle.getCategory());
	}

	@Test
	void create_shouldCreatePricePerOption_whenCategoryIsSupernova() {
		String supernovaCategory = PassCategories.SUPERNOVA.toString();
		String aPassOption = PassOptions.PACKAGE.toString();
		PassBundleRequest passBundleRequest = new PassBundleRequest(supernovaCategory, aPassOption, null);

		PassBundle passBundle = passBundleFactory.create(passBundleRequest);

		assertEquals(PassBundleFactory.SUPERNOVA_PACKAGE_PRICE, passBundle.getPricePerOption(PassOptions.PACKAGE));
		assertEquals(PassBundleFactory.SUPERNOVA_SINGLE_PASS_PRICE, passBundle.getPricePerOption(PassOptions.SINGLE_PASS));
	}

	@Test
	void create_shouldCreatePricePerOption_whenCategoryIsSupergiant() {
		String supergiantCategory = PassCategories.SUPERGIANT.toString();
		String aPassOption = PassOptions.PACKAGE.toString();
		PassBundleRequest passBundleRequest = new PassBundleRequest(supergiantCategory, aPassOption, null);

		PassBundle passBundle = passBundleFactory.create(passBundleRequest);

		assertEquals(PassBundleFactory.SUPERGIANT_PACKAGE_PRICE, passBundle.getPricePerOption(PassOptions.PACKAGE));
		assertEquals(PassBundleFactory.SUPERGIANT_SINGLE_PASS_PRICE, passBundle.getPricePerOption(PassOptions.SINGLE_PASS));
	}

	@Test
	void create_shouldCreatePricePerOption_whenCategoryIsNebula() {
		String nebulaCategory = PassCategories.NEBULA.toString();
		String aPassOption = PassOptions.PACKAGE.toString();
		PassBundleRequest passBundleRequest = new PassBundleRequest(nebulaCategory, aPassOption, null);

		PassBundle passBundle = passBundleFactory.create(passBundleRequest);

		assertEquals(PassBundleFactory.NEBULA_PACKAGE_PRICE, passBundle.getPricePerOption(PassOptions.PACKAGE));
		assertEquals(PassBundleFactory.NEBULA_SINGLE_PASS_PRICE, passBundle.getPricePerOption(PassOptions.SINGLE_PASS));
	}

	@Test
	void create_shouldCreateOption_whenOptionIsSinglePass() {
		String aPassCategory = PassCategories.SUPERNOVA.toString();
		String singlePassOption = PassOptions.SINGLE_PASS.toString();
		List<String> someEventDates = Arrays.asList(
				FestivalConfiguration.getDefaultStartEventDate().toString(),
				FestivalConfiguration.getDefaultStartEventDate().plusDays(1).toString()
		);
		PassBundleRequest passBundleRequest = new PassBundleRequest(aPassCategory, singlePassOption, someEventDates);

		PassBundle passBundle = passBundleFactory.create(passBundleRequest);

		assertEquals(passBundle.getOption().toString(), PassOptions.SINGLE_PASS.toString());
	}

	@Test
	void create_shouldCreateOption_whenOptionIsPackage() {
		String aPassCategory = PassCategories.SUPERNOVA.toString();
		String packageOption = PassOptions.PACKAGE.toString();
		PassBundleRequest passBundleRequest = new PassBundleRequest(aPassCategory, packageOption, null);

		PassBundle passBundle = passBundleFactory.create(passBundleRequest);

		assertEquals(passBundle.getOption().toString(), PassOptions.PACKAGE.toString());
	}

	@ParameterizedTest
	@ValueSource(ints = { 1, 2, 5 })
	void create_shouldCreateNoPriceDiscountStrategy_whenPassOptionIsSinglePassAndPassCategoryIsSupernova(int passQuantity) {
		String supernovaCategory = PassCategories.SUPERNOVA.toString();
		String singlePassOption = PassOptions.SINGLE_PASS.toString();
		List<String> someEventDates = Collections.nCopies(passQuantity, FestivalConfiguration.getDefaultStartEventDate().toString());
		PassBundleRequest passBundleRequest = new PassBundleRequest(supernovaCategory, singlePassOption, someEventDates);
		PriceDiscountStrategy priceDiscountStrategy = new NoPriceDiscountStrategy();

		PassBundle passBundle = passBundleFactory.create(passBundleRequest);
		Money passPrice = passBundle.getPasses().get(0).getPrice();
		Money expectedPrice = priceDiscountStrategy.calculateDiscount(passQuantity, passPrice);

		assertEquals(expectedPrice, passBundle.getPrice());
	}

	@ParameterizedTest
	@ValueSource(ints = { 1, 2, SupergiantPriceDiscountStrategy.PASS_QUANTITY_THRESHOLD + 1 })
	void create_shouldCreateSupergiantPriceDiscountStrategy_whenPassOptionIsSinglePassAndPassCategoryIsSupergiant(int passQuantity) {
		String supergiantCategory = PassCategories.SUPERGIANT.toString();
		String singlePassOption = PassOptions.SINGLE_PASS.toString();
		List<String> someEventDates = Collections.nCopies(passQuantity, FestivalConfiguration.getDefaultStartEventDate().toString());
		PassBundleRequest passBundleRequest = new PassBundleRequest(supergiantCategory, singlePassOption, someEventDates);
		PriceDiscountStrategy priceDiscountStrategy = new SupergiantPriceDiscountStrategy();
		Money passPrice = priceDiscountStrategy.calculateDiscount(passQuantity, PassBundleFactory.SUPERGIANT_SINGLE_PASS_PRICE);
		Money expectedPrice = passPrice.multiply(new BigDecimal(passQuantity));

		PassBundle passBundle = passBundleFactory.create(passBundleRequest);

		assertEquals(expectedPrice, passBundle.getPrice());
	}

	@ParameterizedTest
	@ValueSource(ints = { 1, 2, NebulaPriceDiscountStrategy.PASS_QUANTITY_THRESHOLD + 1 })
	void create_shouldCreateNebulaPriceDiscountStrategy_whenPassOptionIsSinglePassAndPassCategoryIsNebula(int passQuantity) {
		String nebulaCategory = PassCategories.NEBULA.toString();
		String singlePassOption = PassOptions.SINGLE_PASS.toString();
		List<String> someEventDates = Collections.nCopies(passQuantity, FestivalConfiguration.getDefaultStartEventDate().toString());
		PassBundleRequest passBundleRequest = new PassBundleRequest(nebulaCategory, singlePassOption, someEventDates);
		PriceDiscountStrategy priceDiscountStrategy = new NebulaPriceDiscountStrategy();
		Money passPrice = priceDiscountStrategy.calculateDiscount(passQuantity, PassBundleFactory.NEBULA_SINGLE_PASS_PRICE);
		Money expectedPrice = passPrice.multiply(new BigDecimal(passQuantity));

		PassBundle passBundle = passBundleFactory.create(passBundleRequest);

		assertEquals(expectedPrice, passBundle.getPrice());
	}

	@ParameterizedTest
	@EnumSource(PassCategories.class)
	void create_shouldCreateNoPriceDiscountStrategy_whenPassOptionIsPackage(PassCategories category) {
		String aPassCategory = category.toString();
		String packageOption = PassOptions.PACKAGE.toString();
		PassBundleRequest passBundleRequest = new PassBundleRequest(aPassCategory, packageOption, null);
		PriceDiscountStrategy priceDiscountStrategy = new NoPriceDiscountStrategy();

		PassBundle passBundle = passBundleFactory.create(passBundleRequest);
		Money passPrice = passBundle.getPasses().get(0).getPrice();
		Money expectedPrice = priceDiscountStrategy.calculateDiscount(1, passPrice);

		assertEquals(expectedPrice, passBundle.getPrice());
	}

	@Test
	void create_shouldThrowInvalidFormatException_whenEventDateIsNotNullAndPassOptionIsPackage() {
		String aPassCategory = PassCategories.SUPERNOVA.toString();
		String packageOption = PassOptions.PACKAGE.toString();
		List<String> someEventDates = Collections.singletonList(FestivalConfiguration.getDefaultStartEventDate().toString());
		PassBundleRequest passBundleRequest = new PassBundleRequest(aPassCategory, packageOption, someEventDates);

		assertThrows(InvalidFormatException.class, () -> passBundleFactory.create(passBundleRequest));
	}

	@Test
	void create_shouldThrowSinglePassWithoutEventDateException_whenEventDateIsNullAndPassOptionIsSinglePass() {
		String aPassCategory = PassCategories.SUPERNOVA.toString();
		String singlePassOption = PassOptions.SINGLE_PASS.toString();
		PassBundleRequest passBundleRequest = new PassBundleRequest(aPassCategory, singlePassOption, null);

		assertThrows(InvalidFormatException.class, () -> passBundleFactory.create(passBundleRequest));
	}

	@Test
	void create_shouldThrowInvalidFormatException_whenPassCategoryDoesNotExist() {
		String anInvalidPassCategory = "anInvalidPassCategory";
		String aPassOption = PassOptions.PACKAGE.toString();
		PassBundleRequest passBundleRequest = new PassBundleRequest(anInvalidPassCategory, aPassOption, null);

		assertThrows(InvalidFormatException.class, () -> passBundleFactory.create(passBundleRequest));
	}

	@Test
	void create_shouldThrowInvalidFormatException_whenPassOptionDoesNotExist() {
		String aPassCategory = PassCategories.SUPERNOVA.toString();
		String anInvalidPassOption = "anInvalidPassOption";
		PassBundleRequest passBundleRequest = new PassBundleRequest(aPassCategory, anInvalidPassOption, null);

		assertThrows(InvalidFormatException.class, () -> passBundleFactory.create(passBundleRequest));
	}

	@Test
	void create_shouldThrowInvalidFormatException_whenEventDateIsInvalid() {
		String aPassCategory = PassCategories.SUPERNOVA.toString();
		String aPassOption = PassOptions.PACKAGE.toString();
		List<String> anInvalidEventDate = Collections.singletonList("anInvalidEventDate");
		PassBundleRequest passBundleRequest = new PassBundleRequest(aPassCategory, aPassOption, anInvalidEventDate);

		assertThrows(InvalidFormatException.class, () -> passBundleFactory.create(passBundleRequest));
	}
}