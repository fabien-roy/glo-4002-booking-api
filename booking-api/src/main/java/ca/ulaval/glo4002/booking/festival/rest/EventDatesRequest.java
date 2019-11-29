package ca.ulaval.glo4002.booking.festival.rest;

public class EventDatesRequest {

    private String beginDate;
    private String endDate;

    public EventDatesRequest() {
        // Empty constructor for parsing
    }

    public String getBeginDate() {
        return beginDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
