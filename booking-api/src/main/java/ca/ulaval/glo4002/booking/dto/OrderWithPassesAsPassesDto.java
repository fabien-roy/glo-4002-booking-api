package ca.ulaval.glo4002.booking.dto;

import ca.ulaval.glo4002.booking.interfaces.configuration.DoubleContextualSerializer;
import ca.ulaval.glo4002.booking.interfaces.configuration.Precision;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

public class OrderWithPassesAsPassesDto implements Dto {

    public String orderNumber;

    @JsonSerialize(using = DoubleContextualSerializer.class)
    @Precision(precision = 2)
    public double orderPrice;

    public String orderDate;
    public String vendorCode;
    public List<PassDto> passes;
}
