package ca.ulaval.glo4002.booking.dto;

import java.util.List;

public class OrderWithPassesAsPassesDto {

    // TODO : Check if we want orderNumber in response DTO
    private String orderNumber;
    /*
    @JsonSerialize(using = DoubleContextualSerializer.class)
    @Precision(precision = 2)
    */
    private double orderPrice;
    private List<PassDto> passes;

    public OrderWithPassesAsPassesDto(String orderNumber, double orderPrice, List<PassDto> passes) {
        this.orderNumber = orderNumber;
        this.orderPrice = orderPrice;
        this.passes = passes;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public double getPrice() {
        return orderPrice;
    }
}
