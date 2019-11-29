package ca.ulaval.glo4002.booking.passes.domain;

import java.util.List;

public class PassBundleDto {

	private String passCategory;
	private String passOption;
	private List<String> eventDates;

	public PassBundleDto() {
		// Empty constructor for parsing
	}

	public PassBundleDto(String passCategory, String passOption) {
		this.passCategory = passCategory;
		this.passOption = passOption;
	}

	public PassBundleDto(String passCategory, String passOption, List<String> eventDates) {
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
