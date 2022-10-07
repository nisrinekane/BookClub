package com.nisrinekane.bookclub.services;


import com.nisrinekane.bookclub.models.Book;
import com.nisrinekane.bookclub.models.User;
import com.nisrinekane.bookclub.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //create a book
    public void createBook(Book book, User user) {
        book.setUser(user);
        bookRepository.save(book);
    }

    //retrieve a book
    public Book findBook(long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    //return all books
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }


    public Book updateBook(Book book){
        return bookRepository.save(book);
    }

    // delete all books
    public Book deleteBook(long id) {
        bookRepository.deleteById(id);
        return null;
    }

}
