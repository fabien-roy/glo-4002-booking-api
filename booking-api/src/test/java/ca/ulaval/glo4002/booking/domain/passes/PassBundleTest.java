package ca.ulaval.glo4002.booking.domain.passes;

import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.enums.PassOptions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PassBundleTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    public void constructing_shouldSetCorrectPrice(int passQuantity) {
        Money passPrice = new Money(new BigDecimal(100.0));
        Money expectedPrice = passPrice.multiply(new BigDecimal(passQuantity));
        Pass pass = mock(Pass.class);
        when(pass.getPrice()).thenReturn(passPrice);
        List<Pass> passes = Collections.nCopies(passQuantity, pass);

        PassBundle passBundle = new PassBundle(
                passes,
                new PassCategory(PassCategories.SUPERNOVA, null),
                PassOptions.PACKAGE
        );

        assertEquals(expectedPrice, passBundle.getPrice());
    }
}