package ca.ulaval.glo4002.booking.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
	public Long id;
    public LocalDateTime orderDate;
    public Long vendorId;
    // @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    // List<OrderItemEntity> orderItems = new ArrayList<>();

    public OrderEntity() {

    }

    public OrderEntity(LocalDateTime orderDate, Long vendorId) {
        this.orderDate = orderDate;
        this.vendorId = vendorId;
    }

    public OrderEntity(Long id, LocalDateTime orderDate, Long vendorId) {
        this.id = id;
        this.orderDate = orderDate;
        this.vendorId = vendorId;
    }
}
