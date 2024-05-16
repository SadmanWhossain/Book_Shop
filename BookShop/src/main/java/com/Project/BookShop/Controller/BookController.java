package com.Project.BookShop.Controller;

import com.Project.BookShop.DTO.BookDTO;
import com.Project.BookShop.Entity.BookEntity;
import com.Project.BookShop.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/book/create")
    public BookDTO CreateBook(@RequestBody BookDTO bookDTO){
        return bookService.CreateBook(bookDTO);
    }

    @GetMapping("/book/{id}")
    public BookDTO GetBook(@PathVariable long id){
        return bookService.GetBook(id);
    }

    @GetMapping("/books/")
    public List<BookEntity> GetAllBooks(){
        return bookService.GetAllBooks();
    }

    @PutMapping("/book/update/{id}")
    public BookDTO UpdateBook(@RequestBody BookDTO bookDTO, @PathVariable long id){
        return bookService.UpdateBook(bookDTO, id);
    }

    @DeleteMapping("book/delete/{id}")
    public BookDTO DeleteBook(@PathVariable long id){
        return bookService.DeleteBook(id);
    }
}
