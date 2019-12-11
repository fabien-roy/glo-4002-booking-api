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
		BigDecimal multipliedValue = value.multiply(factor);

		return new Money(multipliedValue);
	}

	public Money add(Money money) {
		BigDecimal addedValue = this.value.add(money.getValue());

		return new Money(addedValue);
	}

	public Money subtract(Money money) {
		BigDecimal subtractedValue = this.value.subtract(money.getValue());

		return new Money(subtractedValue);
	}

	@Override
	public int compareTo(Money other) {
		return comparator.compare(this, other);
	}

	public Money applyAmountDiscount(BigDecimal discount) {
		BigDecimal discountedValue = value.subtract(discount);

		return new Money(discountedValue);
	}

	// TODO : Test Money.applyPercentageDiscount
	public Money applyPercentageDiscount(BigDecimal discount) {
		PercentageDiscount percentageDiscount = new PercentageDiscount(discount);
		BigDecimal discountedValue = percentageDiscount.apply(this.value);

		return new Money(discountedValue);
	}
}