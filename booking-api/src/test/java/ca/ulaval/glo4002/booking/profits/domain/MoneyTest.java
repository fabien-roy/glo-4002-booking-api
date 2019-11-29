package ca.ulaval.glo4002.booking.profits.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import ca.ulaval.glo4002.booking.profits.domain.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MoneyTest {

    private Money aMoney;
    private Money anotherMoney;
    
    private static final BigDecimal anAmount = new BigDecimal(255);
    private static final BigDecimal anotherAmount = new BigDecimal(25);

    @BeforeEach
    void setupMoney() {
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
    void hashCode_shouldReturnValueHashCode() {
        int expectedHashCode = anAmount.hashCode();

        int hashCode = aMoney.hashCode();

        assertEquals(expectedHashCode, hashCode);
    }
    
    @Test
    void add_shouldAddTheMoney() {
    	aMoney.add(anotherMoney);
    	
    	assertEquals(anAmount.add(anotherAmount), aMoney.getValue());
    }
    
    @Test
    void substract_shouldSubtractTheMoney() {
    	aMoney.subtract(anotherMoney);
    	
    	assertEquals(anAmount.subtract(anotherAmount), aMoney.getValue());
    }
    
    @Test
    void multiply_shouldMultiplyTheMoney() {
    	BigDecimal multiplyResult = anAmount.multiply(anotherAmount);
    	
    	aMoney.multiply(anotherAmount);
    	
    	assertEquals(multiplyResult, aMoney.getValue());
    }
}
