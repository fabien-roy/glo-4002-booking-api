package ca.ulaval.glo4002.booking.dto;

import java.time.LocalDate;
import java.util.List;

public class PassDTO {
    private String passCategory;
    private String passOption;
    private List<LocalDate> eventDates;

    public String getPassCategory() {
        return passCategory;
    }

    public void setPassCategory(String passCategory) {
        this.passCategory = passCategory;
    }

    public String getPassOption() {
        return passOption;
    }

    public void setPassOption(String passOption) {
        this.passOption = passOption;
    }

    public List<LocalDate> getEventDates() {
        return eventDates;
    }

    public void setEventDates(List<LocalDate> eventDates) {
        this.eventDates = eventDates;
    }
}
