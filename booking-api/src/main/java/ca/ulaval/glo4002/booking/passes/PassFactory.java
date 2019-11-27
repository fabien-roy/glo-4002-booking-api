package ca.ulaval.glo4002.booking.passes;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ca.ulaval.glo4002.booking.events.EventDateFactory;
import ca.ulaval.glo4002.booking.numbers.NumberGenerator;
import ca.ulaval.glo4002.booking.events.EventDate;
import ca.ulaval.glo4002.booking.profits.Money;

public class PassFactory {

	private final NumberGenerator numberGenerator;
	private final EventDateFactory eventDateFactory;

	@Inject
	public PassFactory(NumberGenerator numberGenerator, EventDateFactory eventDateFactory) {
		this.numberGenerator = numberGenerator;
		this.eventDateFactory = eventDateFactory;
	}

	public List<Pass> buildAll(List<String> eventDates, Money passPrice) {
		List<Pass> passes = new ArrayList<>();

		if (eventDates == null) {
			passes.add(new Pass(numberGenerator.generate(), passPrice));
		} else {
			eventDates.forEach(eventDate -> {
				EventDate builtEventDate = eventDateFactory.build(eventDate);
				Pass pass = new Pass(numberGenerator.generate(), passPrice, builtEventDate);
				passes.add(pass);
			});
		}

		return passes;
	}
}
