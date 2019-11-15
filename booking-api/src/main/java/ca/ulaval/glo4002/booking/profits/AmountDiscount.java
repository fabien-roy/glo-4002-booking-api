package ca.ulaval.glo4002.booking.profits;

import java.math.BigDecimal;

public class AmountDiscount {

	private BigDecimal amount;

	public AmountDiscount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal apply(BigDecimal value) {
		return value.subtract(amount);
	}
}
