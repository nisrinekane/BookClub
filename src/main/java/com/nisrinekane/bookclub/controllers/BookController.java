package com.nisrinekane.bookclub.controllers;


import com.nisrinekane.bookclub.models.Book;
import com.nisrinekane.bookclub.models.User;
import com.nisrinekane.bookclub.services.BookService;
import com.nisrinekane.bookclub.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    // show all books
    @RequestMapping("/books")
    public String index(Model model, @ModelAttribute("book") Book book, HttpSession session) {
        List<Book> books = bookService.allBooks();
        model.addAttribute("user", userService.getUser((Long) session.getAttribute("userId")));
        System.out.println(books);
        model.addAttribute("books", books);
        return "books.jsp";
    }

    //show one book
    @GetMapping("/books/{id}")
    public String showPerson(@PathVariable Long id, Model model, HttpSession session) {
        model.addAttribute("user", userService.getUser((Long) session.getAttribute("userId")));
        Book book = bookService.findBook(id);
        model.addAttribute("book", book);
        return "showOneBook.jsp";
    }

    //create new book: render the form
    @GetMapping("/books/new")
    public String createBookForm(Model model, @ModelAttribute("book") Book book) {
        List<User> users = userService.allUsers();
        model.addAttribute("users", users);
        return "newBook.jsp";
    }

    //create book: post route/ process data
    @PostMapping("/createBook")
    public String createBook(@Valid @ModelAttribute("book")
                             Book book,
                             BindingResult result,
                             HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if(userId == null) {
            return "redirect:/logout";
        }
        if (result.hasErrors()) {
            return "newBook.jsp";
        }
        bookService.createBook(book, userService.findUser(userId));
        return "redirect:/books";
    }

    //update book: render form
    @RequestMapping("/books/{id}/edit")
    public String editBook(@PathVariable("id") Long bookId, Model model) {
        List<User> users = userService.allUsers();
        Book book = bookService.findBook(bookId);
        model.addAttribute("book", book);
        model.addAttribute("users", users);
        System.out.println(book.getId());
        return "editBook.jsp";
    }

    //update book: process data
    @PutMapping ("/books/{id}")
    public String updateBook(@PathVariable("id")
                              Long bookId,
                             @Valid @ModelAttribute("book") Book book,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "editBook.jsp";
        }
        bookService.updateBook(book);
        return "redirect:/books";

    }

    //delete a book
    @DeleteMapping("/books/{bookId}/delete")
    public String destroyBook(@PathVariable("bookId") Long bookId) {
        bookService.deleteBook(bookId);
        return "redirect:/books";
    }

}
