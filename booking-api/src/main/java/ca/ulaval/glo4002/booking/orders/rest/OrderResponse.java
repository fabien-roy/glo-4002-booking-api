package ca.ulaval.glo4002.booking.orders.rest;

import ca.ulaval.glo4002.booking.passes.rest.PassResponse;

import java.util.List;

public class OrderResponse {

    private float orderPrice;
    private List<PassResponse> passes;

    public OrderResponse(float orderPrice, List<PassResponse> passes) {
        this.orderPrice = orderPrice;
        this.passes = passes;
    }

    public float getOrderPrice() {
        return orderPrice;
    }

    public List<PassResponse> getPasses() {
        return passes;
    }

    public void setPasses(List<PassResponse> passes) {
        this.passes = passes;
    }
}
