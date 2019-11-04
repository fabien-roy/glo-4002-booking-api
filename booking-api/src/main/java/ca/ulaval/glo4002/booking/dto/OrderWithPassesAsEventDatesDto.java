package ca.ulaval.glo4002.booking.dto;

public class OrderWithPassesAsEventDatesDto {

    private String orderDate;
    private String vendorCode;
    private PassBundleDto passes;

    public OrderWithPassesAsEventDatesDto() {
        // Empty constructor for parsing
    }

    public OrderWithPassesAsEventDatesDto(String orderDate, String vendorCode, PassBundleDto passes) {
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

    public PassBundleDto getPasses() {
        return passes;
    }

    public void setPasses(PassBundleDto passes) {
        this.passes = passes;
    }
}
