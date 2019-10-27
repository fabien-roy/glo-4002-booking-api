package ca.ulaval.glo4002.booking.factories;

import java.math.BigDecimal;

import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.domain.shuttles.Capacity;
import ca.ulaval.glo4002.booking.domain.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.enums.ShuttleCategories;

public class ShuttleFactory {
	
	private static final Capacity ETSPACESHIP_CAPACITY = new Capacity(1);
	private static final Money ETSPACESHIP_PRICE = new Money(new BigDecimal(100000));

	private static final Capacity MILLENIUMFALCON_CAPACITY = new Capacity(20);
	private static final Money MILLENIUMFALCON_PRICE = new Money(new BigDecimal(65000));
	
	private static final Capacity SPACEX_CAPACITY = new Capacity(30);
	private static final Money SPACEX_PRICE = new Money(new BigDecimal(30000));
	
	public Shuttle buildShuttle(ShuttleCategories category) {
		switch(category) {
		case ETSPACESHIP:
			return new Shuttle(ShuttleCategories.ETSPACESHIP, ETSPACESHIP_CAPACITY, ETSPACESHIP_PRICE);
		default:
		case MILLENIUMFALCON:
			return new Shuttle(ShuttleCategories.MILLENIUMFALCON, MILLENIUMFALCON_CAPACITY, MILLENIUMFALCON_PRICE);
		case SPACEX:
			return new Shuttle(ShuttleCategories.SPACEX, SPACEX_CAPACITY, SPACEX_PRICE);
		}
	}
}
