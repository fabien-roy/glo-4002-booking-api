package ca.ulaval.glo4002.booking.dto;

public class PassDto {

    private Long passNumber;
    private String passCategory;
    private String passOption;
    private String eventDate;

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
