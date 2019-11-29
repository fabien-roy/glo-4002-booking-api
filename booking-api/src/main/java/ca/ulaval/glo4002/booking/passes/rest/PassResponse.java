package ca.ulaval.glo4002.booking.passes.rest;

public class PassResponse {

    private Long passNumber;
    private String passCategory;
    private String passOption;
    private String eventDate;

    public PassResponse(Long passNumber, String passCategory, String passOption, String eventDate) {
        this.passNumber = passNumber;
        this.passCategory = passCategory;
        this.passOption = passOption;
        this.eventDate = eventDate;
    }

    public Long getPassNumber() {
        return passNumber;
    }

    public String getPassCategory() {
        return passCategory;
    }

    public String getPassOption() {
        return passOption;
    }

    public String getEventDate() {
        return eventDate;
    }
}