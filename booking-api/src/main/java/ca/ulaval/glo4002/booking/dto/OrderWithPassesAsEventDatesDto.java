package ca.ulaval.glo4002.booking.dto;

public class OrderWithPassesAsEventDatesDto implements Dto {

    public Long orderNumber;
    public String orderDate;
    public String vendorCode;
    public PassesDto passes;
}
