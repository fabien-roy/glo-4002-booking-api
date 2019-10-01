package ca.ulaval.glo4002.booking.entities;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="orderId", nullable = false)
    private OrderEntity order;

    public OrderItemEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }
}
