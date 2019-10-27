package ca.ulaval.glo4002.booking.dto;

import ca.ulaval.glo4002.booking.domain.money.Money;

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
