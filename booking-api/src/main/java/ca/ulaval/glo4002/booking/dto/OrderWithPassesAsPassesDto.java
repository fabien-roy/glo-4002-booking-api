package ca.ulaval.glo4002.booking.dto;

import java.util.List;

public class OrderWithPassesAsPassesDto {

    private String orderNumber;
    /*
    @JsonSerialize(using = DoubleContextualSerializer.class)
    @Precision(precision = 2)
    */
    private double orderPrice;
    private String orderDate;
    private String vendorCode;
    private List<PassDto> passes;

    public String getOrderNumber() {
        return orderNumber;
    }
}
