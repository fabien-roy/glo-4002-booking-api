package ca.ulaval.glo4002.booking.shuttles.domain;

import java.math.BigDecimal;

import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.passes.domain.PassCategories;

public class ShuttleFactory {
	
	static final Integer ET_SPACESHIP_MAX_CAPACITY = 1;
	static final Money ET_SPACESHIP_PRICE = new Money(new BigDecimal(100000));

	static final Integer MILLENNIUM_FALCON_MAX_CAPACITY = 20;
	static final Money MILLENNIUM_FALCON_PRICE = new Money(new BigDecimal(65000));
	
	static final Integer SPACE_X_MAX_CAPACITY = 30;
	static final Money SPACE_X_PRICE = new Money(new BigDecimal(30000));

	public ShuttleCategories buildCategory(PassCategories category) {
		switch(category) {
			case SUPERNOVA:
				return ShuttleCategories.ET_SPACESHIP;
			case SUPERGIANT:
				return ShuttleCategories.MILLENNIUM_FALCON;
			default:
			case NEBULA:
				return ShuttleCategories.SPACE_X;
		}
	}

	public Shuttle build(ShuttleCategories category) {
		switch(category) {
			case ET_SPACESHIP:
                return new Shuttle(
                		ShuttleCategories.ET_SPACESHIP,
                		ET_SPACESHIP_MAX_CAPACITY,
                		ET_SPACESHIP_PRICE
				);
			case MILLENNIUM_FALCON:
                return new Shuttle(
                		ShuttleCategories.MILLENNIUM_FALCON,
                		MILLENNIUM_FALCON_MAX_CAPACITY,
                		MILLENNIUM_FALCON_PRICE
				);
			default:
			case SPACE_X:
                return new Shuttle(
                		ShuttleCategories.SPACE_X,
                		SPACE_X_MAX_CAPACITY,
                		SPACE_X_PRICE
				);
		}
	}
}
