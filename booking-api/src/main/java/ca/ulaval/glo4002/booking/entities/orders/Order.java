package ca.ulaval.glo4002.booking.entities.orders;

import ca.ulaval.glo4002.booking.entities.vendors.Vendor;
import ca.ulaval.glo4002.booking.parsers.ParsableEntity;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Order implements ParsableEntity {
	
	@Id
	protected Long id;
    private LocalDateTime orderDate;
    private Vendor vendor;
    private List<OrderItem> orderItems;

    public Order(Long id, LocalDateTime orderDate, Vendor vendor) {
        this.id = id;
        this.orderDate = orderDate;
        this.vendor = vendor;
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
