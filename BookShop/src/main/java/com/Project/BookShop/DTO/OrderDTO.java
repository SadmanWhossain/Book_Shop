package com.Project.BookShop.DTO;

import com.Project.BookShop.Entity.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class OrderDTO {
    private String orderStatus;
    private LocalDateTime dateTime;
    private String customerName;
}
