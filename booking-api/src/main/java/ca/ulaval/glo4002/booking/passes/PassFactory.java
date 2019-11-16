package ca.ulaval.glo4002.booking.passes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ca.ulaval.glo4002.booking.numbers.NumberGenerator;
import ca.ulaval.glo4002.booking.events.EventDate;
import ca.ulaval.glo4002.booking.profits.Money;
import ca.ulaval.glo4002.booking.exceptions.InvalidFormatException;

public class PassFactory {

	private final NumberGenerator numberGenerator;

	@Inject
	public PassFactory(NumberGenerator numberGenerator) {
		this.numberGenerator = numberGenerator;
	}

	public List<Pass> buildAll(List<String> eventDates, Money passPrice) {
		List<Pass> passes = new ArrayList<>();

		if (eventDates == null) {
			passes.add(new Pass(numberGenerator.generate(), passPrice));
		} else {
			eventDates.forEach(eventDate -> {
				LocalDate parsedEventDate = parseLocalDate(eventDate);
				EventDate builtEventDate = new EventDate(parsedEventDate);
				Pass pass = new Pass(numberGenerator.generate(), passPrice, builtEventDate);
				passes.add(pass);
			});
		}

		return passes;
	}

	private LocalDate parseLocalDate(String textEventDate) {
		try {
			return LocalDate.parse(textEventDate);
		} catch (Exception exception) {
			throw new InvalidFormatException();
		}
	}
}