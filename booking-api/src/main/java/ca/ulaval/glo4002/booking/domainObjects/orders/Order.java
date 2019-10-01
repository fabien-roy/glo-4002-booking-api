package ca.ulaval.glo4002.booking.domainObjects.orders;

import ca.ulaval.glo4002.booking.domainObjects.vendors.Vendor;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
	
	protected Long id;
    private LocalDateTime orderDate;
    private Vendor vendor;
    private List<OrderItem> orderItems;

    public Order(Long id, LocalDateTime orderDate, Vendor vendor) {
        this.id = id;
        this.orderDate = orderDate;
        this.vendor = vendor;
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

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
}
