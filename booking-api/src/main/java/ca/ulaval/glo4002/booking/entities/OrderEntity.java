package ca.ulaval.glo4002.booking.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
    public String orderDate;
    public Long vendorId;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<OrderItemEntity> orderItems = new ArrayList<>();

    public OrderEntity() {

    }

    public OrderEntity(Long id, String orderDate, Long vendorId) {
        this.id = id;
        this.orderDate = orderDate;
        this.vendorId = vendorId;
    }
}
