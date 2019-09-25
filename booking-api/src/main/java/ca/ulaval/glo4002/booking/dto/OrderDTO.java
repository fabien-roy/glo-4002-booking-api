package ca.ulaval.glo4002.booking.dto;

public class OrderDTO
{
    private String orderDate;
    private String vendorCode;
    private PassDTO passes;

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public void setPasses(PassDTO passes) {
        this.passes = passes;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public PassDTO getPasses() {
        return passes;
    }
}
