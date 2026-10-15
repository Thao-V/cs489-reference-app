package com.example.lesson61.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lesson61.entity.Book;
import com.example.lesson61.service.BookService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller  
public class BookViewController {
    private final BookService bookService;
    public BookViewController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String books(Model model) {
        List<Book> books = bookService.getAllBooks();

        System.out.println("Books: " + books.size());
        model.addAttribute("books", books);
        return "books";
    }
}
