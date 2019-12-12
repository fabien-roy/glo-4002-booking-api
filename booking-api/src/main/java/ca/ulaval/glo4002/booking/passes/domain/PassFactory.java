package ca.ulaval.glo4002.booking.passes.domain;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ca.ulaval.glo4002.booking.program.events.domain.EventDateFactory;
import ca.ulaval.glo4002.booking.numbers.NumberGenerator;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.profits.domain.Money;

public class PassFactory {

	private final PassNumberGenerator numberGenerator;
	private final EventDateFactory eventDateFactory;

	@Inject
	public PassFactory(PassNumberGenerator numberGenerator, EventDateFactory eventDateFactory) {
		this.numberGenerator = numberGenerator;
		this.eventDateFactory = eventDateFactory;
	}

	public List<Pass> createAll(List<String> eventDates, Money passPrice) {
		List<Pass> passes = new ArrayList<>();

		if (eventDates == null) {
			passes.add(new Pass(numberGenerator.generate(), passPrice));
		} else {
			eventDates.forEach(eventDate -> {
				EventDate builtEventDate = eventDateFactory.create(eventDate);
				Pass pass = new Pass(numberGenerator.generate(), passPrice, builtEventDate);
				passes.add(pass);
			});
		}

		return passes;
	}
}
