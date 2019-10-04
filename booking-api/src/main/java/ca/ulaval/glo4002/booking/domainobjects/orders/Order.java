package ca.ulaval.glo4002.booking.domainobjects.orders;

import ca.ulaval.glo4002.booking.domainobjects.passes.Pass;
import ca.ulaval.glo4002.booking.domainobjects.vendors.Vendor;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
	
	protected Long id;
    private LocalDateTime orderDate;
    private Vendor vendor;
    private List<Pass> passes;
    private double price;

    public Order(Long id, LocalDateTime orderDate, Vendor vendor, List<Pass> passes) {
        this.id = id;
        this.orderDate = orderDate;
        this.vendor = vendor;
        this.passes = passes;
    }

    public Order(Long id, LocalDateTime orderDate, Vendor vendor, List<Pass> passes, double price) {
        this.id = id;
        this.orderDate = orderDate;
        this.vendor = vendor;
        this.passes = passes;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public Vendor getVendor(){
        return this.vendor;
    }

    public List<Pass> getPasses() {
        return passes;
    }

    public void setPasses(List<Pass> passes) {
        this.passes = passes;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
