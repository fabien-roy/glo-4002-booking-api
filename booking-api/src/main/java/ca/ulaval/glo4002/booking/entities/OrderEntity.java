package ca.ulaval.glo4002.booking.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
	private Long id;
    private LocalDateTime orderDate;
    private Long vendorId;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public Long getVendorId() {
        return vendorId;
    }
}
