package ca.ulaval.glo4002.booking.dto;

import java.util.List;

public class PassesDto implements Dto {

    public Long passNumber;
    public String passCategory;
    public String passOption;
    public List<String> eventDates;
}
