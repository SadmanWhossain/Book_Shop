package com.Project.BookShop.DTO;

import com.Project.BookShop.Entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CustomerDTO {
    private String Name;
    private String Phone;
    private String Address;
    private List<OrderDTO> orderDTOList;
}
