package ca.ulaval.glo4002.booking.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDto implements Dto {

    public LocalDateTime orderDate;
    public String vendorCode;
    public List<PassDto> passes;
}
