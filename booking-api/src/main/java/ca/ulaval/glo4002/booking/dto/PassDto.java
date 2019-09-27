package ca.ulaval.glo4002.booking.dto;

import java.time.LocalDate;
import java.util.List;

public class PassDto implements Dto {

    //TODO: Put these attributes private and use getters and setters
    public Long passNumber;
    public String passCategory;
    public String passOption;
    public List<LocalDate> eventDates;

}
