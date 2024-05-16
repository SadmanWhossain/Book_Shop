package com.Project.BookShop.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerDTOWithoutOrder {
    private String Name;
    private String Phone;
    private String Address;
}
