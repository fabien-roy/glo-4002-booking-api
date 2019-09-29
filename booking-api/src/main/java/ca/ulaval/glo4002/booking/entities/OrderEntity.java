package ca.ulaval.glo4002.booking.entities;

import javax.persistence.Entity;

@Entity
public class OrderEntity {

    @javax.persistence.Id
	public Long id;
    public String orderDate;
    public Long vendorId;


    public OrderEntity() {

    }

    public OrderEntity(Long id, String orderDate, Long vendorId) {
        this.id = id;
        this.orderDate = orderDate;
        this.vendorId = vendorId;
    }
}
