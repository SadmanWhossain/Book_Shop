package com.Project.BookShop.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String bookName;
    private String authorName;
    private long price;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<OrderListEntity> orderList;
}
