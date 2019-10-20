package ca.ulaval.glo4002.booking.dto;

public class OrderWithPassesAsEventDatesDto {

    private String orderDate;
    private String vendorCode;
    private PassesDto passes;

    public OrderWithPassesAsEventDatesDto(String orderDate, String vendorCode, PassesDto passes) {
        this.orderDate = orderDate;
        this.vendorCode = vendorCode;
        this.passes = passes;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getVendorCode() {
        return vendorCode;
    }
}
