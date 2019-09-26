package ca.ulaval.glo4002.booking.entities.orders;

import ca.ulaval.glo4002.booking.entities.vendors.Vendor;
import ca.ulaval.glo4002.booking.parsers.ParsableEntity;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class Order implements ParsableEntity {
	
	@Id
	protected Long id;
    private LocalDateTime orderDate;
    private Vendor vendor;

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Vendor getVendor(){
        return this.vendor;
    }
}
