package ca.ulaval.glo4002.booking.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
	private Long id;
    private LocalDateTime orderDate;
    private Long vendorId;
    @OneToMany(mappedBy = "order")
    private List<PassEntity> passes = new ArrayList<>();

    public OrderEntity() {

    }

    public OrderEntity(LocalDateTime orderDate, Long vendorId, List<PassEntity> passes) {
        this.orderDate = orderDate;
        this.vendorId = vendorId;
        this.passes = passes;
    }

    public OrderEntity(Long id, LocalDateTime orderDate, Long vendorId, List<PassEntity> passes) {
        this.id = id;
        this.orderDate = orderDate;
        this.vendorId = vendorId;
        this.passes = passes;
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

    public List<PassEntity> getPasses() {
        return passes;
    }

    public void addOrderItems(List<PassEntity> orderItems) {
        this.passes.addAll(orderItems);
    }

    public void clearOrderItems() {
        this.passes.clear();
    }
}
