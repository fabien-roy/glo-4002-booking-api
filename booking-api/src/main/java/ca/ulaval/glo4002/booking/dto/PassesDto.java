package ca.ulaval.glo4002.booking.dto;

import java.util.List;

public class PassesDto {

    private Long passNumber;
    private String passCategory;
    private String passOption;
    private List<String> eventDates;

    public PassesDto(String passCategory, String passOption, List<String> eventDates) {
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
}
