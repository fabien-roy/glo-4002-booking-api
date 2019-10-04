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
    private Double price;

    public OrderEntity() {

    }

    public OrderEntity(LocalDateTime orderDate, Long vendorId, List<PassEntity> passes, Double price) {
        this.orderDate = orderDate;
        this.vendorId = vendorId;
        this.passes = passes;
        this.price = price;
    }

    public OrderEntity(Long id, LocalDateTime orderDate, Long vendorId, List<PassEntity> passes, Double price) {
        this.id = id;
        this.orderDate = orderDate;
        this.vendorId = vendorId;
        this.passes = passes;
        this.price = price;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void addOrderItems(List<PassEntity> orderItems) {
        this.passes.addAll(orderItems);
    }

    public void clearOrderItems() {
        this.passes.clear();
    }
}
