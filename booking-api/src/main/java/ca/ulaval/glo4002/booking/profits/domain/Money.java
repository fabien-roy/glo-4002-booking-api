package ca.ulaval.glo4002.booking.profits.domain;

import java.math.BigDecimal;
import java.util.Comparator;

public class Money implements Comparable<Money> {

	private BigDecimal value;
	private final Comparator<Money> comparator = Comparator.comparing(Money::getValue);

	public Money(BigDecimal value) {
		this.value = value;
	}

	public Money(Money money) {
		this.value = money.getValue();
	}

	public BigDecimal getValue() {
		return value;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null || getClass() != object.getClass())
			return false;

		Money money = (Money) object;

		// Using compareTo since BigDecimal.equals gives "0.0 != 0.00"
		return value.compareTo(money.getValue()) == 0;
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	public Money multiply(BigDecimal factor) {
		value = value.multiply(factor);

		return this;
	}

	public Money add(Money money) {
		this.value = this.value.add(money.getValue());

		return new Money(this.value);
	}

	public Money subtract(Money money) {
		this.value = this.value.subtract(money.getValue());

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