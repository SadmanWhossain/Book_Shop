package com.Project.BookShop.Service;

import com.Project.BookShop.DTO.BookDTO;
import com.Project.BookShop.Entity.BookEntity;
import com.Project.BookShop.Repository.BookRepository;
import lombok.Setter;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public BookDTO CreateBook(BookDTO bookDTO){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setBookName(bookDTO.getBookName());
        bookEntity.setAuthorName(bookDTO.getAuthorName());
        bookEntity.setPrice(bookDTO.getPrice());

        bookRepository.save(bookEntity);
        return bookDTO;
    }

    public BookDTO GetBook(long id){
        Optional<BookEntity> bookEntityOptional = bookRepository.findById(id);
        if (bookEntityOptional.isEmpty()){
            throw new RuntimeException("Book not found");
        }
        else {
            BookEntity bookEntity = bookEntityOptional.get();
            BookDTO bookDTO = new BookDTO(bookEntity.getBookName(), bookEntity.getAuthorName(), bookEntity.getPrice());
            bookRepository.save(bookEntity);
            return bookDTO;
        }
    }

    public List<BookEntity> GetAllBooks(){
        return bookRepository.findAll();
    }

    public BookDTO UpdateBook(BookDTO bookDTO, long id) {
        Optional<BookEntity> bookEntityOptional = bookRepository.findById(id);
        if(bookEntityOptional.isEmpty()){
            throw new RuntimeException("Book not found");
        }
        else {
            BookEntity bookEntity = bookEntityOptional.get();
            bookEntity.setBookName(bookDTO.getBookName());
            bookEntity.setAuthorName(bookDTO.getAuthorName());
            bookEntity.setPrice(bookDTO.getPrice());

            bookRepository.save(bookEntity);

            return bookDTO;
        }
    }

    public BookDTO DeleteBook(long id){
        Optional<BookEntity> bookEntityOptional =bookRepository.findById(id);
        if(bookEntityOptional.isEmpty()){
            throw new RuntimeException("Book not found");
        }
        else {
            BookEntity bookEntity = bookEntityOptional.get();
            BookDTO bookDTO = new BookDTO(bookEntity.getBookName(), bookEntity.getAuthorName(), bookEntity.getPrice());
            bookRepository.delete(bookEntity);
            return bookDTO;
        }
    }
}
