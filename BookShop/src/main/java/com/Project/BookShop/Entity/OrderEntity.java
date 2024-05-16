package com.Project.BookShop.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String orderStatus;
    private LocalDateTime dateTime;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonManagedReference
    private CustomerEntity customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderListEntity> orderList;

}
