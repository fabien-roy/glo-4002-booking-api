package ca.ulaval.glo4002.booking.profits;

import java.math.BigDecimal;
import java.util.Comparator;

public class Money implements Comparable<Money> {

	private BigDecimal value;
	private Comparator<Money> comparator = Comparator.comparing(Money::getValue);

	public Money(BigDecimal value) {
		this.value = value;
	}

	public Money(Money money) {
		this.value = new BigDecimal(money.value.doubleValue());
	}

	public BigDecimal getValue() {
		return value;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null || getClass() != object.getClass())
			return false;

		Money money = (Money) object;

		return value.equals(money.getValue());
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	public Money multiply(BigDecimal factor) {
		value = value.multiply(factor);

		return this;
	}

	// TODO : Test
	public Money divide(BigDecimal divisor) {
		value = value.divide(divisor);

		return this;
	}

	public Money add(Money money) {
		BigDecimal amount = new BigDecimal(money.value.doubleValue());

		this.value = this.value.add(amount);

		return new Money(this.value);
	}

	public Money subtract(Money money) {
		BigDecimal amount = new BigDecimal(money.value.doubleValue());

		this.value = this.value.subtract(amount);

		return new Money(this.value);
	}

	@Override
	public int compareTo(Money other) {
		return comparator.compare(this, other);
	}

	public void applyAmountDiscount(BigDecimal discount) {
		AmountDiscount amountDiscount = new AmountDiscount(discount);
		this.value = amountDiscount.apply(this.value);
	}

	public void applyPercentageDiscount(BigDecimal discount) {
		PercentageDiscount percentageDiscount = new PercentageDiscount(discount);
		this.value = percentageDiscount.apply(this.value);
	}
}