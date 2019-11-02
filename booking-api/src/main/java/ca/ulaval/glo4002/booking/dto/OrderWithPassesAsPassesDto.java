package ca.ulaval.glo4002.booking.dto;

import ca.ulaval.glo4002.booking.domain.money.Money;

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

    public double getOrderPrice() {
        return orderPrice;
    }

    public List<PassDto> getPasses() {
        return passes;
    }

    public void setPasses(List<PassDto> passes) {
        this.passes = passes;
    }
}
