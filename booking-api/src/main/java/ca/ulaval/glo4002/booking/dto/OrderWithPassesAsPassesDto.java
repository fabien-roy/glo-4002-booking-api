package ca.ulaval.glo4002.booking.dto;

import ca.ulaval.glo4002.booking.domain.Money;

import java.util.List;

public class OrderWithPassesAsPassesDto {

    /*
    @JsonSerialize(using = DoubleContextualSerializer.class)
    @Precision(precision = 2)
    */
    private double orderPrice;
    private List<PassDto> passes;

    public OrderWithPassesAsPassesDto(double orderPrice, List<PassDto> passes) {
        this.orderPrice = orderPrice;
        this.passes = passes;
    }

    public double getPrice() {
        return orderPrice;
    }
}
