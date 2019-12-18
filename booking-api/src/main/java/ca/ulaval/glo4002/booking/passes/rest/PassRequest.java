package ca.ulaval.glo4002.booking.passes.rest;

import java.util.List;

public class PassRequest {

	private String passCategory;
	private String passOption;
	private List<String> eventDates;

	public PassRequest() {
		// Empty constructor for parsing
	}

	public PassRequest(String passCategory, String passOption) {
		this.passCategory = passCategory;
		this.passOption = passOption;
	}

	public PassRequest(String passCategory, String passOption, List<String> eventDates) {
		this.passCategory = passCategory;
		this.passOption = passOption;
		this.eventDates = eventDates;
	}

	public String getPassCategory() {
		return passCategory;
	}

	public String getPassOption() {
		return passOption;
	}

	public List<String> getEventDates() {
		return eventDates;
	}
}
