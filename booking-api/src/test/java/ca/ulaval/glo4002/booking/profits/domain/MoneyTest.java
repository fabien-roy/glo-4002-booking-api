package ca.ulaval.glo4002.booking.profits.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    private Money aMoney;
    private Money anotherMoney;
    
    private static final BigDecimal anAmount = new BigDecimal(255);
    private static final BigDecimal anotherAmount = new BigDecimal(25);

    @BeforeEach
    void setUpMoney() {
    	aMoney = new Money(anAmount);
    	anotherMoney = new Money(anotherAmount);
    }
    
    @Test
    void equals_shouldReturnFalse_whenObjectIsNotId() {
        Object object = new Object();

        boolean result = aMoney.equals(object);

        assertFalse(result);
    }

    @Test
    void equals_shouldReturnTrue_whenValuesAreEqual() {
        Money moneyWithSameValue = new Money(anAmount);

        boolean result = moneyWithSameValue.equals(aMoney);

        assertTrue(result);
    }

    @Test
    void equals_shouldReturnFalse_whenValuesAreNotEqual() {
        boolean result = anotherMoney.equals(aMoney);
        
        assertFalse(result);
    }

    @Test
    void add_shouldAddTheMoney() {
    	aMoney = aMoney.add(anotherMoney);
    	
    	assertEquals(anAmount.add(anotherAmount), aMoney.getValue());
    }
    
    @Test
    void subtract_shouldSubtractTheMoney() {
    	aMoney = aMoney.subtract(anotherMoney);
    	
    	assertEquals(anAmount.subtract(anotherAmount), aMoney.getValue());
    }
    
    @Test
    void multiply_shouldMultiplyTheMoney() {
    	BigDecimal multiplyResult = anAmount.multiply(anotherAmount);
    	
    	aMoney = aMoney.multiply(anotherAmount);
    	
    	assertEquals(multiplyResult, aMoney.getValue());
    }

    @Test
    void applyAmount_shouldApplyTheDiscount() {
        aMoney = new Money(BigDecimal.valueOf(100.0));
        BigDecimal amount = BigDecimal.valueOf(10.0);
        BigDecimal expectedValue = BigDecimal.valueOf(90.0);

        aMoney = aMoney.applyAmountDiscount(amount);

        assertEquals(expectedValue, aMoney.getValue());
    }

    @Test
    void apply_shouldApply() {
        aMoney = new Money(BigDecimal.valueOf(100.0));
        BigDecimal percentage = BigDecimal.valueOf(.1);
        BigDecimal expectedValue = BigDecimal.valueOf(90.0);

        aMoney = aMoney.applyPercentageDiscount(percentage);

        // Using BigDecimal.compareTo since "0.0 != 0.00"
        assertEquals(0, expectedValue.compareTo(aMoney.getValue()));
    }
}
