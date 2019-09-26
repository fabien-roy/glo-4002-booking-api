package ca.ulaval.glo4002.booking.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDto implements Dto {

    private LocalDateTime orderDate;
    private String vendorCode;
    private List<PassDto> passes;

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public List<PassDto> getPasses() {
        return passes;
    }

    public void setPasses(List<PassDto> passes) {
        this.passes = passes;
    }
}
