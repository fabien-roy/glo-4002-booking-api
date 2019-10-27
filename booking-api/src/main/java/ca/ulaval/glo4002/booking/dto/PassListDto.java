package ca.ulaval.glo4002.booking.dto;

import java.util.List;

public class PassListDto {

    private String passCategory;
    private String passOption;
    private List<String> eventDates;

    public PassListDto() {
        // Empty constructor for parsing
    }

    public PassListDto(String passCategory, String passOption, List<String> eventDates) {
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
