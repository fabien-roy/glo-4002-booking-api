package ca.ulaval.glo4002.booking.dto;

import java.util.List;

public class OrderWithPassesAsPassesDto implements Dto {

    public Long orderNumber;
    public String orderDate;
    public String vendorCode;
    public List<PassDto> passes;
}
