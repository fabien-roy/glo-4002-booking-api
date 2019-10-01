package ca.ulaval.glo4002.booking.entities;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

    public OrderItemEntity() {

    }

    public OrderItemEntity(Long id, PassEntity pass) {
        this.id = id;
        // this.pass = pass;
    }
}
