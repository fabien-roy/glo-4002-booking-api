package ca.ulaval.glo4002.booking.domain.oxygen;

import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.enums.OxygenCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OxygenTankTest {

    private OxygenTank oxygenTank;
    private OxygenDate requestDate;

    @BeforeEach
    void setUpRequestDate() {
        requestDate = new OxygenDate(LocalDate.of(2050, 7, 1));
    }

    @Test
    void getCategoryOxygenCategoryA_shouldReturnCategoryA() {
        oxygenTank = new OxygenTank(OxygenCategory.A, requestDate);

        assertEquals(OxygenCategory.A, oxygenTank.getCategory());
    }

    @Test
    void getCategoryOxygenCategoryB_shouldReturnCategoryB() {
        oxygenTank = new OxygenTank(OxygenCategory.B, requestDate);

        assertEquals(OxygenCategory.B, oxygenTank.getCategory());
    }

    @Test
    void getCategoryOxygenCategoryE_shouldReturnCategoryE() {
        oxygenTank = new OxygenTank(OxygenCategory.E, requestDate);

        assertEquals(OxygenCategory.E, oxygenTank.getCategory());
    }

    @Test
    void getReadyDateOxygenCategoryA_shouldReturn20DaysLater() {
        OxygenDate expectedDate = requestDate;
        expectedDate.addDays(20);

        oxygenTank = new OxygenTank(OxygenCategory.A, requestDate);

        assertEquals(expectedDate, oxygenTank.getReadyDate());
    }

    @Test
    void getReadyDateOxygenCategoryB_shouldReturn10DaysLater() {
        OxygenDate expectedDate = requestDate;
        expectedDate.addDays(10);

        oxygenTank = new OxygenTank(OxygenCategory.B, requestDate);

        assertEquals(expectedDate, oxygenTank.getReadyDate());
    }

    @Test
    void getReadyDateOxygenCategoryE_shouldReturnSameDay() {
        oxygenTank = new OxygenTank(OxygenCategory.E, requestDate);

        assertEquals(requestDate, oxygenTank.getReadyDate());
    }

    @Test
    void getMoneyOfOxygenCategoryA_shouldReturnCorrectPrice() {
        BigDecimal tankPrice = new BigDecimal((OxygenTank.CATEGORY_A_NUMBER_OF_RESOURCES_NEEDED * OxygenTank.CATEGORY_A_RESOURCE_PRICE) / OxygenTank.CATEGORY_A_NUMBER_OF_TANKS_CREATED);
        Money expectedPrice = new Money(tankPrice);

        oxygenTank = new OxygenTank(OxygenCategory.A, requestDate);

        assertEquals(expectedPrice, oxygenTank.getPrice());
    }

    @Test
    void getMoneyOfOxygenCategoryB_shouldReturnCorrectPrice() {
        BigDecimal tankPrice = new BigDecimal((OxygenTank.CATEGORY_B_NUMBER_OF_RESOURCES_NEEDED * OxygenTank.CATEGORY_B_RESOURCE_PRICE) / OxygenTank.CATEGORY_B_NUMBER_OF_TANKS_CREATED);
        Money expectedPrice = new Money(tankPrice);

        oxygenTank = new OxygenTank(OxygenCategory.B, requestDate);

        assertEquals(expectedPrice, oxygenTank.getPrice());
    }

    @Test
    void getMoneyOfOxygenCategoryE_shouldReturnCorrectPrice() {
        BigDecimal tankPrice = new BigDecimal((OxygenTank.CATEGORY_E_NUMBER_OF_RESOURCES_NEEDED * OxygenTank.CATEGORY_E_RESOURCE_PRICE) / OxygenTank.CATEGORY_E_NUMBER_OF_TANKS_CREATED);
        Money expectedPrice = new Money(tankPrice);

        OxygenTank oxygenE = new OxygenTank(OxygenCategory.E, requestDate);

        assertEquals(expectedPrice, oxygenE.getPrice());
    }

}
