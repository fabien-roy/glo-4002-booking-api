package ca.ulaval.glo4002.booking.dto.events;

public class ProgramEventDto {

    private String eventDate;
    private String am;
    private String pm;

    public ProgramEventDto(String eventDate, String am, String pm) {
        this.eventDate = eventDate;
        this.am = am;
        this.pm = pm;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getAm() {
        return am;
    }

    public String getPm() {
        return pm;
    }
}
