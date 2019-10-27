package ca.ulaval.glo4002.booking.dto;

public class OrderWithPassesAsEventDatesDto {

    private String orderDate;
    private String vendorCode;
    private PassListDto passes;

    public OrderWithPassesAsEventDatesDto() {
        // Empty constructor for parsing
    }

    public OrderWithPassesAsEventDatesDto(String orderDate, String vendorCode, PassListDto passes) {
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

    public PassListDto getPasses() {
        return passes;
    }

    public void setPasses(PassListDto passes) {
        this.passes = passes;
    }
}
