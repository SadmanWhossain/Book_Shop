package com.Project.BookShop.DTO;

import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookDTO {
    private String bookName;
    private String authorName;
    private long price;
}
