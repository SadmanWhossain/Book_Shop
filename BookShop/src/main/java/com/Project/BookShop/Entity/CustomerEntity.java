package com.Project.BookShop.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;
    private String Name;
    private String Phone;
    private String Address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<OrderEntity> orderEntityList;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<OrderListEntity> orderList;
}
