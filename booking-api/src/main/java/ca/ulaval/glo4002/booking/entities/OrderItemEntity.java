package ca.ulaval.glo4002.booking.entities;

import javax.persistence.*;

@Entity(name = "OrderItems")
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
    // @ManyToOne
    // OrderEntity order;
    // @OneToOne(mappedBy = "orderItem")
    // public PassEntity pass; // TODO : This should also work for Shuttle and OxygenTank

    public OrderItemEntity() {

    }

    public OrderItemEntity(Long id, PassEntity pass) {
        this.id = id;
        // this.pass = pass;
    }
}
