package ca.ulaval.glo4002.booking.program.events.rest;

public class EventDatesDto {

    private String beginDate;
    private String endDate;

    public EventDatesDto() {
        // Empty constructor for parsing
    }

    public String getBeginDate() {
        return beginDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
